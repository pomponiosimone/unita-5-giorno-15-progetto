package pomponiosimone.unita_5_giorno_15_progetto.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
        @NotEmpty(message = "Inserisci nome")
        @Size(min = 4, max = 30, message = "Il nome deve essere compreso tra 3 e 50 caratteri")
        String nome,
        @NotEmpty(message = "Inserisci cognome")
        @Size(min = 4, max = 30, message = "Il cognome deve essere compreso tra 3 e 50 caratteri")
        String cognome,
        @NotEmpty(message = "Inserisci password valida")
        @Size(min = 4, max = 35, message = "la password deve essere compresa tra 3 e 40 caratteri")
        String password,
        @NotEmpty(message = "Non hai inserito l'email")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "Inserisci ruolo valido")
        @Size(min = 4, max = 30, message = "Il ruolo può essere UTENTE_NORMALE o ORGANIZZATORE")
        String role
) {
}
