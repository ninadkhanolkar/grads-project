package com.systems.wissen.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.systems.wissen.model.Admin;
import com.systems.wissen.repo.AdminDao;
import com.systems.wissen.repo.SuperAdminRepository;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/wiseconnect/v1/admins")
public class SuperAdminController {

	@Autowired
	private SuperAdminRepository superAdminRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<AdminDao> getAllAdmins() {
		List<AdminDao> admins = this.superAdminRepository.getAdminDao();
		return admins;
	}

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public Admin post(@RequestBody Admin admin) {
		return superAdminRepository.addAdmin(admin);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void removeAdmin(@PathVariable int id) {
		this.superAdminRepository.removeAdmin(id);
	}
}
