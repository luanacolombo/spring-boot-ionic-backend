package com.example.cursomc.services; //interface de serviço de e-mail

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.example.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);  //e-mail de confirmação de pedido
	
	void sendEmail(SimpleMailMessage msg); //envio de e-mail com texto plano
	
	void sendOrderConfirmationHtmlEmail(Pedido obj); //e-mail de confirmação de pedido
	
	void sendHtmlEmail(MimeMessage msg); //envio de e-mail html
}
