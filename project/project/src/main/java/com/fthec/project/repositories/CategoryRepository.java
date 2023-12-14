package com.fthec.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fthec.project.entities.Category;

public interface  CategoryRepository extends JpaRepository<Category, Long> {//heranca do jparepository, não precisa de anotação.

}
