package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.HiringManagerRequest;
import com.sai.hirely.dto.company.HiringManagerResponse;
import com.sai.hirely.mappers.HiringManagerMapper;
import com.sai.hirely.service.company.HiringManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;

@RestController
@RequestMapping("/api/hiring-managers")
public class HiringManagerApi {
    private final HiringManagerService hiringManagerService;
    private final HiringManagerMapper hiringManagerMapper;

    @Autowired
    public HiringManagerApi(HiringManagerService hiringManagerService, HiringManagerMapper hiringManagerMapper) {
        this.hiringManagerService = hiringManagerService;
        this.hiringManagerMapper = hiringManagerMapper;
    }
    @GetMapping("/me")
    public ResponseEntity<HiringManagerResponse> getCurrentHiringManager(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.HIRING_MANAGER);
        return ResponseEntity.ok(hiringManagerMapper.toResponse(hiringManagerService.findById(user.getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HiringManagerResponse> getHiringManager(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(hiringManagerMapper.toResponse(hiringManagerService.findById(id)));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<HiringManagerResponse> updateHiringManager(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long id,
            @RequestBody HiringManagerRequest request
    ) {
        CurrentUser.require(user, AccountType.HIRING_MANAGER);
        CurrentUser.requireId(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                hiringManagerMapper.toResponse(hiringManagerService.updateHiringManager(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHiringManager(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long id) {
        CurrentUser.require(user, AccountType.HIRING_MANAGER);
        CurrentUser.requireId(user, id);
        hiringManagerService.deleteHiringManager(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
