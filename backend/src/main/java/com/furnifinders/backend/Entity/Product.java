package com.furnifinders.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.furnifinders.backend.Entity.Enum.PostStatus;
import com.furnifinders.backend.Entity.Enum.ProductStatus;

import java.util.List;


@Entity
@Data
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String product_name;

    private String product_description;

    private String product_image;

    private Long product_percentage;

    private Long product_price;


    private PostStatus product_post_status;

    private ProductStatus product_status;

    private Long product_quantity;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductUserLink> productUserLinkList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetail> cart_detailList;

}
