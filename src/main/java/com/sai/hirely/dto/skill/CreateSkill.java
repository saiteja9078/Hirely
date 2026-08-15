package com.sai.hirely.dto.skill;

import com.sai.hirely.models.enums.Proficiency;
import lombok.Getter;

@Getter
public class CreateSkill {
    private String name;
    private Proficiency proficiency;

    public CreateSkill() {}

    public CreateSkill(String name, Proficiency proficiency) {
        this.name = name;
        this.proficiency = proficiency;
    }
}
