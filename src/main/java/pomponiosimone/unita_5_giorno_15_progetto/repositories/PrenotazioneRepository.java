package pomponiosimone.unita_5_giorno_15_progetto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Prenotazione;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;

import java.util.List;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findByUtente(Utente utente);
}
