//package com.furnifinders.backend.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//import java.util.List;
//
//
//@Data
//@Entity
//@Table(name = "cart")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int cart_id;
//
//    private Date cart_created_at;
//    private String cart_delivery_address;
//    private int cart_total_price;
//
//
//
//    List<CartDetail> cartDetails;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
