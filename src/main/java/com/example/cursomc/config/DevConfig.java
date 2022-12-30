package com.example.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.cursomc.services.DBService;

@Configuration
@Profile("dev") //diz que essa configuração é especifica do profile de test
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}") //pega o valor da chave do apllication-dev.properties
	private String strategy; //armazena o valor da chave
	
	@Bean
	public boolean instantiateDatabase() throws ParseException { //instantiateDatabase método responsável por instanciar o banco de dados no profile de test
		
		if (!"create".equals(strategy)) { //se a variavel strategy não for igual a create
			return false; //retorna falso
		}
		 		
		dbService.instantiateTestDatabase(); //chama a instanciação DBService
		return true;
	}
	
}
