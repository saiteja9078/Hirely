package com.sai.hirely.apis;

// import com.sai.hirely.utils.PasswordMigrator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    // private final PasswordMigrator passwordMigrator;
    public TestController() {
    }
    @GetMapping("/migrate-pass")
    public String migrate() throws Exception {
        // passwordMigrator.run();
        return "successs";
    }
}
