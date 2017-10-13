package com.zzm.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zzm.test.entity.Province;

public interface TestDao {
	List<Province> getAllProvince();
}
