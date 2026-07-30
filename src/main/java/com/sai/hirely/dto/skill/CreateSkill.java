package com.sai.hirely.dto.skill;

import com.sai.hirely.models.enums.Proficiency;
import lombok.Getter;

@Getter
public class CreateSkill{
        private final String name;
        private final Proficiency proficiency;
        public CreateSkill(String name,Proficiency proficiency) {
            this.name = name;
            this.proficiency = proficiency;
        }
}
