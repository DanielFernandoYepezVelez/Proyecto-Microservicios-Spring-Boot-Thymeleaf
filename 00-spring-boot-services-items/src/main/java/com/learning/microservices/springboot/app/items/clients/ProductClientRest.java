package com.learning.microservices.springboot.app.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.microservices.springboot.app.items.models.Product;

@FeignClient(name = "products-service")
//@FeignClient(name = "products-service", url = "localhost:8001")
public interface ProductClientRest {
	
	/* Aquí Obtengo El JSON Que Me Retorna El Microservicio Y Con Feing, Se Convierten En Objetos */
	
	@GetMapping("/list")
	public List<Product> list();
	
	@GetMapping("/view/{id}")
	public Product details(@PathVariable Long id);

}
