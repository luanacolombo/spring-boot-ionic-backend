package com.example.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.ProdutoRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) { //método buscar recebe um id do tipo integer como parâmetro
		Optional<Produto> obj = repo.findById(id); //vai no banco de dados busca uma categoria com esse id e já retorna o obj pronto
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	} //agora o método de serviço vai lançar uma exceção caso o id não exista

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		//paginação, Integer page p/contagem de páginas, Integer linesPerPage p/ quantas linhas de página eu quero, orderBy p/ definir a 
		//ordem de aparição, p/ definir a direção descendente ou ascendente
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); //pageRequest será um obj que irá preparar pras infos p/ ser feita a consulta, p/ retornar a página de dados){
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
}
