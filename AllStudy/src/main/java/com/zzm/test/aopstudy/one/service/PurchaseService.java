package com.zzm.test.aopstudy.one.service;

import org.springframework.stereotype.Service;

//@Service("purchaseService")
public class PurchaseService {
	public void purchaseProduct(String productName,int price,String type) {  
        System.out.println("购买商品。。。");  
    } 
}
