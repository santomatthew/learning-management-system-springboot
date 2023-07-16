package com.lawencon.lmssanto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lawencon.lmssanto.service.SendMailService;


@Service
public class SendMailServiceImpl implements SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(message);

		javaMailSender.send(msg);

	}

}
