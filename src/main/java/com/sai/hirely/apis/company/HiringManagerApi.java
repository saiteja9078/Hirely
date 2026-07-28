package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.HiringManagerRequest;
import com.sai.hirely.dto.company.HiringManagerResponse;
import com.sai.hirely.mappers.HiringManagerMapper;
import com.sai.hirely.service.company.HiringManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hiring-managers")
public class HiringManagerApi {
    private final HiringManagerService hiringManagerService;
    private final HiringManagerMapper hiringManagerMapper;

    @Autowired
    public HiringManagerApi(HiringManagerService hiringManagerService, HiringManagerMapper hiringManagerMapper) {
        this.hiringManagerService = hiringManagerService;
        this.hiringManagerMapper = hiringManagerMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HiringManagerResponse> getHiringManager(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(hiringManagerMapper.toResponse(hiringManagerService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HiringManagerResponse> addHiringManager(@RequestBody HiringManagerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        hiringManagerMapper.toResponse(
                                hiringManagerService.addHiringManager(
                                        hiringManagerMapper.toEntity(request),
                                        request.departmentId()
                                )
                        )
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HiringManagerResponse> updateHiringManager(
            @PathVariable Long id,
            @RequestBody HiringManagerRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                hiringManagerMapper.toResponse(hiringManagerService.updateHiringManager(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHiringManager(@PathVariable Long id) {
        hiringManagerService.deleteHiringManager(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
