package com.learning.microservices.springboot.app.products.Datos;

import com.learning.microservices.springboot.app.products.models.entity.Product;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductosData {

    public static List<Long> ids() {
        return Arrays.asList(1L, 2L, 3L);
    }

    public static Optional<Product> producto() {
        Product product = new Product(1L, "Producto Nuevo Uno", 5000.0, new Date(), 2200);
        return Optional.of(product);
    }

    public static List<Product> productos() {
        return Arrays.asList(
                new Product(1L, "Producto Uno", 25000.0, new Date(), 2200),
                new Product(2L, "Producto Dos", 22000.0, new Date(), 8000),
                new Product(3L, "Producto Tres", 89000.0, new Date(), 9011)
        );
    }
}
