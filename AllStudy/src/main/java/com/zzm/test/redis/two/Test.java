package com.zzm.test.redis.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	private RedisCache redisCache;
	
	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("redis.xml");
		redisCache = ctx.getBean("redisCache",RedisCache.class);
	}
	
	@org.junit.Test
	public void test() throws Exception {
		List<String> list = new ArrayList<String>();
        list.add("测试list");
        list.add("测试list2");
        
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("test*","测试数据");
        map.put("测试数据","啥的");
        map.put("listTest",list);
        redisCache.putCache("testMap",map);

        Map<String,Object> mapResult = redisCache.getCache("testMap");
        System.out.print(mapResult.toString());

	}
	
}
