package com.furnifinders.backend.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

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

    public User(String email, String phone, String password, String first_name, String last_name, String role, Timestamp create_date, boolean confirmed_email, boolean checkbox) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.create_date = create_date;
        this.confirmed_email = confirmed_email;
        this.checkbox = checkbox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public boolean isConfirmed_email() {
        return confirmed_email;
    }

    public void setConfirmed_email(boolean confirmed_email) {
        this.confirmed_email = confirmed_email;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", role='" + role + '\'' +
                ", create_date='" + create_date + '\'' +
                ", confirmed_email=" + confirmed_email +
                ", checkbox=" + checkbox +
                '}';
    }
}
