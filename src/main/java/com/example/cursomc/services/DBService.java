package com.example.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.domain.Estado;
import com.example.cursomc.domain.ItemPedido;
import com.example.cursomc.domain.Pagamento;
import com.example.cursomc.domain.PagamentoComBoleto;
import com.example.cursomc.domain.PagamentoComCartao;
import com.example.cursomc.domain.Pedido;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.domain.enums.EstadoPagamento;
import com.example.cursomc.domain.enums.TipoCliente;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.repositories.EstadoRepository;
import com.example.cursomc.repositories.ItemPedidoRepository;
import com.example.cursomc.repositories.PagamentoRepository;
import com.example.cursomc.repositories.PedidoRepository;
import com.example.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	//dependências
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		//instanciação dos objetos (categorias)
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
				
		//instanciação dos objetos (produtos)
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "toalho", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
				
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); //diz que os produtos 1, 2 e 3 estão associados a categoria 1
		cat2.getProdutos().addAll(Arrays.asList(p2, p4)); //diz que os produtos 2 e 4 estão associados a categoria 2
		cat3.getProdutos().addAll(Arrays.asList(p5, p6)); //diz que os produtos 5 e 6 estão associados a categoria 3
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7)); //diz que os produtos 1, 2, 3 e 7 estão associados a categoria 4
		cat5.getProdutos().addAll(Arrays.asList(p8)); //diz que o produto 8 está associado a categoria 5
		cat6.getProdutos().addAll(Arrays.asList(p9, p10)); //diz que os produtos 9 e 10 estão associados a categoria 6
		cat7.getProdutos().addAll(Arrays.asList(p11)); //diz que o produto 11 está associado a categoria 7
				
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4)); //diz que as categorias 1 e 4 estão associadas ao produto 1
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4)); //diz que as categorias 1, 2 e 4 estão associadas ao produto 2
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4)); //diz que as categorias 1 e 4 estão associadas ao produto 3
		p4.getCategorias().addAll(Arrays.asList(cat2)); //diz que a categoria 2 está associada ao produto 4
		p5.getCategorias().addAll(Arrays.asList(cat3)); //diz que a categoria 3 está associada ao produto 5
		p6.getCategorias().addAll(Arrays.asList(cat3)); //diz que a categoria 3 está associada ao produto 6
		p7.getCategorias().addAll(Arrays.asList(cat4)); //diz que a categoria 4 está associada ao produto 7
		p8.getCategorias().addAll(Arrays.asList(cat5)); //diz que a categoria 5 está associada ao produto 8
		p9.getCategorias().addAll(Arrays.asList(cat6)); //diz que a categoria 6 está associada ao produto 9
		p10.getCategorias().addAll(Arrays.asList(cat6)); //diz que a categoria 6 está associada ao produto 10
		p11.getCategorias().addAll(Arrays.asList(cat7)); //diz que a categoria 7 está associada ao produto 11
				
		//salvar os objetos no banco de dados (categoriaRepository e produtoRepository)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7)); //salva todas as categorias
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)); //salva todos os produtos
		
		//instanciação dos objetos (estado)
		Estado est1 = new Estado(null, "Minas gerais");
		Estado est2 = new Estado(null, "São Paulo");
				
		//instanciação dos objetos (cidade)
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
				
		est1.getCidades().addAll(Arrays.asList(c1)); //diz que a cidade 1 está associada ao estado 1
		est2.getCidades().addAll(Arrays.asList(c2, c3)); //diz que as cidades 2 e 3 estão associadas ao estado 2
				
		estadoRepository.saveAll(Arrays.asList(est1, est2)); //salva todos os estados
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3)); //salva todas as cidades
				
		//instanciação dos objetos (cliente)
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
				
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393")); //diz que esses telefones estão associados ao cliente 1
				
		//instanciação dos objetos (endereço)
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
				
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2)); //diz que os endereços 1 e 2 estão associados ao cliente 1
				
		clienteRepository.saveAll(Arrays.asList(cli1)); //salva todos os clientes
		enderecoRepository.saveAll(Arrays.asList(e1, e2)); //salva todos os endereços
				
		//instanciação de datas
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				
		//instanciação dos objetos (pedido)
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
				
		//instanciação dos objetos (pagamento)
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
			
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
				
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2)); //diz que os pedidos 1 e 2 estão associados ao cliente 1
				
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2)); //salva todos os pedidos
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2)); //salva todos os pagamento
				
		//instanciação dos objetos (ItemPedido)
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
				
		ped1.getItens().addAll(Arrays.asList(ip1, ip2)); //diz que os ItensPedidos 1 e 2 estão associados ao pedido 1
		ped2.getItens().addAll(Arrays.asList(ip3)); //diz que o ItemPedido 3 está associado ao pedido 2
			
		p1.getItens().addAll(Arrays.asList(ip1)); //diz que o ItemPedido 1 está associado ao produto 1
		p2.getItens().addAll(Arrays.asList(ip3)); //diz que o ItemPedido 3 está associado ao produto 2
		p3.getItens().addAll(Arrays.asList(ip2)); //diz que o ItemPedido 2 está associado ao produto 3
				
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3)); //salva todos os ItensPedidos
	}
}
