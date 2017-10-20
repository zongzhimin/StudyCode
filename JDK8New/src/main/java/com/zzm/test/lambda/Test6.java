package com.zzm.test.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Test6 {

	public static void main(String[] args) {
		List<String> strList = Arrays.asList("abc","ab","dc","zzm","num1");
		//filter对每个元素过滤
		List<String> filtered = strList.stream().filter(x -> x.length()>2).collect(Collectors.toList());
		System.out.println("OLD strList:"+strList+"    NEW filtered:"+filtered);
		
		System.out.println("\n \n");
		//使用map对每个元素操作
		List<String> greatStr = strList.stream().map(n->n.toUpperCase()).collect(Collectors.toList());
		System.out.println("转大写： "+greatStr);
		
		//distinct过滤
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map(x -> x*x).distinct().collect(Collectors.toList());
		System.out.println("平方过滤后:"+distinct);
		
		//获取最大最小
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt(x->x).summaryStatistics();
		System.out.println("最大："+stats.getMax());
		System.out.println("最小："+stats.getMin());
		System.out.println("求和："+stats.getSum());
		System.out.println("平均："+stats.getAverage());
	}
	
}
