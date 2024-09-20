package pomponiosimone.unita_5_giorno_15_progetto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;

import java.util.Optional;
import java.util.UUID;

public interface UtentiRepository extends JpaRepository <Utente, UUID> {
    Optional<Utente> findByEmail (String email);
}
