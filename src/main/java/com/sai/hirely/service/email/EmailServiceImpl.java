package com.sai.hirely.service.email;

import com.sai.hirely.models.job.JobPosting;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendWelcomeEmail(String to, String name, String role) {
        String subject = "Welcome to Hirely!";
        String htmlBody = buildHtmlBody(
                "Welcome to Hirely!",
                "Hi " + name + ",",
                "We are thrilled to have you on board as a <strong>" + role + "</strong>! Hirely is dedicated to connecting top talent with amazing opportunities.",
                "Explore Dashboard",
                "#"
        );
        sendHtmlEmail(to, subject, htmlBody);
    }

    @Override
    @Async
    public void sendJobPostedEmail(String to, String candidateName, JobPosting posting) {
        String subject = "New Job Match: " + posting.getTitle();
        String htmlBody = buildHtmlBody(
                "New Job Posted Matching Your Interests",
                "Hello " + candidateName + ",",
                "A new job for <strong>" + posting.getTitle() + "</strong> has been posted by <strong>" + posting.getCompany().getName() + "</strong>. " +
                        "Since this matches your role preferences, we wanted to let you know right away!",
                "View Job Details",
                "#"
        );
        sendHtmlEmail(to, subject, htmlBody);
    }

    @Override
    @Async
    public void sendJobApplicationEmail(String to, String candidateName, String jobTitle, String companyName) {
        String subject = "Application Received: " + jobTitle;
        String htmlBody = buildHtmlBody(
                "Application Successful",
                "Hi " + candidateName + ",",
                "Your application for the <strong>" + jobTitle + "</strong> position at <strong>" + companyName + "</strong> has been received successfully. " +
                        "The hiring team will review your profile and get back to you.",
                "View My Applications",
                "#"
        );
        sendHtmlEmail(to, subject, htmlBody);
    }

    private void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            helper.setFrom("noreply@hirely.com");

            mailSender.send(message);
        } catch (MessagingException e) {
            // In a real application, you might want to log this or handle it appropriately
            System.err.println("Failed to send email to " + to + ": " + e.getMessage());
        } catch (Exception e) {
             System.err.println("Email configuration error or not configured: " + e.getMessage());
        }
    }

    private String buildHtmlBody(String header, String greeting, String message, String btnText, String btnLink) {
        return "<!DOCTYPE html>" +
                "<html><head><style>" +
                "body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #f9f9f9; padding: 20px; color: #333333; }" +
                ".container { background-color: #ffffff; max-width: 600px; margin: 0 auto; padding: 40px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); border: 1px solid #eaeaea; }" +
                "h2 { color: #1a1a1a; margin-top: 0; }" +
                "p { font-size: 16px; line-height: 1.6; color: #555555; }" +
                ".button { display: inline-block; padding: 12px 24px; margin-top: 20px; background-color: #000000; color: #ffffff !important; text-decoration: none; border-radius: 4px; font-weight: bold; font-size: 14px; }" +
                ".footer { margin-top: 40px; padding-top: 20px; border-top: 1px solid #eaeaea; font-size: 12px; color: #999999; text-align: center; }" +
                "</style></head><body>" +
                "<div class='container'>" +
                "<h2>" + header + "</h2>" +
                "<p>" + greeting + "</p>" +
                "<p>" + message + "</p>" +
                "<a href='" + btnLink + "' class='button'>" + btnText + "</a>" +
                "<div class='footer'>&copy; " + java.time.Year.now().getValue() + " Hirely. All rights reserved.</div>" +
                "</div>" +
                "</body></html>";
    }
}
