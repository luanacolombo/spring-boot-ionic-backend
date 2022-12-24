package com.example.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cursomc.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> { //esse objeto será capaz de realizar 
	//operações de acesso a dados, como busca, salvar, alterar, deletar. Várias operações de acesso a dados referente ao 
	//objeto ItemPedido

}
