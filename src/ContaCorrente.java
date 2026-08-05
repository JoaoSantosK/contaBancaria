import java.math.BigDecimal;

public class ContaCorrente extends ContaBancaria implements Tributavel{
    public ContaCorrente(BigDecimal saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public BigDecimal calcularTaxaMensal() {
        return new BigDecimal("15.00");
    }

}
