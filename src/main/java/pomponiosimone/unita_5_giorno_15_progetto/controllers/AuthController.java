package pomponiosimone.unita_5_giorno_15_progetto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.BadRequestException;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.NewUtenteDTO;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.NewUtenteRespDTO;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.UtenteLoginDTO;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.UtenteLoginRespDTO;
import pomponiosimone.unita_5_giorno_15_progetto.services.AuthService;
import pomponiosimone.unita_5_giorno_15_progetto.services.UtentiService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UtentiService utentiService;
    @Autowired
    private AuthService authService;
    @PostMapping("/login")

    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload) {
        return new UtenteLoginRespDTO (this.authService.checkAndGenerate(payload));}

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {


            return new NewUtenteRespDTO(this.utentiService.save(body).getId());
        }

    }}
