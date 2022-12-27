package com.example.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired 
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Optional<Cliente>> find(@PathVariable Integer id) { 
		//encontrar um cliente com esse id
		Optional<Cliente> obj = Optional.ofNullable(service.find(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){ //@RequestBody faz o Json ser convertido para o obj java automaticamente
		Cliente obj = service.fromDTO(objDTO); //converte um objDTO p/ um obj entity
		obj = service.insert(obj); //obj vai ser inserido no banco de dados e o banco de dados vai atriburi um novo id pra esse obj, esse novo id será fornecido no postman para consulta
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	} //pega o novo id e fornece como argumento da uri, fromCurrentRequest() pega a uri do postman, path("/{id}") acrescenta o id, buildAndExpand(obj.getId()) atribui o valor no id, toUri() converte para uri

	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) //atualizar no postman
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto); //converte um objDTO p/ um obj entity
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) //deleta no postman
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET) //retorna lista de todos os clientes
	public ResponseEntity<List<ClienteDTO>> findAll() { 
		//encontrar um cliente com esse id
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); //stream p/ 
		//percorrer a lista, map irá efetuar uma operação p/ cada elemento da lista, -> função anonima que recebe um obj com o new 
		//passando o obj como argumento. Com isso, passar o stream de obj para o tipo lista usando o collect, que em apenas uma linha
		//converte uma lista para outra lista
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET) //retorna página com todas os clientes
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, //se não informar o parâmetro da página, automaticamente vai p/ 1º página
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, //informa por qual campo quer ordenar
			@RequestParam(value="direction", defaultValue="ASC") String direction) { //ASC ascendente, DESC descendente
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); //map irá efetuar uma 
		//operação p/ cada elemento da página, -> função anonima que recebe um obj com o new 
		//passando o obj como argumento. Convertendo cada obj da lista para um DTO
		return ResponseEntity.ok().body(listDto);
	}

}
