package com.systems.wissen.web;

import com.systems.wissen.model.Role;

public class AdminDTO {

	private String id;

	private int role;

	public AdminDTO() {

	}

	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
