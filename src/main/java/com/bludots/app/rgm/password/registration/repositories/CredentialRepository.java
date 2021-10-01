package com.bludots.app.rgm.password.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bludots.app.rgm.password.registration.repositories.entities.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
	public Credential findByEmail(String email);

}
