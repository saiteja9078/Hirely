package com.sai.hirely.dto.skill;

import com.sai.hirely.models.enums.Proficiency;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ExistingSkill {
    private Long id;
    private Proficiency proficiency;

    public ExistingSkill() {}

    public ExistingSkill(Long id, Proficiency proficiency) {
        this.id = id;
        this.proficiency = proficiency;
    }
}
