package com.example.cursomc.resources;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired 
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) { 
		//encontrar uma categoria com esse id
		Optional<Categoria> obj = Optional.ofNullable(service.find(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ //@RequestBody faz o Json ser convertido para o obj java automaticamente
		obj = service.insert(obj); //obj vai ser inserido no banco de dados e o banco de dados vai atriburi um novo id pra esse obj, esse novo id será fornecido no postman para consulta
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	} //pega o novo id e fornece como argumento da uri, fromCurrentRequest() pega a uri do postman, path("/{id}") acrescenta o id, buildAndExpand(obj.getId()) atribui o valor no id, toUri() converte para uri

}
