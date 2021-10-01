package com.bludots.app.rgm.password.registration.services.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.repositories.RequestRepository;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.EmailService;
import com.bludots.app.rgm.password.registration.services.RequestServices;
import com.bludots.app.rgm.password.registration.services.impl.token.WebToken;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;

@Service
@Transactional
public class RequestServiceImpl implements RequestServices {
	
	@Autowired
	private RequestRepository requestRepository;
		
	@Autowired
	EmailService email;
	
	@Override
	public Request persistRequest(RequestVO vo) {
		Request request = new Request();
		request.setEmail(vo.getEmail());
		request.setRequestDateTime(LocalDateTime.now());
		WebToken web = new WebToken();
		String token = web.generateToken(vo.getEmail());
		request.setToken(token);
		String link = "http://localhost:9800/rgm.pwd.reg/pwd?token=" + token;
		email.send(
				request.getEmail(),
				buildEmail(request.getEmail(), link));
		
		return requestRepository.save(request);
	}
	
	private String buildEmail(String email, String link) {
		
		return  "<img src = 'https://www.logo.wine/a/logo/Iamgold/Iamgold-Logo.wine.svg' style='width:600px; height:100px'><br>"
				+"Email: " + email + "<a href=" + link + " target=\\\"_blank\\\"> click this link to register</a> ";
	}

	@Override
	public Request findRequest(String token) {
		Request request = requestRepository.findByToken(token);
		return request;
	}

}


