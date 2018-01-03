package com.igool.ssp.web.model;

import java.io.Serializable;

/**
 * Created by zhanggen on 2017/8/16.
 */
public class WfdlRequest implements Serializable{
    private String xzqh;
    private String address;

    private String dldm;

    public String getDldm() {
        return dldm;
    }

    public void setDldm(String dldm) {
        this.dldm = dldm;
    }


    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
