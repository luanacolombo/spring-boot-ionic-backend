package com.example.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cursomc.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> { //esse objeto será capaz de realizar 
	//operações de acesso a dados, como busca, salvar, alterar, deletar. Várias operações de acesso a dados referente ao 
	//objeto pagamento

}
