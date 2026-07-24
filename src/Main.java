import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(new BigDecimal("100.00"));

        conta.depositar(new BigDecimal("50.00"));
        conta.depositar(new BigDecimal("1250.00"));

        try {
            conta.sacar(new BigDecimal("30.00"));
        } catch (SaldoInsuficienteException | ValorInvalidoException e) {
            System.out.println("Erro na transação: " + e.getMessage());
        }

        try {
            conta.sacar(new BigDecimal("-10.00"));
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            conta.sacar(new BigDecimal("2000.00"));
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        conta.sacar(new BigDecimal("25.00"));

        DateTimeFormatter formatadorBr = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss");

        System.out.println("\n--- Extrato da Conta ---");
        System.out.println("Saldo Final: R$ " + conta.getSaldo());
        System.out.println("Histórico de Transações:");

        conta.getHistorico().forEach(transacao -> {
            System.out.println(transacao.tipoTransacao() + " | R$ " + transacao.valor() + " | Data: " + transacao.dataHora());
        });

        System.out.println("Testando filtro: Extrato do mês atual.");

        int mesAtual = LocalDateTime.now().getMonthValue();
        int anoAtual = LocalDateTime.now().getYear();

        List<Transacao> extratoMes = conta.getExtratoDoMes(mesAtual, anoAtual);

        if (extratoMes.isEmpty()) {
            throw new ExtratoDoMesInvalido("Sem extratos para esse mês!");
        } else {
            extratoMes.forEach(transacao -> {
               String dataFormatada = transacao.dataHora().format(formatadorBr);
                System.out.println(transacao.tipoTransacao() + " | R$ " + transacao.valor() + " | Data " + dataFormatada);
            });
        }

        //Extrato ordenado
        System.out.println("\n--- Extrato ordenado por valor ---");

        List<Transacao> extratoOrdenado = conta.getExtratoDoMesPorValor();

        extratoOrdenado.forEach(transacao -> {
           String dataFormatada = transacao.dataHora().format(formatadorBr);
            System.out.println(transacao.tipoTransacao() + " | R$ " + transacao.valor() + " | Data: " + dataFormatada);
        });

    }
}