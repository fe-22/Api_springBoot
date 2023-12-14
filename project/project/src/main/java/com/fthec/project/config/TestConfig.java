package com.fthec.project.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fthec.project.entities.Category;
import com.fthec.project.entities.Order;
import com.fthec.project.entities.OrderItem;
import com.fthec.project.entities.Payment;
import com.fthec.project.entities.Product;
import com.fthec.project.entities.User;
import com.fthec.project.entities.enums.OrderStatus;
import com.fthec.project.repositories.CategoryRepository;
import com.fthec.project.repositories.OrderItemRepository;
import com.fthec.project.repositories.OrderRepository;
import com.fthec.project.repositories.ProductRepository;
import com.fthec.project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;//injeção dos OrderItemRepository para salvamento no banco de dados.

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		User u1 = new User(null, "Gustavo Xavier Fernandes", "gustavo@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Danilo Xavier Fernandes", "danilo@gmail.com", "977777777", "123467"); 
		User u3 = new User(null, "Priscila Xavier Fernandes", "priscila@gmail.com", "910094560", "123478");
		
		
		Order o1 = new Order(null, Instant.parse("2023-12-02T19:53:07Z"),OrderStatus.PAID, u1); //intanciação com associação, usando o padrão ISO 8601
		Order o2 = new Order(null, Instant.parse("2023-12-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2); //z no final da data indica que esta no padrão UTC.
		Order o3 = new Order(null, Instant.parse("2023-12-02T15:21:22Z"),OrderStatus.WAITING_PAYMENT, u1);
		Order o4 = new Order(null, Instant.parse("2023-11-22T15:21:22Z"),OrderStatus.SHIPPED, u3);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));//classe de configuração para salvar no banco de dados
        orderRepository.saveAll(Arrays.asList(o1,o2,o3,o4));
        
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());//item 1,pedido 1, associado ao pedido 1, quantidade 2 e preço p1. 
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());//item2,pedido 1, associado ao pedido 3, quantidade 1 e preço p3.
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
        
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));//salvamento dos itens no banco de dados
        
        Payment pay1 = new Payment(null, Instant.parse("2023-12-02T21:53:07Z"), o1);
        o1.setPayment(pay1);//associação em via dupla, pagamento com pedido.
        
        orderRepository.save(o1);
		
	}

}
