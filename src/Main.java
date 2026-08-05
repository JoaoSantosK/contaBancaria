import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        //ContaBancaria conta = new ContaBancaria(new BigDecimal("100.00"));
        Notificador notificador = new NotificadorSMS();
        ContaBancariaService service = new  ContaBancariaService(notificador);

        ContaCorrente cc = new ContaCorrente(new BigDecimal("1000.00"));
        ContaPoupanca cp = new ContaPoupanca(new BigDecimal("2000.00"));

        System.out.println("- Testando Conta Corrente -");
        System.out.println("Saldo base CC: R$" + cc.getSaldo());
        service.processarTaxa(cc);

        System.out.println("\n- Testando Conta Poupanca -");
        System.out.println("Saldo base CP: R$" + cp.getSaldo());
        service.processarRendimento(cp);
        service.processarTaxa(cp);

        System.out.println("\nOperações");
        service.realizarDeposito(cc, new BigDecimal("150.00"));
        service.realizarSaque(cp, new BigDecimal("100.00"));

    }
}