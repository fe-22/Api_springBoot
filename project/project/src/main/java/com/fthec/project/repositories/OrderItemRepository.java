package com.fthec.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fthec.project.entities.OrderItem;

public interface  OrderItemRepository extends JpaRepository<OrderItem, Long> {//heranca do jparepository, não precisa de anotação.

}//classe criada para salvar os itens em banco de dados.
