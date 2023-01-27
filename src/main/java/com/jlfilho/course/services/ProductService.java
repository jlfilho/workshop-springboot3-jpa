package com.jlfilho.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jlfilho.course.domain.Category;
import com.jlfilho.course.domain.Product;
import com.jlfilho.course.repositories.ProductRepository;
import com.jlfilho.course.services.exceptions.DatabaseException;
import com.jlfilho.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id); 
		return product.get();
	}
	
	public Product insert(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(Long id) {
		try {
			productRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);  
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());  
		}
	}
	
	public Product update(Long id, Product product) {
		try {
			Product p = productRepository.getReferenceById(id);
			updateData(p, product);
			return productRepository.save(p);			
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);  
		}
	}

	private void updateData(Product p, Product product) {
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setImgUrl(product.getImgUrl());
		for (Category c : product.getCategories()) {
			p.getCategories().add(c);			
		}
		
	}

}
