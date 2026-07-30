package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.DepartmentRequest;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.company.Department;
import com.sai.hirely.repository.company.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sai.hirely.exceptions.company.EntityNotFoundException;

@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final CompanyService companyService;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo, CompanyService companyService) {
        this.departmentRepo = departmentRepo;
        this.companyService = companyService;
    }

    @Transactional(readOnly = true)
    public Department findById(Long id) throws EntityNotFoundException {
        return departmentRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Department", id)
        );
    }

    @Transactional
    public Department addDepartment(Department entity, Long companyId) {
        if (companyId != null) {
            Company company = companyService.findById(companyId);
            entity.setCompany(company);
        }
        return departmentRepo.save(entity);
    }

    @Transactional
    public Department updateDepartment(Long id, DepartmentRequest request) throws EntityNotFoundException {
        Department department = findById(id);
        department.setName(request.name());
        
        if (request.companyId() != null && (department.getCompany() == null || !department.getCompany().getId().equals(request.companyId()))) {
            Company company = companyService.findById(request.companyId());
            department.setCompany(company);
        }
        
        return department;
    }

    @Transactional
    public void deleteDepartment(Long id) throws EntityNotFoundException {
        if (!departmentRepo.existsById(id)) {
            throw new EntityNotFoundException("Department", id);
        }
        departmentRepo.deleteById(id);
    }
}
