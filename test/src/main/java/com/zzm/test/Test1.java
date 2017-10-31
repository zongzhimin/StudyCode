package com.zzm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test1 {

	public static void main(String[] args) {
		int i = 3;
		List<String> l = new ArrayList<String>();
		l.add("one");
		l.add("two");
		l.add("three");
		l.add("four");
		System.out.println(l);
		l.add(i++, "add");
		System.out.println(l);
		Stack a = new Stack();
		
	}

}
class A {
	public void methodA() {
		
	}
}
class B extends A{
	public void methodB() {
		
	}
}
