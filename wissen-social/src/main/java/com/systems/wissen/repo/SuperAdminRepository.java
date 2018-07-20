package com.systems.wissen.repo;

import java.util.List;

import com.systems.wissen.model.Admin;
import com.systems.wissen.web.AdminDTO;

public interface SuperAdminRepository {
	Admin addAdmin(Admin admin);

	void removeAdmin(int adminId);
	
	public List<AdminDTO> getAdminDTO();

}
