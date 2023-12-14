package com.fthec.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fthec.project.entities.Product;

public interface  ProductRepository extends JpaRepository<Product, Long> {//heranca do jparepository, não precisa de anotação.

}
