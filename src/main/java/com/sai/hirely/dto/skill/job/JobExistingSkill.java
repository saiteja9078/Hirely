package com.sai.hirely.dto.skill.job;

import com.sai.hirely.dto.skill.CreateSkill;
import com.sai.hirely.dto.skill.ExistingSkill;
import com.sai.hirely.models.enums.Proficiency;
import lombok.Getter;
import lombok.Setter;

@Getter
public class JobExistingSkill extends ExistingSkill {
    private final boolean required;
    public JobExistingSkill(long id, Proficiency proficiency,boolean required) {
        super(id,proficiency);
        this.required = required;
    }
}
