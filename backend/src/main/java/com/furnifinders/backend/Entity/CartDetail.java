package com.furnifinders.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cartDetail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetail_id;

    private Long cartDetail_quantity;
    private Long cartDetail_total_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cartDetail_cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cartDetail_product_id")
    private Product product;

}
