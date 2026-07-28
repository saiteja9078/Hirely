package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.HiringManagerRequest;
import com.sai.hirely.exceptions.company.HiringManagerNotFoundException;
import com.sai.hirely.models.company.Department;
import com.sai.hirely.models.company.HiringManager;
import com.sai.hirely.repository.company.HiringManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HiringManagerService {
    private final HiringManagerRepo hiringManagerRepo;
    private final DepartmentService departmentService;

    @Autowired
    public HiringManagerService(HiringManagerRepo hiringManagerRepo, DepartmentService departmentService) {
        this.hiringManagerRepo = hiringManagerRepo;
        this.departmentService = departmentService;
    }

    @Transactional(readOnly = true)
    public HiringManager findById(Long id) throws HiringManagerNotFoundException {
        return hiringManagerRepo.findById(id).orElseThrow(
                () -> new HiringManagerNotFoundException(id)
        );
    }

    @Transactional
    public HiringManager addHiringManager(HiringManager entity, Long departmentId) {
        if (departmentId != null) {
            Department department = departmentService.findById(departmentId);
            entity.setHiringDepartment(department);
        }
        return hiringManagerRepo.save(entity);
    }

    @Transactional
    public HiringManager updateHiringManager(Long id, HiringManagerRequest request) throws HiringManagerNotFoundException {
        HiringManager manager = findById(id);
        manager.setFirstName(request.firstName());
        manager.setLastName(request.lastName());
        manager.setGender(request.gender());
        manager.setEmail(request.email());

        if (request.departmentId() != null && (manager.getHiringDepartment() == null || !manager.getHiringDepartment().getId().equals(request.departmentId()))) {
            Department department = departmentService.findById(request.departmentId());
            manager.setHiringDepartment(department);
        }
        
        return manager;
    }

    @Transactional
    public void deleteHiringManager(Long id) throws HiringManagerNotFoundException {
        if (!hiringManagerRepo.existsById(id)) {
            throw new HiringManagerNotFoundException(id);
        }
        hiringManagerRepo.deleteById(id);
    }
}
