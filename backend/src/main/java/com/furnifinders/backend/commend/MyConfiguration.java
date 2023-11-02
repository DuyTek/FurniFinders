package com.furnifinders.backend.commend;

import com.furnifinders.backend.DAO.userDAOImpl;
import com.furnifinders.backend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Configuration
public class MyConfiguration {
    @Bean
    @Autowired
    public CommandLineRunner getRunner(userDAOImpl userDAOImplObject) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            while(true){
                printMenu();
                int select = scanner.nextInt();
                scanner.nextLine();
                switch (select){
                    case 1:
                        addUser(userDAOImplObject);
                        break;
                    case 2:
                        getUser(userDAOImplObject);
                        System.out.println("Find user");
                        break;
                    case 3:
                        System.out.println("Remove user");
                        break;
                    default:
                        System.out.println("Invalid select");
                        break;
                }
            }
        };
    }

    public void printMenu(){
        System.out.println("============================================\n");
        System.out.println("MENU:\n"+
                "1. Add user\n"+
                "2. Find user\n"+
                "3. Remove user\n"+
                "Select: \n"
        );
    }

    public void addUser(userDAOImpl userDAOImplObject) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Phone: ");
        String phone = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("First name: ");
        String first_name = scanner.nextLine();
        System.out.println("Last name: ");
        String last_name = scanner.nextLine();
        System.out.println("Role: ");
        String role = scanner.nextLine();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse("2022-01-01 10:20:30.000");
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        User user = new User(email, phone, password, first_name, last_name, role, timestamp, false, false);
        userDAOImplObject.addUser(user);
    }

    public void getUser(userDAOImpl userDAOImplObject) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID: ");
        int id = scanner.nextInt();
        User user = userDAOImplObject.getUser(id);
        if(user == null) {
            System.out.println("User not found");
        }
        else {
            System.out.println(user);
        }
    }
}
