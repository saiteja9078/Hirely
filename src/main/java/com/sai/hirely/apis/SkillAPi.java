package com.sai.hirely.apis;

import com.sai.hirely.dto.SkillResponse;
import com.sai.hirely.service.skill.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillAPi {

    private final SkillService skillService;

    public SkillAPi(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillResponse> findAllSkills() {
        return skillService.findAll();
    }
}
