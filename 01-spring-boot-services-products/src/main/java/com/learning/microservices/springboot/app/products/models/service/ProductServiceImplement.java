package com.learning.microservices.springboot.app.products.models.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.microservices.springboot.app.products.models.dao.ProductDao;
import com.learning.microservices.springboot.app.products.models.entity.Product;

@Service
public class ProductServiceImplement implements IProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		List<Product> products = (List<Product>) productDao.findAll();

		products.stream().peek(product -> {
			product.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
			product.setPort(port);
		}).toList();

		return products;
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {		
		return productDao.findById(id).orElse(null);
	}
}