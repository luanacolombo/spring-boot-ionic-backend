package com.example.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> buscar(Integer id) { //método buscar recebe um id do tipo integer como parâmetro
		Optional<Categoria> obj = repo.findById(id); //vai no banco de dados busca uma categoria com esse id e já retorna o obj pronto
		return obj;
	}
}
