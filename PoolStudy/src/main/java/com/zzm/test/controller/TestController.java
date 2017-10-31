package com.zzm.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzm.test.dao.TestDao;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestDao testDao;
	
	@ResponseBody
	@RequestMapping(value="/getUsers",method=RequestMethod.GET)
	Object getUsers(){
		return testDao.getAllUser(); 
	}
	
}
