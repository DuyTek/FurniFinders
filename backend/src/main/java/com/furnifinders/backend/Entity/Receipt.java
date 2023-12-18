package com.furnifinders.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnifinders.backend.Entity.Enum.DeliveryStatus;
import com.furnifinders.backend.Entity.Enum.PaymentMethod;
import com.furnifinders.backend.Entity.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Table(name = "receipt")
@Getter
@Setter
@NoArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receipt_id;

    private Date receipt_created_date;
    private String receipt_delivery_address;
    private String receipt_delivery_phone;
    private String receipt_delivery_note;
    private PaymentMethod receipt_payment_method;
    private DeliveryStatus receipt_delivery_status;
    private PaymentStatus receipt_payment_status;
    private Long receipt_total_price;
    private Long receipt_total_quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "receipt_cart_id")
    private Cart cart;

}
