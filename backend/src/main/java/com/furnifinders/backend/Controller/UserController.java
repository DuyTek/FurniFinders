package com.furnifinders.backend.Controller;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.ProductUserLink;
import com.furnifinders.backend.dto.PostProductRequest;
import com.furnifinders.backend.dto.RefreshTokenRequest;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }

    @PostMapping("/postProduct")
    public ResponseEntity<ProductUserLink> postProduct(@RequestBody PostProductRequest postProductRequest) {
        Product product = userService.addProduct(postProductRequest);
        ProductUserLink productUserLink = userService.addProductUserLink(product, postProductRequest);
        return ResponseEntity.ok(productUserLink);
    }

    @GetMapping("/findAllUserProducts")
    public ResponseEntity<List<Product>> findAllUserProducts(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        List<Product> products = userService.findAllUserProducts(refreshTokenRequest);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        List<Product> products = userService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
}