package com.furnifinders.backend.Controller;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Request.SearchProductsRequest;
import com.furnifinders.backend.dto.Request.UpdatePostStatusRequest;
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
    public ResponseEntity<Product> postProduct(@RequestBody PostProductRequest postProductRequest) {
        Product product = userService.addProduct(postProductRequest);
        userService.addProductUserLink(product, postProductRequest.getProduct_user_id());
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findAllUserProducts/{id}")
    public ResponseEntity<List<Product>> findAllUserProducts(@PathVariable Long id) {
        List<Product> products = userService.findAllUserProducts(id);
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


    @PutMapping("/updateProductStatus")
    public ResponseEntity<Product> updateProductStatus(@RequestBody UpdatePostStatusRequest updatePostStatusRequest) {
        Product product;


        if(updatePostStatusRequest.getPostStatus().equals("APPROVED")) {
            product = userService.updateApprovePostStatus(updatePostStatusRequest.getProduct_id());
        }
        else {
            product = userService.updateRejectPostStatus(updatePostStatusRequest.getProduct_id());
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findAllApprovedProducts")
    public ResponseEntity<List<Product>> findAllApprovedProducts() {
        List<Product> products = userService.findAllApprovedProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestBody SearchProductsRequest searchProductsRequest) {
        List<Product> products = userService.searchProducts(searchProductsRequest.getKeyword());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/verifyUser/{id}")
    public ResponseEntity<User> verifyUser(@PathVariable Long id) {
        userService.verifyUser(id);
        return ResponseEntity.ok().build();
    }
}