package com.hendisantika.service;

import com.hendisantika.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-authorization
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/04/22
 * Time: 18.44
 */
@Service
public class ProductService {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public ProductService() {
        addProduct(new Product("First Product", "First Product Description"));
        addProduct(new Product("Second Product", "Second Product Description"));
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Product getProductById(long id) {
        return productMap.get(id);
    }

    public void addProduct(Product product) {
        if (productMap.values().stream().anyMatch(p -> p.getName().equals(product.getName()))) {
            throw new IllegalArgumentException(String.format("Product with name %s already exists", product.getName()));
        }

        product.setId(idCounter.incrementAndGet());
        productMap.put(product.getId(), product);
    }
}
