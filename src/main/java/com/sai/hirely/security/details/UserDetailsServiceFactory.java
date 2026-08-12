package com.sai.hirely.security.details;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceFactory {

    private final CandidateDetailsService candidateDetailsService;
    private final CompanyDetailsService companyDetailsService;
    private final HiringManagerDetailsService hiringManagerDetailsService;

    public UserDetailsServiceFactory(
            CandidateDetailsService candidateDetailsService,
            CompanyDetailsService companyDetailsService,
            HiringManagerDetailsService hiringManagerDetailsService) {

        this.candidateDetailsService = candidateDetailsService;
        this.companyDetailsService = companyDetailsService;
        this.hiringManagerDetailsService = hiringManagerDetailsService;
    }

    public UserDetailsService getUserDetailsService(AccountType type) {

        return switch (type) {
            case CANDIDATE -> candidateDetailsService;
            case COMPANY -> companyDetailsService;
            case HIRING_MANAGER -> hiringManagerDetailsService;
        };
    }
}