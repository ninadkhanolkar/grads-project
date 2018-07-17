package com.systems.wissen.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_credential database table.
 * 
 */
@Entity
@Table(name="user_credential")
@NamedQuery(name="UserCredential.findAll", query="SELECT u FROM UserCredential u")
public class UserCredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_credential_id")
	private int userCredentialId;
	
	@JoinColumn(name="emp_id")
	@OneToOne
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	private String password;

	@Column(name="role_id")
	private int roleId;

	public UserCredential() {
	}

	public int getUserCredentialId() {
		return this.userCredentialId;
	}

	public void setUserCredentialId(int userCredentialId) {
		this.userCredentialId = userCredentialId;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}