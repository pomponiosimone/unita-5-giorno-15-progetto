package pomponiosimone.unita_5_giorno_15_progetto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Evento;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
