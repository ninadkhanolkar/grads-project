package com.systems.wissen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.systems.wissen.jwt.JwtUserFactory;
import com.systems.wissen.model.Admin;
import com.systems.wissen.model.Employee;
import com.systems.wissen.model.RoleEnum;
import com.systems.wissen.model.UserCredential;
import com.systems.wissen.repo.SuperAdminRepository;
import com.systems.wissen.repo.UserCredentialRepository;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserCredentialRepository userCredentialRepository;
	@Autowired
	private SuperAdminRepository superAdminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Before");
		UserCredential user = userCredentialRepository.getUserByEmpId(username);
		System.out.println("in user details service");
		if (user == null) {
			Admin admin = superAdminRepository.getAdmin(username);
			if (admin != null) {
				user = new UserCredential();
				user.setRoleId(admin.getRole().getRoleId());
				Employee employee = new Employee();
				employee.setEmpId(admin.getId());
				user.setEmployee(employee);
				user.setPassword(admin.getPassword());
			}
		}

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return JwtUserFactory.create(user);
			
		}
	}
}
