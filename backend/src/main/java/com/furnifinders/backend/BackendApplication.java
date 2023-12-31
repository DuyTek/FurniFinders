package com.furnifinders.backend;

import com.furnifinders.backend.Entity.Enum.Gender;
import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.Enum.UserVerify;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Repository.UserRepository;
import com.furnifinders.backend.service.EntityService.UserEntityService;
import com.furnifinders.backend.service.FilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityService userEntityService;

    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    public void run(String... args){
        storageService.init();
        User adminAccount = userEntityService.findUserByRole(Role.ADMIN);

        if(null == adminAccount){
            User user = new User();
            user.setUser_email("admin@gmail.com");
            user.setUser_first_name("admin");
            user.setUser_last_name("admin");
            user.setUser_phone("1234567890");
            user.setUser_role(Role.ADMIN);
            user.setUser_verified(UserVerify.YES);
            user.setUser_address("23 Nguyen Thi Minh Khai, District 1, Ho Chi Minh City");
            user.setUser_gender(Gender.MALE);
            user.setUser_dob(LocalDate.parse("1999-01-01"));
            user.setUser_password(new BCryptPasswordEncoder().encode("P@ssw0rd1234"));
            userRepository.save(user);
        }


    }
}