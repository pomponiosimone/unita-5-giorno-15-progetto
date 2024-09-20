package pomponiosimone.unita_5_giorno_15_progetto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.BadRequestException;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.NotFoundException;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.NewUtenteDTO;
import pomponiosimone.unita_5_giorno_15_progetto.repositories.UtentiRepository;

import java.util.List;
import java.util.UUID;
@Service
public class UtentiService {
    @Autowired
    private UtentiRepository utentiRepository;
    @Autowired
    private PasswordEncoder bcrypt;

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
   public Utente save(NewUtenteDTO body) {
        this.utentiRepository.findByEmail(body.email()).ifPresent(user -> {throw new BadRequestException("l'email"+ body.email() + "in uso");});
        Utente newUtente = new Utente(body.cognome(),body.email(), body.nome(), bcrypt.encode(body.password()),body.role());
       return this.utentiRepository.save(newUtente);
   }
    }


