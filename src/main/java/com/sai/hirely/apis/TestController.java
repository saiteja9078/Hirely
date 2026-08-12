package com.sai.hirely.apis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Success";
    }
    @GetMapping("/api/test")
    public String testApiEnd() {
        return  "Api end authenticated";
    }
    @GetMapping("/api/admin/test")
    public String testAdmin() {
        return "Admin endpoint testing";
    }
    @GetMapping("/api/user/test")
    public String testUser() {
        return "Testing user endpoint";
    }
    @PostMapping("/api/admin/test")
    @ResponseStatus(HttpStatus.CREATED)
    public String testPostAdmin() {
        return "Created something";
    }
}
