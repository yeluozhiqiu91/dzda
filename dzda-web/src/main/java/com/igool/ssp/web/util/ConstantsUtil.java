package com.igool.ssp.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2017/11/8.
 */
public  class ConstantsUtil {
    public static int OPERATORTYPE_CX=1;//操作类型，查询
    public static int OPERATORTYPE_DY=2;//操作类型，打印
    public static Map<String,String> businessType=new HashMap<String,String>();//业务类型
    static{
        businessType.put("A","注册登记");
        businessType.put("B","转移登记");
        businessType.put("D","变更登记");
        businessType.put("E","抵押登记");
        businessType.put("F","停复驶业务");
        businessType.put("G","注销登记");
        businessType.put("H","临时入境");
        businessType.put("I","转入业务");
        businessType.put("J","校车登记");
        businessType.put("K","补换牌证合格标志");
        businessType.put("L","补换登记证书");
        businessType.put("M","档案更正");
        businessType.put("N","被盗抢记录");
        businessType.put("O","临时号牌");
        businessType.put("P","申请合格标志");
        businessType.put("Q","委托检验");
        businessType.put("R","受托检验");
        businessType.put("S","锁定业务");
        businessType.put("T","解除监管");
        businessType.put("U","转出/注销恢复");
        businessType.put("V","变更备案");
        businessType.put("W","电子档案补录");
        businessType.put("X","大吨小标更正");
        businessType.put("QT","其他");
    }
}
