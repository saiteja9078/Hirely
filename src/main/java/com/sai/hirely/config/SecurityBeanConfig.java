package com.sai.hirely.config;
import com.sai.hirely.security.details.CandidateDetailsService;
import com.sai.hirely.security.details.CompanyDetailsService;
import com.sai.hirely.security.details.HiringManagerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityBeanConfig {
    @Bean("candidateAuthenticationProvider")
    public DaoAuthenticationProvider candidateAuthenticationProvider(
            CandidateDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(service);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Bean("hiringManagerAuthenticationProvider")
    public DaoAuthenticationProvider hiringManagerAuthenticationProvider(
            HiringManagerDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(service);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Bean("companyAuthenticationProvider")
    public DaoAuthenticationProvider companyAuthenticationProvider(
            CompanyDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(service);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Bean("inMemoryUserDetails") // only for testing
    public UserDetailsService getUserDetails(PasswordEncoder encoder) {
        UserDetails details1 =  User.builder()
                .username("saiteja")
                .password(encoder.encode("tejasai"))
                .roles("CANDIDATE")
                .build();

        UserDetails details2 =  User.builder()
                .username("admin")
                .password(encoder.encode("internet"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(details1,details2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("calling bean");
        return new BCryptPasswordEncoder();
    }

}
