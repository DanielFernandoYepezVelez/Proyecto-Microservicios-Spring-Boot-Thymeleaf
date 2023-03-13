package com.learning.microservices.springboot.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.microservices.springboot.app.items.clients.ProductClientRest;
import com.learning.microservices.springboot.app.items.models.Item;

@Service("itemServiceFeingImpl")
public class ItemServiceFeingImpl implements ItemService {
	
	@Autowired
	private ProductClientRest clientFeingRest;

	@Override
	public List<Item> findAll() {
		return clientFeingRest.list().stream().map((product) -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(clientFeingRest.details(id), quantity);		
	}
	
}
