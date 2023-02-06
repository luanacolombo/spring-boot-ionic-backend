package com.example.cursomc.services; //interface de serviço de e-mail

import org.springframework.mail.SimpleMailMessage;

import com.example.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);  //e-mail de confirmação de pedido
	
	void sendEmail(SimpleMailMessage msg);
}
