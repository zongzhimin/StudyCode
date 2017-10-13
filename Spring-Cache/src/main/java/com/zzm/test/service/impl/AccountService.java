package com.zzm.test.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.zzm.test.entity.Account;

public class AccountService {
	
	@Cacheable(value="accountCache",condition="#userName.length()<=4")
	public Account getAccountByName(String userName) {
		System.out.println("real query account"+userName);
		return getFromDB(userName);
	}
	
	@Cacheable(value="accountCache",key="#userName.concat(#password)")
	public Account getAccount(String userName,String password,boolean sendLog) {
		return getFromDB(userName,password);
	}
	
	@CacheEvict(value="accountCache",key="#account.getName()")//清空 accountCache 缓存
	public void updateAcount(Account account) {
		updateDB(account);
	}
	
	@CacheEvict(value="accountCache",allEntries=true)
	public void reload() {
		
	}
	
	private Account getFromDB(String userName,String password) {
		System.out.println("real query DB"+userName+" "+password);
		return new Account(userName,password);
	}
	
	private Account getFromDB(String userName) {
		System.out.println("real query DB"+userName);
		return new Account(userName);
	}
	private void updateDB(Account account) {
		System.out.println("realUpdate db ..."+account.getName());
	}
	
}
