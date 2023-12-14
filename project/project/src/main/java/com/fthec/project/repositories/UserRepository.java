package com.fthec.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fthec.project.entities.User;

public interface  UserRepository extends JpaRepository<User, Long> {//heranca do jparepository, não precisa de anotação.

}
