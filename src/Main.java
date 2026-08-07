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

        System.out.println("\nOperações");
        service.realizarDeposito(cc, new BigDecimal("150.00"));
        service.realizarSaque(cp, new BigDecimal("100.00"));

        System.out.println("\nExtrato completo via DTO");

        ExtratoDTO extrato = service.gerarExtrato(cc);
        System.out.println("Saldo atual: " + extrato.saldoAtualFormatado());

        extrato.transacoes().forEach(t -> {
            System.out.println(t.tipo() + " -> " + t.valorFormatado() + " | Criado em: " + t.dataFormatada());
        });

        System.out.println("\n--- Extrato do Mês Atual via DTO ---");

        int mesAtual = LocalDateTime.now().getMonthValue();
        int anoAtual = LocalDateTime.now().getYear();

        try {
            // O Front tenta pedir os dados ao backend
            ExtratoDTO extratoMes = service.gerarExtratoDoMes(cc, mesAtual, anoAtual);

            // Se deu certo, ele imprime
            extratoMes.transacoes().forEach(t -> {
                System.out.println(t.tipo() + " -> " + t.valorFormatado() + " | Criado em: " + t.dataFormatada());
            });

        } catch (ExtratoDoMesInvalido | ValorInvalidoException e) {
            // Se o Service barrar, o Front apenas avisa o usuário
            System.out.println("Ops: " + e.getMessage());
        }

    }
}