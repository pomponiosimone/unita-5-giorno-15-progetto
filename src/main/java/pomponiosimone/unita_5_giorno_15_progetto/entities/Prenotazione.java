package pomponiosimone.unita_5_giorno_15_progetto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Getter
@Entity
@Table(name = "prenotazioni")
@NoArgsConstructor
public class Prenotazione {
    @Id
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;


    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;


}
