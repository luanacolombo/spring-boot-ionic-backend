package com.example.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) { //método buscar recebe um id do tipo integer como parâmetro
		Optional<Cliente> obj = repo.findById(id); //vai no banco de dados busca um cliente com esse id e já retorna o obj pronto
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	} //agora o método de serviço vai lançar uma exceção caso o id não exista

	public Cliente update(Cliente obj) { //atualizar no postman
		Cliente newObj = find(obj.getId()); //busca o obj no banco, caso não exista ele lança a exceção
		updateData(newObj, obj); //atualiza os dados do nosso obj com base no obj que veio como argumento
		return repo.save(newObj); //então retorna o novo obj
	}
	
	public void delete(Integer id) {
		find(id); //busca o id no banco, caso não exista ele lança a exceção
		try { //irá tentar executar
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) { // se der uma exceção será capturado
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas"); //exceção personalizada
		} //inserir no excepitons handler também
		
	}
	
	public List<Cliente> findAll(){ //mostra a lista das clientes
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	//paginação, Integer page p/contagem de páginas, Integer linesPerPage p/ quantas linhas de página eu quero, orderBy p/ definir a 
	//ordem de aparição, p/ definir a direção descendente ou ascendente
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); //pageRequest será um obj que irá preparar pras infos p/ ser feita a consulta, p/ retornar a página de dados
		return repo.findAll(pageRequest);
	}
	
	//método auxiliar que instancia um cliente a partir de um DTO
	public Cliente fromDTO(ClienteDTO objDto) { //from, a partir de um DTO vai ser construido um objeto cliente
		//throw new UnsupportedOperationException(); //essa opção retorna uma exceção de um método não implementado
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null); //cpf/cnpj e tipo do cliente serão nulos, pois não tem o dado
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	} //newObj que foi buscado do banco de dados com todos os dados, ele foi atualizado p/ os novos valores fornecidos no obj, feito isso ele salva o newObj
	
}
