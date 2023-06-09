package com.learning.microservices.springboot.app.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.springboot.app.products.models.entity.Product;
import com.learning.microservices.springboot.app.products.models.service.IProductService;

@RestController
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@GetMapping("/list")
	public List<Product> list() {
		return productService.findAll();
	}
	
	@GetMapping("/view/{id}")
	public Product details(@PathVariable Long id) {
		Product product = productService.findById(id);
		//product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//product.setPort(port);
		
		/* try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} */
		
		return product;
	}
	
}