package pomponiosimone.unita_5_giorno_15_progetto.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventoDTO(
        @NotEmpty(message = "Inserisci titolo")
        @Size(min = 4, max = 30, message = "Il nome deve essere compreso tra 4 e 30 caratteri")
        String titolo,
        @NotEmpty(message = "Inserisci descrizione evento")
        @Size(min = 3, max = 50, message = "La descrizione deve essere compresa tra 3 e 50 caratteri")
        String descrizione,
        @NotNull
        int postiDisponibili,
        LocalDate data,
        @NotEmpty(message = "Inserisci luogo valido")
        @Size(min = 4, max = 30, message = "Il luogo deve essere compreso tra 4 e 30 caratteri")
        String luogo,

        @NotNull(message = "L'ID dell'organizzatore non può essere nullo")
        UUID organizzatoreId
)  {
        public UUID getOrganizzatoreId() {
                return organizzatoreId;
        }}