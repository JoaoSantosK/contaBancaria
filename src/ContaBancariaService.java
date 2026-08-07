import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ContaBancariaService {
    private static final DateTimeFormatter FORMATADOR_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss");
    private final Notificador notificador;
    public ContaBancariaService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void realizarDeposito(ContaBancaria conta, BigDecimal valor) {
        try {
            conta.depositar(valor);
            notificador.enviarNotificacao("Depósito de:R$ " + valor + " realizado com sucesso.");
        } catch (ValorInvalidoException e) {
            System.out.println("Erro no depósito: " + e.getMessage());
        }
    }

    public void realizarSaque(ContaBancaria conta, BigDecimal valor) {
        try {
            conta.sacar(valor);
            notificador.enviarNotificacao("Saque de: R$" + valor + " realizado com sucesso;");
        } catch (SaldoInsuficienteException | ValorInvalidoException e) {
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }

    public ExtratoDTO gerarExtrato(ContaBancaria conta) {
        List<TransacaoDTO> transacoesDTO = conta.getHistorico().stream()
                .map(transacao -> new TransacaoDTO(
                        transacao.tipoTransacao().toString(),
                        "RS " + transacao.valor(),
                        transacao.dataHora().format(FORMATADOR_BR)
                ))
                .toList();

        String saldoFormatado = "R$ " + conta.getSaldo();
        return new ExtratoDTO(saldoFormatado, transacoesDTO);
    }

    public ExtratoDTO gerarExtratoDoMes(ContaBancaria conta, int mes, int ano) {
        if (mes < 1 || mes > 12) {
            throw new ValorInvalidoException("Mês inválido.");
        }

        List<Transacao> transacoesDoMes = conta.getExtratoDoMes(mes, ano);

        if (transacoesDoMes.isEmpty()) {
            throw new ExtratoDoMesInvalido("Sem extratos para esse mês!");
        }

        List<TransacaoDTO> transacoesDTO = transacoesDoMes.stream()
                .map(transacao -> new TransacaoDTO(
                   transacao.tipoTransacao().toString(),
                   "R$ " + transacao.valor(),
                   transacao.dataHora().format(FORMATADOR_BR)
                ))
                .toList();

        String saldoFormatado = "R$ " + conta.getSaldo();
        return new ExtratoDTO(saldoFormatado, transacoesDTO);
    }

    public void processarTaxa(Tributavel contaTributavel) {
        BigDecimal taxa = contaTributavel.calcularTaxaMensal();
        notificador.enviarNotificacao("Taxa mensal calculada para desconto: R$ " + taxa);
    }

    public void processarRendimento(Rentavel contaRentavel) {
        BigDecimal rendimento = contaRentavel.calcularRendimento();
        notificador.enviarNotificacao("Rendimento mensal para crédito: R$ " + rendimento);
    }

}
