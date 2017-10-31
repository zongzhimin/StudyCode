package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("test")
	public Object Test(){
		
		return "../index/index";
	}

}
