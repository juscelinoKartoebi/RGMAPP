package com.bludots.app.rgm.password.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bludots.app.rgm.password.registration.repositories.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
