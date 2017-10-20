package com.zzm.test.lambda;

import java.util.Arrays;
import java.util.List;

public class Test5 {
	
	public static void main(String[] args) {
		List<Integer> costBeforeTax = Arrays.asList(100,200,300,400,500);
		//不用Lamdba
		for(Integer cost : costBeforeTax) {
			double price = cost +.12*cost;
			System.out.print(price+" ");
		}
		
		System.out.println();
		
		//使用Lamdba
		costBeforeTax.stream().map((cost) -> cost+.12*cost).forEach(System.out::println);
		double bill = costBeforeTax.stream().map((cost) -> cost+.12*cost).reduce((sum,cost) -> sum +cost).get();
		System.out.println("Total: "+bill);
	}
}
