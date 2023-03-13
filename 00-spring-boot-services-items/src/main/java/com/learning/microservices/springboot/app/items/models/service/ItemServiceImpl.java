package com.learning.microservices.springboot.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.microservices.springboot.app.items.models.Item;
import com.learning.microservices.springboot.app.items.models.Product;

@Service("itemServiceRestTemplateImpl")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clientRest;

	@Override
	public List<Item> findAll() {
		//List<Product> products = Arrays.asList(clientRest.getForObject("http://localhost:8001/list", Product[].class));
		
		List<Product> products = Arrays.asList(clientRest.getForObject("http://products-service/list", Product[].class));
		return products.stream().map((product) -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		
		pathVariables.put("id", id.toString());
		//Product product = clientRest.getForObject("http://localhost:8001/view/{id}", Product.class, pathVariables);
		Product product = clientRest.getForObject("http://products-service/view/{id}", Product.class, pathVariables);
		
		return new Item(product, quantity);
	}
}