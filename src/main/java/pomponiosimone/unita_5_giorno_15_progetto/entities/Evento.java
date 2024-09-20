package pomponiosimone.unita_5_giorno_15_progetto.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Setter
@Getter
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String titolo;
    private String descrizione;
    private int postiDisponibili;
    private LocalDate data;
    private String luogo;

    @JoinColumn(name = "organizzatore_id")
    private Utente utente;
    
    public Evento(String titolo, String descrizione, String luogo, int postiDisponibili, LocalDate data) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.postiDisponibili = postiDisponibili;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Evento{" +
                ", titolo='" + titolo + '\'' +
                ", luogo='" + luogo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                "data=" + data +
                ", postiDisponibili=" + postiDisponibili +
                '}';
    }
}
