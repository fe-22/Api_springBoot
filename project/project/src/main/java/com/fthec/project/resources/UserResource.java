package com.fthec.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fthec.project.entities.User;
import com.fthec.project.services.UserService;

//camada de recurso, controlador rest, depende da camanda de seviço que depende da camada de dados.
@RestController
@RequestMapping(value = "/users")//controlador rest que responde no caminho Users.
public class UserResource {
	
	@Autowired
	private UserService service;//dependencia para a camada de serviço.
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){//metodo que e um endpoint para acessar todos os usuario.
		List<User> list = service.findAll();//dentro da operação, chamada findAll.
		    
		    return ResponseEntity.ok().body(list);
		
		
	}
	
	@GetMapping(value = "/{id}")//indica que a requisição aceita um id dentro da url,busca apenas um usuario por id.
	public ResponseEntity<User> findById(@PathVariable Long id){//anotação dentro do metodo, para que o spring aceite o id.
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){//iserção de um objeto ao banco de dados,(@postMapping).
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){//delete de usuario.
		service.delete(id);
		return ResponseEntity.noContent().build();//retorna um corpo vazio no código 204
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
