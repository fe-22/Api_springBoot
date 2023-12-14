package com.fthec.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fthec.project.entities.Category;
import com.fthec.project.services.CategoryService;
//camada de recurso, controlador rest, depende da camanda de seviço que depende da camada de dados.
@RestController
@RequestMapping(value = "/categories")//controlador rest que responde no caminho Categorys
public class CategoryResource {
	
	@Autowired
	private CategoryService service;//dependencia para a camada de serviço.
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){//metodo que e um endpoint para acessar o usuario.
		List<Category> list = service.findAll();//dentro da operação, chamada findAll.
		    
		    return ResponseEntity.ok().body(list);
		
		
	}
	
	@GetMapping(value = "/{id}")//indica que a requisição aceita um id dentro da url
	public ResponseEntity<Category> findById(@PathVariable Long id){//anotação dentro do metodo, para que o spring aceite o id.
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
