package com.sai.hirely.apis;

import com.sai.hirely.dto.catalog.CatalogItem;
import com.sai.hirely.dto.catalog.CatalogDepartmentItem;
import com.sai.hirely.repository.company.DepartmentRepo;
import com.sai.hirely.repository.job.IndustryRepo;
import com.sai.hirely.repository.role.RoleRepo;
import com.sai.hirely.repository.skill.SkillRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogApi {
    private final SkillRepo skillRepo;
    private final RoleRepo roleRepo;
    private final IndustryRepo industryRepo;
    private final DepartmentRepo departmentRepo;

    public CatalogApi(SkillRepo skillRepo, RoleRepo roleRepo, IndustryRepo industryRepo, DepartmentRepo departmentRepo) {
        this.skillRepo = skillRepo;
        this.roleRepo = roleRepo;
        this.industryRepo = industryRepo;
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("/skills")
    public List<CatalogItem> skills() {
        return skillRepo.findAll().stream().map(skill -> new CatalogItem(skill.getId(), skill.getName())).toList();
    }

    @GetMapping("/roles")
    public List<CatalogItem> roles() {
        return roleRepo.findAll().stream().map(role -> new CatalogItem(role.getId(), role.getName())).toList();
    }

    @GetMapping("/industries")
    public List<CatalogItem> industries() {
        return industryRepo.findAll().stream().map(industry -> new CatalogItem(industry.getId(), industry.getName())).toList();
    }

    @GetMapping("/departments")
    public List<CatalogDepartmentItem> departments() {
        return departmentRepo.findCatalogItems();
    }
}
