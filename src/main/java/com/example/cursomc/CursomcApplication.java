package com.example.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
//CommandLineRunner permite implementar um método auxiliar p/ executar alguma ação quando a aplicação iniciar
	
	//dependência do categoriaRepository
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) { //executar o programa
		SpringApplication.run(CursomcApplication.class, args);
	}

@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
		
		//instanciação dos objetos
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritório");
		
		//salvar os objetos no banco de dados (categoriaRepository)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
