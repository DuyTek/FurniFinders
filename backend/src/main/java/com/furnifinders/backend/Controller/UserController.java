package com.furnifinders.backend.Controller;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.dto.Request.AddToCartRequest;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Response.AddToCartResponse;
import com.furnifinders.backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

@CrossOrigin(origins = "http://localhost:3030", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
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
        Path staticPath = Paths.get("src/main/resources");
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

    @GetMapping("/findAllUserProducts/{id}")
    public ResponseEntity<List<Product>> findAllUserProducts(@PathVariable Long id) {
        List<Product> products = userService.findAllUserProducts(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        List<Product> products = userService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/getProductImage/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)

    public void getImage(HttpServletResponse response, @PathVariable Long id) throws IOException {
        Product product = userService.findProductById(id);
        var imgFile = new ClassPathResource(product.getProduct_image());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        AddToCartResponse addToCartResponse = userService.addToCart(addToCartRequest);
        return ResponseEntity.ok(addToCartResponse);
    }

}