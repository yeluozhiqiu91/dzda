package com.igool.dzda.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.igool.dzda.job.server.StatisticsServer;

public class StatisticsJob extends QuartzJobBean {

	Logger logger = LoggerFactory.getLogger(StatisticsJob.class);

	@Autowired
	StatisticsServer statisticsServer;
	
	public StatisticsServer getStatisticsServer() {
		return statisticsServer;
	}

	public void setStatisticsServer(StatisticsServer statisticsServer) {
		this.statisticsServer = statisticsServer;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		statisticsServer.statisticsJob();
	}

}
