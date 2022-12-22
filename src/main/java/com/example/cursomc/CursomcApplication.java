package com.example.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
//CommandLineRunner permite implementar um método auxiliar p/ executar alguma ação quando a aplicação iniciar
	
	//dependência do categoriaRepository
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) { //executar o programa
		SpringApplication.run(CursomcApplication.class, args);
	}

@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
		
		//instanciação dos objetos (categorias)
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritório");
		
		//instanciação dos objetos (produtos)
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); //diz que os produtos 1, 2 e 3 estão associados a categoria 1
		cat2.getProdutos().addAll(Arrays.asList(p2)); //diz que o produto 2 está associado a categoria 2
		
		p1.getCategorias().addAll(Arrays.asList(cat1)); //diz que a categoria 1 está associada ao produto 1
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2)); //diz que as categorias 1 e 2 estão associadas ao produto 2
		p3.getCategorias().addAll(Arrays.asList(cat1)); //diz que a categoria 1 está associada ao produto 3
		
		//salvar os objetos no banco de dados (categoriaRepository)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); //salva todas as categorias
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3)); //salva todos os produtos
		
	}

}
