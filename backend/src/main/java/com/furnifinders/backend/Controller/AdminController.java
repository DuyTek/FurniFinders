package com.furnifinders.backend.Controller;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Entity.Enum.PostStatus;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Request.SearchProductsRequest;
import com.furnifinders.backend.dto.Request.UpdatePostStatusRequest;
import com.furnifinders.backend.service.FilesStorageService;
import com.furnifinders.backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    FilesStorageService storageService;

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

    @PostMapping("/postProductImage/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> postProductImage(@RequestParam MultipartFile image, @PathVariable Long id) throws Exception {
        Path staticPath = Paths.get("backend/src/main/resources");
        Path imagePath = Paths.get("image");
        if(!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }

        String originalFileName = Objects.requireNonNull(image.getOriginalFilename());
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String newFileName = id + extension;

        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(newFileName);
        try(OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        Product product = userService.addProductImage(id, imagePath.resolve(newFileName).toString());
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/getProductImage/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable Long id) throws IOException {
        Product product = userService.findProductById(id);
        var imgFile = new ClassPathResource(product.getProduct_image());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
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

        if(updatePostStatusRequest.getPostStatus().equals(PostStatus.APPROVED.toString()) ) {
            return ResponseEntity.ok(userService.updateApprovePostStatus(updatePostStatusRequest.getProduct_id()));
        }
        else {
            return ResponseEntity.ok(userService.updateRejectPostStatus(updatePostStatusRequest.getProduct_id()));
        }
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