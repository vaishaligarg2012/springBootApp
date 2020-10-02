package com.mytask.app.model;

import java.util.List;

public class JwtResponse {

	
	private String token;
	private String type;
	private long id;
	private String username;
	private String email;
	private List<String> roles;

	
	public JwtResponse(String jwt, Long id2, String username2, String email2, List<String> roles2) {
		// TODO Auto-generated constructor stub
		this.token = jwt;
		this.id = id2;
		this.username = username2;
		this.email = email2;
		this.roles=roles2;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public  List<String> getRoles() {
		return roles;
	}


	public void setRoles( List<String> roles) {
		this.roles = roles;
	}



}