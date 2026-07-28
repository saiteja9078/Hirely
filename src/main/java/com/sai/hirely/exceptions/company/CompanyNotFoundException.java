package com.sai.hirely.exceptions.company;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super("Company not found with id: " + id);
    }
}
