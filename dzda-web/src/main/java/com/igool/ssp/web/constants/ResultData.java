package com.igool.ssp.web.constants;

/**
 * Created by 123 on 2016/12/23.
 */

/**
 * ajax返回数据
 */
public class ResultData implements java.io.Serializable{
    private String result;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
