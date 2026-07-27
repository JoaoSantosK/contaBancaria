import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(new BigDecimal("100.00"));
        Notificador notificador = new NotificadorSMS();
        ContaBancariaService service = new  ContaBancariaService(notificador);

        service.realizarDeposito(conta, new BigDecimal("100.00"));
        service.realizarDeposito(conta, new BigDecimal("1250.00"));

        service.realizarSaque(conta, new BigDecimal("50.00"));

        service.realizarSaque(conta, new BigDecimal("-50.00"));
        service.realizarSaque(conta, new BigDecimal("5000.00"));

        service.realizarSaque(conta, new BigDecimal("50.00"));

        service.imprimirExtrato(conta);

        int mesAtual = LocalDateTime.now().getMonthValue();
        int anoAtual = LocalDateTime.now().getYear();

        service.imprimirExtratoDoMes(conta, mesAtual, anoAtual);
    }
}