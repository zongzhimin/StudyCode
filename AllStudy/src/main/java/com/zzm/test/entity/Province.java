package com.zzm.test.entity;

import java.io.Serializable;

public class Province implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String province;
	public Province() {
	}
	
	public Province(Integer id, String province) {
		this.id = id;
		this.province = province;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
}
