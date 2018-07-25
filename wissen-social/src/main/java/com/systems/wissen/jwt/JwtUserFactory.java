package com.systems.wissen.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.systems.wissen.model.RoleEnum;
import com.systems.wissen.model.UserCredential;




public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static UserDetails create(UserCredential user) {
    	int roleId=user.getRoleId();
    	ArrayList<String> authorities = new ArrayList<>();
    	for(int i=roleId-1;i<3;i++) {
    	authorities.add(RoleEnum.values()[i].name());
    	}
        return new JwtUser(
                user.getEmployee().getEmpId(),
                user.getPassword(),
              mapToGrantedAuthorities(authorities),
                true
        );
//    	return User.withUsername(user.getEmployee().getEmpId()).password(user.getPassword()).authorities(mapToGrantedAuthorities(authorities))
//    			.accountExpired(false)
//    			.accountLocked(false)
//    			.credentialsExpired(false)
//    			.disabled(false)
//    			.build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }
}
