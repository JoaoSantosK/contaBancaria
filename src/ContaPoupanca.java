import java.math.BigDecimal;

public class ContaPoupanca extends ContaBancaria implements Rentavel {
    public ContaPoupanca(BigDecimal saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public BigDecimal calcularRendimento() {
        BigDecimal taxaRendimento = new BigDecimal("0.005");
        return getSaldo().multiply(taxaRendimento);
    }

}
