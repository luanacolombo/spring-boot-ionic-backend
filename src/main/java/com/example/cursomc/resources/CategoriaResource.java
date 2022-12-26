package com.example.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired 
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Optional<Categoria>> find(@PathVariable Integer id) { 
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

	@RequestMapping(value="/{id}", method=RequestMethod.PUT) //atualizar no postman
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) //deleta no postman
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET) //retorna lista de todas as categorias
	public ResponseEntity<List<CategoriaDTO>> findAll() { 
		//encontrar uma categoria com esse id
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); //stream p/ 
		//percorrer a lista, map irá efetuar uma operação p/ cada elemento da lista, -> função anonima que recebe um obj com o new 
		//passando o obj como argumento. Com isso, passar o stream de obj para o tipo lista usando o collect, que em apenas uma linha
		//converte uma lista para outra lista
		return ResponseEntity.ok().body(listDto);
	}
	
}
