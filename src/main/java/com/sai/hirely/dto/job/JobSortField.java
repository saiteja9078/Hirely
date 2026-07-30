package com.sai.hirely.dto.job;

public enum JobSortField {
    POSTED_AT("postedAt"),
    SALARY_LOWER("salaryLower"),
    SALARY_HIGHER("salaryHigher"),
    TITLE("title"),
    EXPERIENCE("minimumExperienceInMonths");

    private final String field;

    JobSortField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}