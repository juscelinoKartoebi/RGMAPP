package com.bludots.app.rgm.password.registration.repositories.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Table(name="USER_")
public class User {  
	@Id
	@TableGenerator(
			name = "table", 
			table = "sequences_", 
			pkColumnName = "PK_NAME", 
			valueColumnName = "PK_VALUE", 
			initialValue = 0)
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	@Column(name = "USER_ID_")

	
	private long id;
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, roles, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles) && Objects.equals(username, other.username);
	}

	@Column(name = "EMAIL_")
	private String email;
	
	@Column(name = "USERNAME_")
	private String username;
	
	@Column(name = "PASSWORD_")
	private String password;
	
	
	@ManyToMany()
	@JoinTable(
			name = "USER_ROLE_",
			joinColumns = @JoinColumn(referencedColumnName = "USER_ID_"),
			inverseJoinColumns = @JoinColumn(referencedColumnName ="ROLE_ID_"))// not owning side, jointable annotation is owning side
	List<Role> roles;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
