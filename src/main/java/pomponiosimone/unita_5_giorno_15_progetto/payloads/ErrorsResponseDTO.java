package pomponiosimone.unita_5_giorno_15_progetto.payloads;


import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
