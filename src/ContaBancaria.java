import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaBancaria { //Inicio a classe //correção: class fora do main (para ser global)
    private BigDecimal saldo;
    private final List<Transacao> historico = new ArrayList<>();

    public ContaBancaria(BigDecimal saldoInicial) {
        if(saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("Saldo inicial negativo");
        }
        this.saldo = saldoInicial;
        if (saldoInicial.compareTo(BigDecimal.ZERO) > 0) {
            historico.add(new Transacao(TipoTransacao.DEPOSITO_INICIAL, saldoInicial, LocalDateTime.now()));
        }
    }

    public void depositar(BigDecimal valor) { //verifico se o valor permite depósito
        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor invalido"); //Caso a tentativa de dep seja menor ou igual a 0, bloqueia
        }
        this.saldo = this.saldo.add(valor);
        historico.add(new Transacao(TipoTransacao.DEPOSITO, valor, LocalDateTime.now()));
    }

    public void sacar(BigDecimal valor) { //method p sacar
        if (valor.compareTo(BigDecimal.ZERO) <= 0) { //verifica se valor pode ser sacado (valor válido)
            throw new ValorInvalidoException("Valor invalido");
        }
        if (valor.compareTo(this.saldo) > 0) { //Verifica se o valor ao tentar sacar é maior que o saldo, caso seja, block
            throw new SaldoInsuficienteException("Saldo insuficiente"); // illegal é state pois a tentativa (argumentação) é válida, só não a requisição (estado)
        }
        this.saldo = this.saldo.subtract(valor);
        historico.add(new Transacao(TipoTransacao.SAQUE, valor, LocalDateTime.now()));
    }

    public BigDecimal getSaldo() { //func para retornar saldo o user
        return saldo;
    }

    public List<Transacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public List<Transacao> getExtratoDoMes(int mes, int ano) {
        return historico.stream()
                .filter(t -> t.dataHora().getMonthValue() == mes && t.dataHora().getYear() == ano)
                .toList();
    }

}