package com.furnifinders.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnifinders.backend.Entity.Enum.UserType;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name= "product_user_link")
public class ProductUserLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_user_link_id")
    private Long ProductUserLinkID;

    @Column(name = "product_user_link_user_type")
    private UserType userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "product_user_link_user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "product_user_link_product_id")
    private Product product;
}
