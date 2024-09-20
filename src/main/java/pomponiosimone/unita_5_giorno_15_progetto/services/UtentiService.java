package pomponiosimone.unita_5_giorno_15_progetto.services;

import org.springframework.beans.factory.annotation.Autowired;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.NotFoundException;
import pomponiosimone.unita_5_giorno_15_progetto.repositories.UtentiRepository;

import java.util.List;
import java.util.UUID;

public class UtentiService {
    @Autowired
    private UtentiRepository utentiRepository;

    //Trova tramite Id
    public Utente findById(UUID utenteid) {
        return this.utentiRepository.findById(utenteid).orElseThrow(() -> new NotFoundException(utenteid));

    }
    //ALL
    public List<Utente> findAll() {
        return this.utentiRepository.findAll(); }

    //Trova tramite email
    public Utente findByEmail (String email) {
        return utentiRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("l'utente con l'email" + email + "non è stato trovato!!!"));
    }
    //Salvataggio
    }


