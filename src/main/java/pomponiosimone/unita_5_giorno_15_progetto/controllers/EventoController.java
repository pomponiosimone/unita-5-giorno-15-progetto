package pomponiosimone.unita_5_giorno_15_progetto.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Evento;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.BadRequestException;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.NewEventoDTO;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.NewEventoRespDTO;
import pomponiosimone.unita_5_giorno_15_progetto.services.EventoService;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // GET ALL
    @GetMapping
    public Page<Evento> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy){
        return this.eventoService.findAllEventi(page, size, sortBy);
    }
    // GET ID
    @GetMapping("/{eventoId}")
    public Evento findByEventId(@PathVariable UUID eventoId){return this.eventoService.findByEventId(eventoId);}


    // POST

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventoRespDTO saveEvent(@RequestBody @Validated NewEventoDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload" + messages);
        }else {
            return new NewEventoRespDTO(this.eventoService.saveEvento(body).getId());
        }
    }

//Modifica

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento findByEventIdAndUpdate(@PathVariable UUID eventoId,@RequestBody Evento body){
        return this.eventoService.findByIdAndUpdate(eventoId,body);
    }
//Elimina
@DeleteMapping("/{eventoId}")
@PreAuthorize("hasAuthority('ORGANIZZATORE')")
@ResponseStatus(HttpStatus.NO_CONTENT)
public Evento findByIdAndDelete(@PathVariable UUID eventoId){
    this.eventoService.findByIdAndDelete(eventoId);
    return null;
}
}