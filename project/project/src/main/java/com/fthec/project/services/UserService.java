package com.fthec.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fthec.project.entities.User;
import com.fthec.project.repositories.UserRepository;
import com.fthec.project.services.exceptions.DatabaseException;
import com.fthec.project.services.exceptions.ResourceNotFoundExcepition;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){//método para retornar todos os usúarios do banco de dados, operação da camada de serviço.
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundExcepition(id));//operação para retornar usuario por id.
	}
	
	public User insert (User obj) {//operação salva um novo objeto do tipo user dentro do banco de dados.
		return repository.save(obj);
	}
	
	public void delete(Long id) {//operação que deleta o usuario por id no banco de dados.
		try {
		repository.deleteById(id);
	}catch (EmptyResultDataAccessException e) {//tipo de exceção especifica.
		throw new ResourceNotFoundExcepition(id);
	   }catch (DataIntegrityViolationException e) {//apos capturar a exceção especifica, devemos capturar qualquer outra exceção. 
		   throw new DatabaseException(e.getMessage());
	   }
	}
	
	public User update(Long id, User obj) {//atualização de usisario em banco de dados
		try {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
           throw new ResourceNotFoundExcepition(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	


}
