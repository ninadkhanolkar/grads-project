package com.systems.wissen.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.systems.wissen.model.RoleEnum;
import com.systems.wissen.model.UserCredential;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static UserDetails create(UserCredential user) {
		int roleId = user.getRoleId();
		ArrayList<String> authorities = new ArrayList<>();
		for (int i = roleId - 1; i < 3; i++) {
			authorities.add(RoleEnum.values()[i].name());
		}
		return new JwtUser(user.getEmployee().getEmpId(), user.getPassword(), mapToGrantedAuthorities(authorities),
				true);
	}	

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map(authority -> {
			Function<String, SimpleGrantedAuthority> returnAuthority = SimpleGrantedAuthority::new;
			return returnAuthority.apply(authority);
		}).collect(Collectors.toList());
	}
}
