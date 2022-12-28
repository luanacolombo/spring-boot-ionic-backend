package com.example.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.domain.Produto;
import com.example.cursomc.dto.ProdutoDTO;
import com.example.cursomc.resources.utils.URL;
import com.example.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired 
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Optional<Produto>> find(@PathVariable Integer id) { 
		//encontrar uma categoria com esse id
		Optional<Produto> obj = Optional.ofNullable(service.find(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET) //retorna página com todas as categorias
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="0") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, //se não informar o parâmetro da página, automaticamente vai p/ 1º página
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, //informa por qual campo quer ordenar
			@RequestParam(value="direction", defaultValue="ASC") String direction) { //ASC ascendente, DESC descendente
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj)); //map irá efetuar uma 
		//operação p/ cada elemento da página, -> função anonima que recebe um obj com o new 
		//passando o obj como argumento. Convertendo cada obj da lista para um DTO
		return ResponseEntity.ok().body(listDto);
	}

}
