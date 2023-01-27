package com.jlfilho.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jlfilho.course.domain.Product;
import com.jlfilho.course.domain.User;
import com.jlfilho.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Product product = productService.findById(id);
		
		return ResponseEntity.ok().body(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = productService.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
		product = productService.update(id, product);
		return ResponseEntity.ok().body(product);
	}

}