package com.sai.hirely.exceptions.company;

public class HiringManagerNotFoundException extends RuntimeException {
    public HiringManagerNotFoundException(Long id) {
        super("Hiring Manager not found with id: " + id);
    }
}
