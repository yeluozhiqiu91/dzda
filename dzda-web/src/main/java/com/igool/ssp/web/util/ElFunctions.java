package com.igool.ssp.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
public class ElFunctions {
    static Logger logger = LoggerFactory.getLogger(ElFunctions.class);
    private static ObjectMapper objectMapper ;
    public static String toJsonString(Object obj){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        String str = null;
        try {
            str =  objectMapper.writeValueAsString(obj);
            str = jsonString2zw(str);
        } catch (Exception e) {
            logger.info("json 转换失败"+e.toString());
            e.printStackTrace();
        }
        return str;
    }
    private static String jsonString2zw(String s){
        char[] temp = s.toCharArray();
        int n = temp.length;
        for(int i =0,j =0;i<n;i++){
            if('\"'== temp[i]){
                if(j%2 == 0){
                    temp[i]='“';
                }else{
                    temp[i]='”';
                }
                j++;
            }
        }
        return new String(temp);
    }
}
