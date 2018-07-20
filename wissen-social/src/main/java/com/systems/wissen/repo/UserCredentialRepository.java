package com.systems.wissen.repo;

import com.systems.wissen.model.UserCredential;

public interface UserCredentialRepository {
	UserCredential addUserCredential(UserCredential user);
	void removeUserCredential(String employeeId);
}
