package pomponiosimone.unita_5_giorno_15_progetto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.UnauthorizedException;
import pomponiosimone.unita_5_giorno_15_progetto.payloads.UtenteLoginDTO;
import pomponiosimone.unita_5_giorno_15_progetto.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UtentiService utentiService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkAndGenerate(UtenteLoginDTO body) {

        Utente found = this.utentiService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {

            return jwtTools.createToken(found);
        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }


    }}