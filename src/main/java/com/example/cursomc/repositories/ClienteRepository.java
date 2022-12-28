package com.example.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { //esse objeto será capaz de realizar 
	//operações de acesso a dados, como busca, salvar, alterar, deletar. Várias operações de acesso a dados referente ao 
	//objeto cliente
	@Transactional(readOnly=true) //não necessita ser envolvida com uma transação de banco de dados, fazendo ela ficar mais rápida 
	Cliente findByEmail(String email); //busca no banco de dados um cliente passando o e-mail como argumento
}
