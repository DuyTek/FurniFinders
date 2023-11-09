package com.furnifinders.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
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

    public User(String email, String phone, String password, String first_name, String last_name, String role, boolean confirmed_email, boolean checkbox) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.create_date = Timestamp.valueOf(sdf.format(System.currentTimeMillis()));
        this.confirmed_email = confirmed_email;
        this.checkbox = checkbox;
    }
}
