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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiringManagerLoginApi {
    private final JwtService jwtService;
    private final DaoAuthenticationProvider authenticationProvider;
    private final com.sai.hirely.service.company.HiringManagerService hiringManagerService;
    private final com.sai.hirely.mappers.HiringManagerMapper hiringManagerMapper;

    public HiringManagerLoginApi(JwtService jwtService,
                             @Qualifier("hiringManagerAuthenticationProvider") DaoAuthenticationProvider authenticationProvider,
                             com.sai.hirely.service.company.HiringManagerService hiringManagerService,
                             com.sai.hirely.mappers.HiringManagerMapper hiringManagerMapper) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
        this.hiringManagerService = hiringManagerService;
        this.hiringManagerMapper = hiringManagerMapper;
    }

    @PostMapping("/login/hiring-manager")
    public ResponseEntity<AuthenticationResponse> hiringManagerLogin(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),request.password()
        ));
        String token = jwtService.generateToken((CustomUserDetails) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token, request.username()));
    }

    @PostMapping("/signup/hiring-manager")
    public ResponseEntity<AuthenticationResponse> hiringManagerSignUp(
            @RequestBody com.sai.hirely.dto.auth.HiringManagerSignupRequest request
    ) {
        com.sai.hirely.models.company.HiringManager savedManager = hiringManagerService.addHiringManager(
                hiringManagerMapper.toEntity(request),
                request.departmentId()
        );
        CustomUserDetails userDetails = new CustomUserDetails(
                savedManager.getId(),
                savedManager.getEmail(),
                savedManager.getPassword(),
                java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_HIRING_MANAGER")),
                com.sai.hirely.security.details.AccountType.HIRING_MANAGER
        );
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthenticationResponse(token, savedManager.getEmail()));
    }
}
