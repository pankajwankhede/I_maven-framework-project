package com.wss.lsl.spring.demo.entity;

import java.util.List;

public class Role {
	
	private Integer id;
	private String name;
	
	private List<Authority> authorities;
	
	public Role() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
}
