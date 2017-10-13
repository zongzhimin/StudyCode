package com.zzm.test.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

	public static void main(String[] args) {
		Map<Long,User> users = new HashMap<Long,User>();
		users.put(1l, new User(1l,"小明",8));
		
		System.out.println(users);
		
		User u = users.get(1l);
		u.setName("老王");
		u.setAge(40);
		
		
		System.out.println(users);
	}

}
