package com.zzm.threadStu.day02.immutable.safe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 使用不可变状态对象情况下，更新车辆的位置
 * @author Administrator
 *
 */
public class VehicleTracker {
	private Map<String,Location> locMap = new ConcurrentHashMap<String,Location>();
	
	public void updateLocation(String vehicleId,Location newLocation){
		locMap.put(vehicleId, newLocation);
	}
}
