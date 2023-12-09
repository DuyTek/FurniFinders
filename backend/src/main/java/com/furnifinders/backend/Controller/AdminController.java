package com.furnifinders.backend.Controller;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Request.RefreshTokenRequest;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3030", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi Admin");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/postProduct")
    public ResponseEntity<Product> postProduct(@RequestBody PostProductRequest postProductRequest, @RequestBody RefreshTokenRequest refreshTokenRequest) {
        Product product = userService.addProduct(postProductRequest);
        userService.addProductUserLink(product, refreshTokenRequest);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findAllUserProducts")
    public ResponseEntity<List<Product>> findAllUserProducts(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        List<Product> products = userService.findAllUserProducts(refreshTokenRequest);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findAllProducts")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = userService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        List<Product> products = userService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/updateApprovePostStatus/{id}")
    public ResponseEntity<Product> updateApproveProductStatus(@PathVariable Long id) {
        Product product = userService.updateApprovePostStatus(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateRejectPostStatus/{id}")
    public ResponseEntity<Product> updateRejectProductStatus(@PathVariable Long id) {
        Product product = userService.updateRejectPostStatus(id);
        return ResponseEntity.ok(product);
    }


}