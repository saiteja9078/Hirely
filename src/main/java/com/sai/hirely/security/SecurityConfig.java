package com.sai.hirely.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/login/**", "/signup/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/migrate-pass").permitAll()
                        .requestMatchers("/api/companies/me/**").hasRole("COMPANY")
                        .requestMatchers("/api/candidates/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/candidate-skills/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/candidate-experiences/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/files/upload/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/notifications/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/apply/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/applications/me/**").hasRole("CANDIDATE")
                        .requestMatchers("/api/applications/job/**").hasAnyRole("HIRING_MANAGER", "COMPANY")
                        .requestMatchers("/api/applications/*").hasAnyRole("HIRING_MANAGER", "COMPANY")
                        .requestMatchers("/api/post-job/mine").hasAnyRole("HIRING_MANAGER", "COMPANY")
                        .requestMatchers("/api/hiring-managers/**").hasAnyRole("HIRING_MANAGER", "COMPANY")
                        .requestMatchers(GET, "/api/catalog/**", "/api/skills").permitAll()
                        .requestMatchers(GET, "/api/companies", "/api/companies/*").permitAll()
                        .requestMatchers(GET, "/api/company-reviews/company/*").permitAll()
                        .requestMatchers("/api/company-reviews/**").hasRole("CANDIDATE")
                        .requestMatchers(GET, "/api/post-job/filter", "/api/post-job/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/post-job/filter").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Value("${app.cors.allowed-origin}")
    private String allowedOrigins;
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept", "X-Requested-With", "Origin"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        configuration.setAllowedOriginPatterns(List.of(
                allowedOrigins.split(",")
        ));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
