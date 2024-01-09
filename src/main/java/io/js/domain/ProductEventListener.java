package io.js.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductEventListener {
    private final ProductRepository productRepository;

    @KafkaListener(topics = "products")
    public void handleProductCreatedEvent(Product product) {
        log.info("Product event received from products topic");
        productRepository.save(product);
    }
}
