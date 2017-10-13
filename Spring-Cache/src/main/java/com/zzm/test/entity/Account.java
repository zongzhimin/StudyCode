package com.zzm.test.entity;

public class Account {
	private Integer id;
	private String name;
	private String password;
	
	public Account() {
	}
	public Account(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(String name) {
		super();
		this.name = name;
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
	
}
