package com.example.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) { //método buscar recebe um id do tipo integer como parâmetro
		Optional<Cliente> obj = repo.findById(id); //vai no banco de dados busca uma categoria com esse id e já retorna o obj pronto
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	} //agora o método de serviço vai lançar uma exceção caso o id não exista

}
