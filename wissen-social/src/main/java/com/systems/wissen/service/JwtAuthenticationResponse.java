package com.systems.wissen.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final List<String> authorities=new ArrayList<>();

    public JwtAuthenticationResponse(String token,Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        for(GrantedAuthority authority:authorities) {
        	System.out.println(authority);
        	this.authorities.add(authority.toString());
        }
    }

    public String getToken() {
        return this.token;
    }


	public List<String> getAuthorities() {
		return authorities;
	}
}
