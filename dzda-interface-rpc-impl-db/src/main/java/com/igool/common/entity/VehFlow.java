package com.igool.common.entity;

import java.util.Date;

/**
 * Created by fangzetao on 17/5/24.
 */
public class VehFlow {
    String dabh;//档案编号
    String hpzl; //号牌种类
    String hphm;//号牌号码
    String ywlx;//业务类型
    String clsbdh;//车辆识别代号
    Date sqrq;//申请日期
    String xh;//序号
    String lszt;//流水状态
    String lsh;//流水号

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

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public Date getSqrq() {
        return sqrq;
    }

    public void setSqrq(Date sqrq) {
        this.sqrq = sqrq;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getLszt() {
        return lszt;
    }

    public void setLszt(String lszt) {
        this.lszt = lszt;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    @Override
    public String toString() {
        return "VehFlow{" +
                "dabh='" + dabh + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", hphm='" + hphm + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", clsbdh='" + clsbdh + '\'' +
                ", sqrq=" + sqrq +
                ", xh='" + xh + '\'' +
                ", lszt='" + lszt + '\'' +
                ", lsh='" + lsh + '\'' +
                '}';
    }
}
