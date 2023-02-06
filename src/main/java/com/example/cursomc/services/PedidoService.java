package com.example.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursomc.domain.ItemPedido;
import com.example.cursomc.domain.PagamentoComBoleto;
import com.example.cursomc.domain.Pedido;
import com.example.cursomc.domain.enums.EstadoPagamento;
import com.example.cursomc.repositories.ItemPedidoRepository;
import com.example.cursomc.repositories.PagamentoRepository;
import com.example.cursomc.repositories.PedidoRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) { //método buscar recebe um id do tipo integer como parâmetro
		Optional<Pedido> obj = repo.findById(id); //vai no banco de dados busca uma categoria com esse id e já retorna o obj pronto
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	} //agora o método de serviço vai lançar uma exceção caso o id não exista

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null); //p/ inserir um novo pedido
		obj.setInstante(new Date()); //cria uma nova data com o instante atual
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj); //associação de mão dupla, o pagamento tem que conhecer o pedido
		if (obj.getPagamento() instanceof PagamentoComBoleto) { //se o pagamento for do tipo com boleto
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento(); //irá gerar uma data para pagamento
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante()); //irá preencher a data de vencimento 
		}
		obj = repo.save(obj); //salva o pedido
		pagamentoRepository.save(obj.getPagamento()); //salva o pagamento 	
		for (ItemPedido ip : obj.getItens()) { //percorre todos os itens de pedido associados ao obj 
			ip.setDesconto(0.0); //passa o desconto
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco()); //busca o produto no banco e depois o preço, seta o preço do pedido com o do produto
			ip.setPedido(obj); 
		}
		itemPedidoRepository.saveAll(obj.getItens()); //salva o pedido
		System.out.println(obj);
		return obj;
	}
	
}
