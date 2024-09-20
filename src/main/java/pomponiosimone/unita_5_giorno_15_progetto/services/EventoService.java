package pomponiosimone.unita_5_giorno_15_progetto.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Evento;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.NotFoundException;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.NewEventoDTO;
import pomponiosimone.unita_5_giorno_15_progetto.repositories.EventoRepository;
import pomponiosimone.unita_5_giorno_15_progetto.repositories.UtentiRepository;


import java.util.UUID;

@Service
public class EventoService {


    @Autowired
    private EventoRepository eventoRepository;
@Autowired
private UtentiRepository utentiRepository;
//GET ALL

    public Page<Evento> findAllEventi(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventoRepository.findAll(pageable);
    }

    //GET ID
    public Evento findByEventId(UUID eventoId) {
        return this.eventoRepository.findById(eventoId).orElseThrow(() -> new NotFoundException(eventoId));
    }
    //salvataggio

    public Evento saveEvento(NewEventoDTO body) {
        Utente organizzatore = utentiRepository.findById(body.organizzatoreId())
                .orElseThrow(() -> new NotFoundException("Organizzatore non trovato"));
        Evento newEvent = new Evento(body.titolo(), body.descrizione(), body.luogo(), body.postiDisponibili(), body.data());

        return this.eventoRepository.save(newEvent);
    }



    //modifica tramite id
    public Evento findByIdAndUpdate(UUID eventoId, Evento newUserData) {

        Evento found = this.findByEventId(eventoId);
        found.setTitolo(newUserData.getTitolo());
        found.setDescrizione(newUserData.getDescrizione());
        found.setLuogo(newUserData.getLuogo());
        found.setPostiDisponibili(newUserData.getPostiDisponibili());
        found.setData(newUserData.getData());

        return this.eventoRepository.save(found);

    }

    //Delete
    public Evento findByIdAndDelete(UUID eventoId) {

        Evento found = this.findByEventId(eventoId);
        this.eventoRepository.delete(found);
        return found;

}}
