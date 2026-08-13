package com.sai.hirely.service.email;

import com.sai.hirely.models.job.JobPosting;

public interface EmailService {
    void sendWelcomeEmail(String to, String name, String role);
    void sendJobPostedEmail(String to, String candidateName, JobPosting posting);
    void sendJobApplicationEmail(String to, String candidateName, String jobTitle, String companyName);
}
