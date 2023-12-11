//package com.furnifinders.backend.Entity;
//
//import com.furnifinders.backend.Entity.Enum.DeliveryStatus;
//import com.furnifinders.backend.Entity.Enum.PaymentMethod;
//import com.furnifinders.backend.Entity.Enum.PaymentStatus;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name = "order")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long order_id;
//
//    private Date order_created_at;
//    private String order_delivery_address;
//    private String order_delivery_phone;
//    private String order_delivery_note;
//    private PaymentMethod order_payment_method;
//    private DeliveryStatus order_delivery_status;
//    private PaymentStatus order_payment_status;
//    private Long order_total_price;
//    private Long order_total_quantity;
//
//
//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private Cart order_cart;
//}
