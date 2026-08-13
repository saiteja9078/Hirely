package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.HiringManagerRequest;
import com.sai.hirely.models.company.Department;
import com.sai.hirely.models.company.HiringManager;
import com.sai.hirely.repository.company.HiringManagerRepo;
import com.sai.hirely.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sai.hirely.exceptions.company.EntityNotFoundException;

@Service
public class HiringManagerService {
    private final HiringManagerRepo hiringManagerRepo;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public HiringManagerService(HiringManagerRepo hiringManagerRepo, DepartmentService departmentService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.hiringManagerRepo = hiringManagerRepo;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Transactional(readOnly = true)
    public HiringManager findById(Long id) throws EntityNotFoundException {
        return hiringManagerRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("HiringManager", id)
        );
    }

    @Transactional
    public HiringManager addHiringManager(HiringManager entity, Long departmentId) {
        if (departmentId != null) {
            Department department = departmentService.findById(departmentId);
            entity.setHiringDepartment(department);
        }
        if (entity.getPassword() != null && !entity.getPassword().isEmpty()) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        HiringManager savedManager = hiringManagerRepo.save(entity);
        emailService.sendWelcomeEmail(savedManager.getEmail(), savedManager.getFirstName(), "Hiring Manager");
        return savedManager;
    }

    @Transactional
    public HiringManager updateHiringManager(Long id, HiringManagerRequest request) throws EntityNotFoundException {
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
    public void deleteHiringManager(Long id) throws EntityNotFoundException {
        if (!hiringManagerRepo.existsById(id)) {
            throw new EntityNotFoundException("HiringManager", id);
        }
        hiringManagerRepo.deleteById(id);
    }
}
