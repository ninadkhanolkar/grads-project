package com.systems.wissen.web;

public class EmployeeViewResponse {

	private String firstName;
	private String lastName;
	private String id;
	private String url;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl() {
		String initUrl = "/api/wiseconnect/v1/employee/" + this.id;
		this.url = initUrl;
	}

}
