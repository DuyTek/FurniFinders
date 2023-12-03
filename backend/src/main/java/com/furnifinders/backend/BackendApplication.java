package com.furnifinders.backend;

import com.furnifinders.backend.Entity.Role;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    public void run(String... args){
        User adminAccount = userRepository.findByRole(Role.ADMIN);

        if(null == adminAccount){
            User user = new User();

            user.setEmail("admin@gmail.com");
            user.setFirst_name("admin");
            user.setLast_name("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }


    }
}