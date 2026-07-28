package com.sai.hirely.exceptions.candidate;

public class SkillNotFoundException extends RuntimeException {
    public SkillNotFoundException(Long id) {
        super("Skill not found with id: "+id);
    }
    public SkillNotFoundException(String name) {
        super("Skill: "+name+ "not found" );
    }
}
