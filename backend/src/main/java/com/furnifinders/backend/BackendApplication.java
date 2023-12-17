package com.furnifinders.backend;

import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Repository.UserRepository;
import com.furnifinders.backend.service.EntityService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityService userEntityService;


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    public void run(String... args){
        User adminAccount = userEntityService.findUserByRole(Role.ADMIN);

        if(null == adminAccount){
            User user = new User();
            user.setUser_email("admin@gmail.com");
            user.setUser_first_name("admin");
            user.setUser_last_name("admin");
            user.setUser_phone("1234567890");
            user.setUser_role(Role.ADMIN);
            user.setUser_password(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }


    }
}