package com.igool.rpc.db.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.igool.rpc.db.model.thrift.Jjtj;
import com.igool.rpc.db.model.thrift.Statistics;
import com.igool.rpc.db.model.thrift.StatisticsFilter;

public interface StatisticsMapper {
	
	public List<Statistics> getStatistics(Statistics query);
	
	public void addStatistics(Statistics record);
	
	
	   /**
     * 根据日期过滤交接信息，获取jjlx和buiness_id
     * @param query
     * @return
     */
    List<StatisticsFilter> getDeliveryStatisticsFilter(Statistics query);
    
    List<StatisticsFilter> getSignStatisticsFilter(Statistics query);
    
    Jjtj getJjtj(List<StatisticsFilter> query);
}
