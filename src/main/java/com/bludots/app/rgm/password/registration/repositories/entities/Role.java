package com.bludots.app.rgm.password.registration.repositories.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="ROLE_")
public class Role { 
	@Id
	@TableGenerator(
			name = "table", 
			table = "sequences_", 
			pkColumnName = "PK_NAME", 
			valueColumnName = "PK_VALUE", 
			initialValue = 0)
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	@Column(name = "ROLE_ID_")
	private long id;
	
	@Column(name = "ROLE_TYPE_")
	private String roleType;
	
	@Column(name = "USER_ID_")
	@ManyToMany(mappedBy = "roles")
	List<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleType, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return id == other.id && Objects.equals(roleType, other.roleType) && Objects.equals(users, other.users);
	}
	
}
