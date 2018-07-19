package com.systems.wissen.repo;

import java.util.List;

import com.systems.wissen.model.Admin;
import com.systems.wissen.model.Employee;
import com.systems.wissen.web.ResponseObject;

public interface SuperAdminRepository {
	Admin addAdmin(Admin admin);

	void removeAdmin(String adminId);

	List<Admin> getAllAdmins();

}
