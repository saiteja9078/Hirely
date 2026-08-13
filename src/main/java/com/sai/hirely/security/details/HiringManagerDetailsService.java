package com.sai.hirely.security.details;

import com.sai.hirely.exceptions.EmailNotFounctException;
import com.sai.hirely.models.company.HiringManager;
import com.sai.hirely.repository.company.HiringManagerRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("hiringManagerDetailsService")
public class HiringManagerDetailsService implements UserDetailsService {
    private final HiringManagerRepo repo;
    public HiringManagerDetailsService(HiringManagerRepo repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HiringManager hiringManager = repo.findByEmail(username).orElseThrow(() -> new EmailNotFounctException("Email: "+username+" not found"));
        return new CustomUserDetails(
                hiringManager.getId(),
                hiringManager.getEmail(),
                hiringManager.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_HIRING_MANAGER")),
                AccountType.HIRING_MANAGER
        );
    }
}
