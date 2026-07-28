package com.sai.hirely.service.role;

import com.sai.hirely.exceptions.candidate.RoleNotFoundException;
import com.sai.hirely.models.utils.RoleEntity;
import com.sai.hirely.repository.role.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleService {
    private RoleRepo repo;
    @Autowired
    public RoleService(RoleRepo repo) {
        this.repo = repo;
    }
    @Transactional(readOnly = true)
    public RoleEntity findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }
    @Transactional(readOnly = true)
    public List<RoleEntity> findByIds(List<Long> ids) {
        return repo.findAllById(ids);
    }
}
