 package com.example.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) { //e-mail de confirmação de pedido
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj); //prepara a mensagem
		sendEmail(sm); //chama o método de enviar email
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj); //esse comando define que o template vai utilizar o obj com apelido de pedido
		return templateEngine.process("email/confirmacaoPedido", context); //retorna o html na forma de string
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try { //vai tentar mandar o e-mail html normal
			MimeMessage mm = prepareMimeMessageFromPedido(obj); //prepara a mensagem
			sendHtmlEmail(mm); //chama o método de enviar email
		}
		catch (MessagingException e) { //se der algum problema manda o e-mail texto plano
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException { //throws MessagingException diz que o método pode lançar uma exceção
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail()); //e-mail destinatario
		mmh.setFrom(sender); //e-mail do remetente
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId()); //assunto do e-mail
		mmh.setSentDate(new Date(System.currentTimeMillis())); //instante do e-mail
		mmh.setText(htmlFromTemplatePedido(obj), true); //corpo do e-mail
		return mimeMessage;
	}
}
