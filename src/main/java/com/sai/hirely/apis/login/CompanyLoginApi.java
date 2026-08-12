package com.sai.hirely.apis.login;
import com.sai.hirely.dto.auth.AuthenticationRequest;
import com.sai.hirely.dto.auth.AuthenticationResponse;
import com.sai.hirely.security.JwtService;
import com.sai.hirely.security.details.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/company")
public class CompanyLoginApi {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DaoAuthenticationProvider authenticationProvider;

    public CompanyLoginApi(JwtService jwtService,
                                 @Qualifier("companyDetailsService") UserDetailsService userDetailsService,
                                 @Qualifier("companyAuthenticationProvider") DaoAuthenticationProvider authenticationProvider) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationProvider = authenticationProvider;
    }
    @PostMapping
    // Have to add company verification logic, for now accept every company login
    public ResponseEntity<AuthenticationResponse> candidateLogin(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),request.password()
        ));
        String token = jwtService.generateToken( (CustomUserDetails) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token));
    }
}
