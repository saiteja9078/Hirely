package com.sai.hirely.dto.skill.job;

import com.sai.hirely.dto.skill.CreateSkill;
import com.sai.hirely.models.enums.Proficiency;
import lombok.Getter;

@Getter
public class JobCreateSkill extends CreateSkill {
    private final boolean required;
    public JobCreateSkill(String name, Proficiency proficiency,boolean required) {
        super(name,proficiency);
        this.required = required;
    }
}
