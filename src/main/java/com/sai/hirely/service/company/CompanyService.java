package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.CompanyRequest;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.repository.company.CompanyRepo;
import com.sai.hirely.repository.job.IndustryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sai.hirely.exceptions.company.EntityNotFoundException;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final IndustryRepo industryRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, IndustryRepo industryRepo, PasswordEncoder passwordEncoder) {
        this.companyRepo = companyRepo;
        this.industryRepo = industryRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Company findById(Long id) throws EntityNotFoundException {
        return companyRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Company", id)
        );
    }

    @Transactional
    public Company addCompany(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.name());
        company.setCompanyProfileUrl(request.companyProfileUrl());
        company.setLocation(request.location());
        company.setEmail(request.email());
        if (request.password() != null && !request.password().isEmpty()) {
            company.setPassword(passwordEncoder.encode(request.password()));
        }
        if (request.industryId() != null) {
            company.setIndustry(industryRepo.getReferenceById(request.industryId()));
        }
        return companyRepo.save(company);
    }

    @Transactional
    public Company updateCompany(Long id, CompanyRequest request) throws EntityNotFoundException {
        Company company = findById(id);
        company.setName(request.name());
        company.setCompanyProfileUrl(request.companyProfileUrl());
        company.setLocation(request.location());
        company.setEmail(request.email());
        if (request.password() != null && !request.password().isEmpty()) {
            company.setPassword(passwordEncoder.encode(request.password()));
        }
        
        if (request.industryId() != null && (company.getIndustry() == null || !company.getIndustry().getId().equals(request.industryId()))) {
            company.setIndustry(industryRepo.getReferenceById(request.industryId()));
        }
        
        return company;
    }

    @Transactional
    public void deleteCompany(Long id) throws EntityNotFoundException {
        if (!companyRepo.existsById(id)) {
            throw new EntityNotFoundException("Company", id);
        }
        companyRepo.deleteById(id);
    }
}
