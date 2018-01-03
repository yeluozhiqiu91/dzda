package com.igool.common.entity;

/**
 * Created by fangzetao on 17/6/1.
 */
public class Vehicle {

    String dabh;//档案编号
    String hpzl; //号牌种类
    String hphm;//号牌号码
    String clsbdh;//车辆识别代号
    String xh;//序号
    String syr;//所有人
    String ccdjrq;//初次登记日期
    String clpp1;//中文品牌
    String sfzmhm;//身份证
    int isLet;      //1 是  0否

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getCcdjrq() {
        return ccdjrq;
    }

    public void setCcdjrq(String ccdjrq) {
        this.ccdjrq = ccdjrq;
    }

    public String getClpp1() {
        return clpp1;
    }

    public void setClpp1(String clpp1) {
        this.clpp1 = clpp1;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public int getIsLet() {
        return isLet;
    }

    public void setIsLet(int isLet) {
        this.isLet = isLet;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

}
