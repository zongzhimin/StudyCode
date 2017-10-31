package com.zzm.threadStu.day02.immutable;
/**
 * 状态可变的位置信息（非线程安全）
 * @author Administrator
 *
 */
public class UnSafeLocation {
	private double x;
	private double y;
	
	public UnSafeLocation(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
