

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(100);

        conta.depositar(50);

        try {
            conta.sacar(30);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            conta.sacar(-10);
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            conta.sacar(200);
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