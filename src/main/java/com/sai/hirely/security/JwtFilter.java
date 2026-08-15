package com.sai.hirely.security;

import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import com.sai.hirely.security.details.UserDetailsServiceFactory;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceFactory userDetailsServiceFactory;
    public JwtFilter(JwtService jwtService, UserDetailsServiceFactory userDetailsServiceFactory) {
        this.jwtService = jwtService;
        this.userDetailsServiceFactory = userDetailsServiceFactory;
    }
    @Override
    public void doFilterInternal(HttpServletRequest httpRequest,
                                 HttpServletResponse httpResponse,
                                 FilterChain filterChain) throws ServletException, IOException {
        String auth = httpRequest.getHeader("Authorization");

        if(auth == null || !auth.startsWith("Bearer ")) {
            filterChain.doFilter(httpRequest,httpResponse);
            return;
        }
        String token = auth.substring(7);
        String userName = jwtService.extractClaim(token, Claims::getSubject);
        String type = jwtService.extractClaim(token, claims -> claims.get("type",String.class));

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null && type!=null) {
            try {
                CustomUserDetails details =(CustomUserDetails) userDetailsServiceFactory.getUserDetailsService(AccountType.valueOf(type)).loadUserByUsername(userName);
                if(jwtService.isTokenValid(token,details)) {
                    UsernamePasswordAuthenticationToken authentication = new
                            UsernamePasswordAuthenticationToken(
                            details,null,details.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.getWriter().write("{\"error\": \"Unauthorized - User not found\"}");
                return;
            }
        }
        filterChain.doFilter(httpRequest,httpResponse);

    }
}
