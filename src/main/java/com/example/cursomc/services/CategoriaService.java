package com.example.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
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
	
	public void delete(Integer id) {
		find(id); //busca o id no banco, caso não exista ele lança a exceção
		try { //irá tentar executar
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) { // se der uma exceção será capturado
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos"); //exceção personalizada
		} //inserir no excepitons handler também
		
	}
	
	public List<Categoria> findAll(){ //mostra a lista das categorias
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	//paginação, Integer page p/contagem de páginas, Integer linesPerPage p/ quantas linhas de página eu quero, orderBy p/ definir a 
	//ordem de aparição, p/ definir a direção descendente ou ascendente
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); //pageRequest será um obj que irá preparar pras infos p/ ser feita a consulta, p/ retornar a página de dados
		return repo.findAll(pageRequest);
	}
	
	//método auxiliar que instancia uma categoria a partir de um DTO
	public Categoria fromDTO(CategoriaDTO objDto) { //from, a partir de um DTO vai ser construido um objeto categoria
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
