package com.example.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) { //método buscar recebe um id do tipo integer como parâmetro
		Optional<Categoria> obj = repo.findById(id); //vai no banco de dados busca uma categoria com esse id e já retorna o obj pronto
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	} //agora o método de serviço vai lançar uma exceção caso o id não exista

	public Categoria insert(Categoria obj) { //inserir nova no postman
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) { //atualizar no postman
		find(obj.getId()); //busca o obj no banco, caso não exista ele lança a exceção
		return repo.save(obj);
	}
	
}
