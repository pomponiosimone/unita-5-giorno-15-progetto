package pomponiosimone.unita_5_giorno_15_progetto.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventoDTO(
        @NotEmpty(message = "Inserisci titolo")
        @Size(min = 4, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String titolo,
        @NotEmpty(message = "Inserisci descrizione evento")
        @Size(min = 3, max = 50, message = "la descrizione deve essere compresa tra 3 e 50 caratteri")
        String descrizione,
        @NotEmpty(message = "Inserisci posti disponibili")
        @Size(min = 1, max = 35, message = "inserisci in numero non in lettere")
        int postiDisponibili,
        @NotEmpty(message = "Inserisci data in modo appropriato")
        LocalDate data,
        @NotEmpty(message = "Inserisci luogo valido")
        @Size(min = 4, max = 30, message = "luogo compreso da 4 e 30 caratteri")
        String luogo
) {
}
