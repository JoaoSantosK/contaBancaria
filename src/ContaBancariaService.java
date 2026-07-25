import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ContaBancariaService {
    private static final DateTimeFormatter FORMATADOR_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss");

    public void realizarDeposito(ContaBancaria conta, BigDecimal valor) {
        try {
            conta.depositar(valor);
            System.out.println("Deposito de R$" + valor + " realizado com sucesso.");
        } catch (ValorInvalidoException e) {
            System.out.println("Erro no depósito: " + e.getMessage());
        }
    }

    public void realizarSaque(ContaBancaria conta, BigDecimal valor) {
        try {
            conta.sacar(valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } catch (SaldoInsuficienteException | ValorInvalidoException e) {
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }

    public void imprimirExtrato(ContaBancaria conta) {
        System.out.println("\n--- Extrato Completo da Conta ---");
        System.out.println("Saldo Atual: R$" + conta.getSaldo());

        conta.getHistorico().forEach(transacao -> {
            String dataFormatada = transacao.dataHora().format(FORMATADOR_BR);
            System.out.println(transacao.tipoTransacao() + " | R$ " + transacao.valor() + " | Data: " + dataFormatada);
        });
    }

    public void imprimirExtratoDoMes(ContaBancaria conta, int mes, int ano) {
        System.out.println("\n--- Extrato do Mês " + mes + "/" + ano + " ---");
        List<Transacao> extratoMes = conta.getExtratoDoMes(mes, ano);

        if (extratoMes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada neste período.");
        } else {
            extratoMes.forEach(transacao -> {
                String dataFormatada = transacao.dataHora().format(FORMATADOR_BR);
                System.out.println(transacao.tipoTransacao() + " | R$ " + transacao.valor() + " | Data: " + dataFormatada);
            });
        }
    }
}
