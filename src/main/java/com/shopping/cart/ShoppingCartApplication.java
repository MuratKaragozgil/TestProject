package com.shopping.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Murat Karagözgil
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.shopping.cart.repository"})
@EntityScan(basePackages = {"com.shopping.cart.model"})
public class ShoppingCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
}