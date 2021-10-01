package com.bludots.app.rgm.password.registration.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bludots.app.rgm.password.registration.repositories.CredentialRepository;
import com.bludots.app.rgm.password.registration.repositories.entities.Credential;
import com.bludots.app.rgm.password.registration.services.CredentialService;
import com.bludots.app.rgm.password.registration.valueobjects.CredentialVO;

@Transactional
@Service
public class CredentialServiceImpl implements CredentialService {
	
	@Autowired
	private CredentialRepository credentialRepository;

	@Override
	public Boolean persistPasswordChange(CredentialVO vo) {
		Credential credential = credentialRepository.findByEmail(vo.getEmail());
		if (credential == null) {
			credential = new Credential();
			credential.setEmail(vo.getEmail());
		}
		credential.setPassword(vo.getPassword());
		credentialRepository.save(credential);
		return Boolean.TRUE;
	}

}
