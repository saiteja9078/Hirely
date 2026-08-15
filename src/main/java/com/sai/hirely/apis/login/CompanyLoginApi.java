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
public class CompanyLoginApi {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DaoAuthenticationProvider authenticationProvider;
    private final com.sai.hirely.service.company.CompanyService companyService;
    private final com.sai.hirely.mappers.CompanyMapper companyMapper;

    public CompanyLoginApi(JwtService jwtService,
                                 @Qualifier("companyDetailsService") UserDetailsService userDetailsService,
                                 @Qualifier("companyAuthenticationProvider") DaoAuthenticationProvider authenticationProvider,
                                 com.sai.hirely.service.company.CompanyService companyService,
                                 com.sai.hirely.mappers.CompanyMapper companyMapper) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationProvider = authenticationProvider;
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }
    
    @PostMapping("/login/company")
    // Have to add company verification logic, for now accept every company login
    public ResponseEntity<AuthenticationResponse> companyLogin(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),request.password()
        ));
        String token = jwtService.generateToken( (CustomUserDetails) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token, request.username()));
    }

    @PostMapping("/signup/company")
    public ResponseEntity<AuthenticationResponse> companySignUp(
            @RequestBody com.sai.hirely.dto.auth.CompanySignupRequest request
    ) {
        com.sai.hirely.models.company.Company savedCompany = companyService.addCompany(
            companyMapper.toEntity(request), 
            request.industryId(), 
            request.industryName()
        );
        CustomUserDetails userDetails = new CustomUserDetails(
                savedCompany.getId(),
                savedCompany.getEmail(),
                savedCompany.getPassword(),
                java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_COMPANY")),
                com.sai.hirely.security.details.AccountType.COMPANY
        );
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthenticationResponse(token, savedCompany.getEmail()));
    }
}
