 package com.example.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.example.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) { //e-mail de confirmação de pedido
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); //destinatário do e-mail
		sm.setFrom(sender); //remetente do e-mail
		sm.setSubject("Pedido confirmado! Código: " + obj.getId()); //assunto do e-mail
		sm.setSentDate(new Date(System.currentTimeMillis())); //data de acordo com o servidor
		sm.setText(obj.toString()); //corpo do e-mail
		return sm;
	}
}
