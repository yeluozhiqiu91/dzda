include "dzda/statistics.thrift"
include "dzda/statisticsfilter.thrift"
include "dzda/jjtj.thrift"
namespace java com.igool.rpc.db.service.thrift

service StatisticsService {
		 	list<statistics.Statistics> getStatistics(1:statistics.Statistics query),
		 	void addStatistics(1:statistics.Statistics record),
		 	list<statisticsfilter.StatisticsFilter> getDeliveryStatisticsFilter(1:statistics.Statistics query),
		 	list<statisticsfilter.StatisticsFilter> getSignStatisticsFilter(1:statistics.Statistics query),
		 	jjtj.Jjtj getJjtj(1:list<statisticsfilter.StatisticsFilter> query)
		 }