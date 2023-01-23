package com.jlfilho.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jlfilho.course.domain.Category;
import com.jlfilho.course.domain.Order;
import com.jlfilho.course.domain.Product;
import com.jlfilho.course.domain.User;
import com.jlfilho.course.domain.enums.OrderStatus;
import com.jlfilho.course.repositories.CategoryRepository;
import com.jlfilho.course.repositories.OrderRepository;
import com.jlfilho.course.repositories.ProductRepository;
import com.jlfilho.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		userRepository.saveAll(Arrays.asList(u1,u2));
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lords of Rings", "Lorem ipsum dolor sit amet, consectetur", 90.50, " ", cat2);
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet. Maecena ante", 2190.00, " ", cat1);
		p2.getCategories().add(cat3);
		Product p3 = new Product(null, "MacBook Pro", "Nam eleifend maximus tortor, at molis", 1250.00, " ", cat3);
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus", 1200.00, " ", cat3);
		Product p5 = new Product(null, "Rail of Dumies", "Cras fringilla convallis sem vel fauccibus", 100.99, " ", cat2);
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
	}
	
	

}
