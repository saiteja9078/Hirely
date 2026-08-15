package com.sai.hirely.apis;

import com.sai.hirely.dto.NotificationResponse;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import com.sai.hirely.service.job.JobApplicationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationApi {
    private final JobApplicationService applicationService;

    public NotificationApi(JobApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/me")
    public List<NotificationResponse> getCurrentNotifications(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return applicationService.notificationsForCandidate(user.getId());
    }
}
