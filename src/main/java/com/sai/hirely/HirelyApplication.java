package com.sai.hirely;

// import com.sai.hirely.utils.PasswordMigrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class HirelyApplication {
    // @Autowired
    // private PasswordMigrator migrator;
    public static void main(String[] args) {
        SpringApplication.run(HirelyApplication.class, args);
    }
}
