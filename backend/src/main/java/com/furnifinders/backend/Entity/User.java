package com.furnifinders.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String first_name;
    @Column( nullable = false)
    private String last_name;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private Timestamp create_date;
    @Column(nullable = false)
    private boolean confirmed_email;
    @Column(nullable = false)
    private boolean checkbox;

    public User() {}

}
