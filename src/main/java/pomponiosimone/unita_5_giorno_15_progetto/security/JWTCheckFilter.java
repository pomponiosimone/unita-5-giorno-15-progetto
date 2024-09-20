package pomponiosimone.unita_5_giorno_15_progetto.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pomponiosimone.unita_5_giorno_15_progetto.entities.Utente;
import pomponiosimone.unita_5_giorno_15_progetto.exceptions.UnauthorizedException;
import pomponiosimone.unita_5_giorno_15_progetto.services.UtentiService;

import java.io.IOException;
import java.util.UUID;

public class JWTCheckFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UtentiService utentiService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Per favore inserisci correttamente il token nell'Authorization Header");


        String accessToken = authHeader.substring(7);


        jwtTools.verifyToken(accessToken);


        String id = jwtTools.extractIdFromToken(accessToken);
        Utente currentUser = this.utentiService.findById(UUID.fromString(id));


        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
