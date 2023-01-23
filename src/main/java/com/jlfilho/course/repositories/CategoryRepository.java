package com.jlfilho.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlfilho.course.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
