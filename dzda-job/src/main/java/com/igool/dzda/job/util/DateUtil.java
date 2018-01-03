package com.igool.dzda.job.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateUtil {

	static public HashMap<String, String> getDate() {
		
		HashMap<String, String> returnMap = new HashMap<>(1);
		
		Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(new Date());
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    
	    String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime());  
	    //14,15 分钟   17,18 秒
	    //[2, 0, 1, 7, -, 1, 2, -, 2, 8,  , 1, 2, :, 3, 8, :, 3, 6]
	    String date = str.substring(0, 10);
	    String hourString = null;
	    if (0 < hour && hour < 10) {
	    	hourString = "0" + (hour - 1);
	    }else if(hour == 0) {
	    	//TODO 这里没有做前一天处理，如果是0点触发，相当于统计前一天23:00:00 到 23:59:59
	    	hourString = "0" + (hour);
	    }else {
	    	hourString = "" + (hour);
	    }
	    
	    String kssj = date + " " + hourString + ":00:00";
	    returnMap.put("kssj", kssj);
	    String tjsj = date + " " + hourString + ":59:59";
	    returnMap.put("tjsj", tjsj);
		
		return returnMap;
	}

}
