package com.learning.microservices.springboot.app.products.models.service;

import com.learning.microservices.springboot.app.products.Datos.ProductosData;
import com.learning.microservices.springboot.app.products.models.dao.ProductDao;
import com.learning.microservices.springboot.app.products.models.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

/* Los Test Con Ayuda De SprinBoot*/
@SpringBootTest
class ProductServiceImplementTest {

    @MockBean
    ProductDao productDao;

    @Autowired
    IProductService productService;

    @Test
    void obtenerTodosLosProductosTest() {
        // GIVEN
        Mockito.when(productDao.findAll()).thenReturn(ProductosData.productos());

        // WHEN
        List<Product> products = productService.findAll();

        // THEN
        Assertions.assertAll(
                () -> Assertions.assertNotNull(products),
                () -> Assertions.assertEquals(ProductosData.ids(), products.stream().map(Product::getId).toList()),
                () -> Assertions.assertNotNull(products.stream().map(Product::getId).toList()),
                () -> Assertions.assertNotNull(products.stream().map(Product::getName).toList()),
                () -> Assertions.assertNotNull(products.stream().map(Product::getPrice).toList()),
                () -> Assertions.assertNotNull(products.stream().map(Product::getPort).toList()),
                () -> Assertions.assertNotNull(products.stream().map(Product::getCreatedAt).toList()),
                () -> Assertions.assertEquals(3, products.size())
        );

        // VERIFY
        Mockito.verify(productDao).findAll();
    }

    @Test
    void obtenerProductoPorIdTest() {
        // GIVEN
        Mockito.when(productDao.findById(ArgumentMatchers.anyLong())).thenReturn(ProductosData.producto());

        // WHEN
        Product product = productService.findById(1L);

        // THEN
        Assertions.assertAll(
                () -> Assertions.assertNotNull(product),
                () -> Assertions.assertEquals(1L, product.getId()),
                () ->  Assertions.assertEquals("Producto Nuevo Uno", product.getName()),
                () -> Assertions.assertFalse(product.getPrice() < 4500.0),
                () -> Assertions.assertTrue(product.getPrice() > 4500.0)
        );

        // VERIFY
        Mockito.verify(productDao).findById(ArgumentMatchers.anyLong());
    }
}