package io.js.api;

import io.js.domain.Product;
import io.js.domain.ProductEventPublisher;
import io.js.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductEventPublisher productEventPublisher;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productEventPublisher.publishProductCreatedEvent(product);
        return ResponseEntity.ok().build();
    }
 }
