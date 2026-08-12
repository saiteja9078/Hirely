package com.sai.hirely.apis.login;
import com.sai.hirely.dto.auth.AuthenticationRequest;
import com.sai.hirely.dto.auth.AuthenticationResponse;
import com.sai.hirely.security.JwtService;
import com.sai.hirely.security.details.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/candidate")
public class CandidateLoginApi {
    private final JwtService jwtService;
    private final DaoAuthenticationProvider authProvider;

    public CandidateLoginApi(JwtService jwtService,
                             @Qualifier("candidateDetailsService") UserDetailsService userDetailsService,
                             @Qualifier("candidateAuthenticationProvider") DaoAuthenticationProvider authManager) {
        this.jwtService = jwtService;
        this.authProvider = authManager;
    }
    @PostMapping
    public ResponseEntity<AuthenticationResponse> candidateLogin(
           @Valid @RequestBody AuthenticationRequest request
    ) {
    Authentication auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(
            request.username(),request.password()
    ));
    String token = jwtService.generateToken((CustomUserDetails) auth.getPrincipal());
       return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token));
    }
}
