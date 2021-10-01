package com.bludots.app.rgm.password.registration.services;

import com.bludots.app.rgm.password.registration.valueobjects.CredentialVO;

public interface CredentialService {

	public Boolean persistPasswordChange(CredentialVO vo);
}
