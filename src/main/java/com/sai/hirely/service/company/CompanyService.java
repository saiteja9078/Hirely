package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.CompanyRequest;
import com.sai.hirely.exceptions.company.CompanyNotFoundException;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.repository.company.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    @Autowired
    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Transactional(readOnly = true)
    public Company findById(Long id) throws CompanyNotFoundException {
        return companyRepo.findById(id).orElseThrow(
                () -> new CompanyNotFoundException(id)
        );
    }

    @Transactional
    public Company addCompany(Company entity) {
        return companyRepo.save(entity);
    }

    @Transactional
    public Company updateCompany(Long id, CompanyRequest request) throws CompanyNotFoundException {
        Company company = findById(id);
        company.setName(request.name());
        company.setCompanyProfileUrl(request.companyProfileUrl());
        return company;
    }

    @Transactional
    public void deleteCompany(Long id) throws CompanyNotFoundException {
        if (!companyRepo.existsById(id)) {
            throw new CompanyNotFoundException(id);
        }
        companyRepo.deleteById(id);
    }
}
