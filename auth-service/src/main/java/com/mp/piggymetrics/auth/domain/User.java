package com.mp.piggymetrics.auth.domain;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

	@NotNull
	@Size(min = 3, max = 20)
	@Id
	private String username;

	@NotNull
	@Size(min = 6, max = 40)
	@Column
	private String password;
	
	@NotNull
	@Column
	private String role;

	public User() {
		
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
