package com.zzm.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zzm.test.dao.TestDao;
import com.zzm.test.entity.Province;
import com.zzm.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDao testDao;

	public List<Province> testDao() {
		return testDao.getAllProvince();
	}
	
	
}
