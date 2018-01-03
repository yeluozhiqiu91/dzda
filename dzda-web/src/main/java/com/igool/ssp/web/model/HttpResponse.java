package com.igool.ssp.web.model;

import java.io.Serializable;

/**
 * Created by linpx on 16/9/23.
 */
public class HttpResponse implements Serializable {

    public final static String FAILED = "FAILED";
    public final static String SUCCESS = "SUCCESS";

    private String result = "FAILED";//失败为FAILED,成功为SUCCESS
    private Object data;//返回数据
    private String expCode;//result为FAILED的错误码
    private String expMsg;//result为FAILED的错误信息

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpMsg() {
        return expMsg;
    }

    public void setExpMsg(String expMsg) {
        this.expMsg = expMsg;
    }
}
