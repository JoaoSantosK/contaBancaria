import java.time.LocalDateTime;

public record Transacao(TipoTransacao tipoTransacao, double valor, LocalDateTime dataHora) {}

