package com.example.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.cursomc.services.DBService;
import com.example.cursomc.services.EmailService;
import com.example.cursomc.services.MockEmailService;

@Configuration
@Profile("test") //diz que essa configuração é especifica do profile de test
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException { //instantiateDatabase método responsável por instanciar o banco de dados no profile de test
		dbService.instantiateTestDatabase(); //chama a instanciação DBService
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
