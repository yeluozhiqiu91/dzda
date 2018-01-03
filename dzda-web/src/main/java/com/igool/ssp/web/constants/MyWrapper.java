
package com.igool.ssp.web.constants;

import com.igool.rpc.db.model.thrift.Jjtj;
import org.displaytag.decorator.TableDecorator;



public class MyWrapper extends TableDecorator {

    public String getLsCount(){
        Jjtj jjtj = (Jjtj) getCurrentRowObject();
        String dateTime = jjtj.getDateTime();
        String startTime = dateTime+" 00:00:00";
        String endTime = dateTime+" 23:59:59";
        int lsCount = jjtj.getLsCount();
        Integer jjlx = jjtj.getJjlx();
        return "<a href=\"/query/lsmxList?startTime="+startTime+"&endTime="+endTime+"&jjlx="+jjlx+"\" >"+lsCount+"</a>";
    }

    public String getDaCount(){
        Jjtj jjtj = (Jjtj) getCurrentRowObject();
        String dateTime = jjtj.getDateTime();
        String startTime = dateTime+" 00:00:00";
        String endTime = dateTime+" 23:59:59";
        int daCount = jjtj.getDaCount();
        Integer jjlx = jjtj.getJjlx();
        return "<a href=\"/query/damxList?startTime="+startTime+"&endTime="+endTime+"&jjlx="+jjlx+"\" >"+daCount+"</a>";

    }
}
