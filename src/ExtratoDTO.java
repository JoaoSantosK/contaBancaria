import java.util.List;

public record ExtratoDTO(String saldoAtualFormatado, List<TransacaoDTO> transacoes) {}
