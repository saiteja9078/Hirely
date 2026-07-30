package com.sai.hirely.exceptions.company;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String name, Object id) {
        super(name + " not found with id: " + id);
    }
}
