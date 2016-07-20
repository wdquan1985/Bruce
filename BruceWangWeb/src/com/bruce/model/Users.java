package com.bruce.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;


public class Users  extends BaseModel implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8536467008591402343L;


	public Users(String username, String password, String role, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}

	public Users(long long1, String string, String string2, String string3,
			boolean boolean1) {
		// TODO Auto-generated constructor stub
	}

	private long id;
	
	private String username;
	
	private String password;
	
	private String role;
	
	private boolean enabled;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String GetFriendlyName() {
		// TODO Auto-generated method stub
		return "用户名="+username+"角色="+role;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password="
				+ password + ", role=" + role + ", enabled=" + enabled + "]";
	}


	public Collection<GrantedAuthority> getAuthorities() {
		  List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(new GrantedAuthorityImpl(getRole()));
	        return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}




}
