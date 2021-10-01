package com.bludots.app.rgm.password.registration.repositories.entities;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="REQUEST_")
public class Request {  
	@Id
	@TableGenerator(
			name = "table", 
			table = "sequences_", 
			pkColumnName = "PK_NAME", 
			valueColumnName = "PK_VALUE", 
			initialValue = 0)
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	@Column(name = "ID_")
	private long id;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "EMAIL_")
	private String email;
	
	@Column(name = "TOKEN_")
	private String token;
	
	@Column(name = "REQUEST_DATE_TIME_")
	private LocalDateTime requestDateTime;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((requestDateTime == null) ? 0 : requestDateTime.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (requestDateTime == null) {
			if (other.requestDateTime != null)
				return false;
		} else if (!requestDateTime.equals(other.requestDateTime))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	public long getId() {
		return id;
	}
        public void setId(Long id) {
        	this.id = id;
        
			
		
		}     
       
}
