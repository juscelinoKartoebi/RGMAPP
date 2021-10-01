package com.bludots.app.rgm.password.registration.services.impl;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.services.EmailService;




@Service
public class EmailServiceImpl implements EmailService{

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	 @Autowired
	private JavaMailSender mailSender;

	@Override
	public void send(String to, String email) {
		// TODO Auto-generated method stub
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("hello@email.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("failed to send email", e);
			throw new IllegalStateException("failed to send email");
		}
	}

}
