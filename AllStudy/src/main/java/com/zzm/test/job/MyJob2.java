package com.zzm.test.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob2 implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(new Date() + ": doing something2...");
	}

}
