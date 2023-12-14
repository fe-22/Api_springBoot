package com.fthec.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fthec.project.entities.Order;

public interface  OrderRepository extends JpaRepository<Order, Long> {//heranca do jparepository, não precisa de anotação.

}
