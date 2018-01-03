package com.igool.rpc.db.service.impl;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.igool.rpc.db.model.thrift.Jjtj;
import com.igool.rpc.db.model.thrift.Statistics;
import com.igool.rpc.db.model.thrift.StatisticsFilter;
import com.igool.rpc.db.mysql.mapper.StatisticsMapper;
import com.igool.rpc.db.service.thrift.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Override
	public void close() {
		this.close();
	}

	@Override
	public List<Statistics> getStatistics(Statistics query) throws TException {
		return statisticsMapper.getStatistics(query);
	}

	@Override
	public void addStatistics(Statistics record) throws TException {
		statisticsMapper.addStatistics(record);
	}

	@Override
	public List<StatisticsFilter> getDeliveryStatisticsFilter(Statistics query) throws TException {
		return statisticsMapper.getDeliveryStatisticsFilter(query);
	}

	@Override
	public Jjtj getJjtj(List<StatisticsFilter> query) throws TException {
		return statisticsMapper.getJjtj(query);
	}

	@Override
	public List<StatisticsFilter> getSignStatisticsFilter(Statistics query) throws TException {
		return statisticsMapper.getSignStatisticsFilter(query);
	}

}
