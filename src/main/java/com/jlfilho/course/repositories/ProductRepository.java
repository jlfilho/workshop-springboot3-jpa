package com.jlfilho.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlfilho.course.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
