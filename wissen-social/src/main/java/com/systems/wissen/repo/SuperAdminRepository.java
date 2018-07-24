package com.systems.wissen.repo;

import java.util.List;

import com.systems.wissen.model.Admin;

public interface SuperAdminRepository {
	Admin addAdmin(Admin admin);

	void removeAdmin(int adminId);
	
	public List<AdminDao> getAdminDao();
	
	public Admin getAdmin(String id);

}
