package com.learning.microservices.springboot.app.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.springboot.app.items.models.Item;
import com.learning.microservices.springboot.app.items.models.Product;
import com.learning.microservices.springboot.app.items.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
	@Autowired
	//@Qualifier("itemServiceRestTemplateImpl")
	@Qualifier("itemServiceFeingImpl")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> list() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "otherMethod")
	@GetMapping("/view/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
	
	public Item otherMethod(@PathVariable Long id, @PathVariable Integer quantity) {
		Item item = new Item();
		Product product = new Product();
		
		item.setQuantity(quantity);
		
		product.setId(id);
		product.setName("Camara Sony Del otherMethod");
		product.setPrice(500.00);
		
		item.setProduct(product);
		
		return item;
	}
}