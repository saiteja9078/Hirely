package com.sai.hirely.exceptions.candidate;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role with id: "+ id+ " not found");
    }
    public RoleNotFoundException() {
        super("Roles are not valid");
    }
}
