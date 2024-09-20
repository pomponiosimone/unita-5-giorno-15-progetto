package pomponiosimone.unita_5_giorno_15_progetto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;
import pomponiosimone.unita_5_giorno_15_progetto.services.UtentiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtentiService utentiService;


    //Get By ID
    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable UUID utenteId) {
        return this.utentiService.findById(utenteId);
    }
     //GET ALL
    @GetMapping
    public List<Utente> findAll() { return utentiService.findAll();}}
