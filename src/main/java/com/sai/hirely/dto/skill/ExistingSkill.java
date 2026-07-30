package com.sai.hirely.dto.skill;

import com.sai.hirely.models.enums.Proficiency;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ExistingSkill{
        private final Long id;
        private final Proficiency proficiency;
        public ExistingSkill(Long id,Proficiency proficiency) {
            this.id = id;
            this.proficiency = proficiency;
        }
}
