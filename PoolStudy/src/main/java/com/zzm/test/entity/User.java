package com.zzm.test.entity;

public class User {
	private Integer id;
	private String name;
	private Integer age;
	private String recommend;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", recommend=" + recommend + "]";
	}
	
	
	
}
