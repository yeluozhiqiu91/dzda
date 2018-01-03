package com.igool.dzda.job.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igool.dzda.job.server.StatisticsServer;
import com.igool.dzda.job.util.DateUtil;
import com.igool.rpc.db.model.thrift.Jjtj;
import com.igool.rpc.db.model.thrift.Statistics;
import com.igool.rpc.db.model.thrift.StatisticsFilter;
import com.igool.rpc.db.service.thrift.StatisticsService;

@Service("statisticsServer")
public class StatisticsServerImpl implements StatisticsServer {
	
	Logger logger = LoggerFactory.getLogger(StatisticsServerImpl.class);
	
	@Autowired
	StatisticsService statisticsService;
	
	int DELIVERYTYPE = 1;
	
	int SIGNTYPE = 2;
	
	@Override
	public void statisticsJob() {
		logger.info("statistics begin......");

		HashMap<String, String> dateMap = DateUtil.getDate();
		String kssj = dateMap.get("kssj");
		//kssj = "2017-12-14 18:00:00";
		logger.info("now kssj is " + kssj);
		String tjsj = dateMap.get("tjsj");
		//tjsj = "2017-12-14 18:59:59";
		logger.info("now tjsj is " + tjsj);

		Statistics query = new Statistics();
		query.setKssj(kssj);
		query.setTjsj(tjsj);
		
		// 根据kssj和tjsj查询符合时间段的交接记录
		// 结果的格式为jjxl,buiness_id
		List<StatisticsFilter> statisticsFilterList = null;
		try {
			statisticsFilterList = statisticsService.getDeliveryStatisticsFilter(query);
			jjtjJob(query, statisticsFilterList, DELIVERYTYPE);
			
			statisticsFilterList = statisticsService.getSignStatisticsFilter(query);
			jjtjJob(query, statisticsFilterList, SIGNTYPE);
		} catch (TException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void jjtjJob(Statistics query, List<StatisticsFilter> statisticsFilterList, int type) {
		try {
			logger.info("statistics type is:" + type);
			logger.info("filterList sumSize:" + statisticsFilterList.size());
			if (statisticsFilterList != null && statisticsFilterList.size() > 0) {
				// 然后根据jjlx的不同,划分到不同列表里,分别进行统计查询
				// 如果jjlx有2种,则过滤为jjlx1的list和jjlx2的list
				HashMap<Integer, List<StatisticsFilter>> filterMap = new HashMap<>();
				for (StatisticsFilter filter : statisticsFilterList) {
					// 根据交接类型获取map中对应的jjlx的key的list
					List<StatisticsFilter> list = filterMap.get(filter.getJjlx());
					// 如果没有,则创建新list,并把这个filter对象放入到list中
					if (list == null) {
						list = new ArrayList<StatisticsFilter>();
					}
					//存在的话则直接添加当前filter到该list中
					//然后key为jjlx,value为list,放入到map里
					list.add(filter);
					filterMap.put(filter.getJjlx(), list);
				}
				
				
				//通过获取按照jjlx分类好的filterMap循环按照条件进行统计查询,并写入数据库
				Iterator<Entry<Integer, List<StatisticsFilter>>> it = filterMap.entrySet().iterator();
				while(it.hasNext()) {
					Entry<Integer, List<StatisticsFilter>> entry = it.next();
					logger.info("jjlx:" + entry.getKey());
					logger.info("jjlx size:" + entry.getValue().size());
					Jjtj deliveryJjtj = statisticsService.getJjtj(entry.getValue());
					logger.info("dasl:" + deliveryJjtj.getDaCount());
					logger.info("lssl:" + deliveryJjtj.getLsCount());
					
					Statistics record = new Statistics();
					record.setJjlx(entry.getKey()); //交接类型
					record.setDasl(deliveryJjtj.getDaCount()); //档案数量
					record.setLssl(deliveryJjtj.getLsCount()); //流水数量
					record.setKssj(query.getKssj()); //开始时间
					record.setTjsj(query.getTjsj()); //统计时间
					record.setType(type); //统计类型
					statisticsService.addStatistics(record);
				}
			}
		} catch (TException e) {
			logger.info("交接统计任务失败.....");
			e.printStackTrace();
		}
	}
}
