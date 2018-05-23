package com.marcus.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcus.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
