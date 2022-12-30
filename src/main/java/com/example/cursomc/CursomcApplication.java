package com.example.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
//CommandLineRunner permite implementar um método auxiliar p/ executar alguma ação quando a aplicação iniciar
	
	public static void main(String[] args) { //executar o programa
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}