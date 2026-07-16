import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transacao(TipoTransacao tipoTransacao, BigDecimal valor, LocalDateTime dataHora) {}

