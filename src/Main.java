import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(new BigDecimal("100.00"));

        conta.depositar(new BigDecimal("50.00"));

        try {
            conta.sacar(new BigDecimal("30.00"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            conta.sacar(new BigDecimal("-10.00"));
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            conta.sacar(new BigDecimal("200.00"));
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n--- Extrato da Conta ---");
        System.out.println("Saldo Final: R$ " + conta.getSaldo());
        System.out.println("Histórico de Transações:");

        conta.getHistorico().forEach(transacao -> {
            System.out.println(transacao.tipoTransacao() + " | R$ " + transacao.valor() + " | Data: " + transacao.dataHora());
        });

    }
}