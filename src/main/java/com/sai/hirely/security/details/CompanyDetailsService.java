package com.sai.hirely.security.details;

import com.sai.hirely.exceptions.EmailNotFounctException;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.repository.company.CompanyRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("companyDetailsService")
public class CompanyDetailsService implements UserDetailsService {
    private final CompanyRepo repo;
    public CompanyDetailsService(CompanyRepo repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Company company = repo.findByEmail(username).orElseThrow(() -> new EmailNotFounctException("Email: "+username+" not found"));
        return new CustomUserDetails(
                company.getEmail(),
                company.getPassword(),
                List.of(new SimpleGrantedAuthority("HIRING_MANAGER")),
                AccountType.HIRING_MANAGER
        );
    }
}
