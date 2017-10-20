package com.zzm.test.dateapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Test1 {

	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2017,10,20);
		int day = date1.getDayOfMonth();
		System.out.println(day);
		System.out.println("------");
		
		
		//日期解析和格式化
		LocalDate date2 = LocalDate.parse("2017-10-20"); 
		LocalTime time1 = LocalTime.parse("08:08:08");
		System.out.println("date2:  "+date2);
		System.out.println("time1:  "+time1);
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println("now  "+now);
		System.out.println("format now:  "+ now.format(DateTimeFormatter.BASIC_ISO_DATE));
		
		//日期合并
		LocalDate localDate = LocalDate.now(); 
		LocalTime localTime = LocalTime.now();
		LocalDateTime dt1 = LocalDateTime.of(2017, Month.JANUARY, 21, 18, 7);
		LocalDateTime dt2 = LocalDateTime.of(localDate, localTime);
		LocalDateTime dt3 = localDate.atTime(13,45,20);
		LocalDateTime dt4 = localDate.atTime(localTime);
		LocalDateTime dt5 = localTime.atDate(localDate);
		
		//操作日期
		LocalDate date5 = LocalDate.of(2014,3,18); //2014-3-18
		LocalDate date6 = date1.plusWeeks(1); //2014-3-25
		LocalDate date3 = date2.minusYears(3); //2011-3-25
		LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS); //2011-09-25
	}

}
