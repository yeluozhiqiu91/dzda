package com.igool.ssp.web.util;



import java.io.Serializable;

public class SspUploadRequest implements Serializable{


    /**
     *
     */
    private static final long serialVersionUID = 1225894527830159868L;
    private Integer id;
    private String checkStatus;//审核状态
    private String checkAdvice;//审核意见
    private String lawType;
    private String lawCode;
    private String carNum;
    private String carType;
    private String remark;
    private String DtjtUrl;
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String dlbm;
    private String ldbm;
    private String dlmc;
    private String checkUserId;
    private Integer dlldStatus = 0;//0为初始,1为默认查回的数据,2为用户修改
    private boolean checkSame;

    public String getDtjtUrl() {
        return DtjtUrl;
    }

    public void setDtjtUrl(String dtjtUrl) {
        DtjtUrl = dtjtUrl;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCheckStatus() {
        return checkStatus;
    }
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    public String getCheckAdvice() {
        return checkAdvice;
    }
    public void setCheckAdvice(String checkAdvice) {
        this.checkAdvice = checkAdvice;
    }
    public String getLawType() {
        return lawType;
    }
    public void setLawType(String lawType) {
        this.lawType = lawType;
    }
    public String getLawCode() {
        return lawCode;
    }
    public void setLawCode(String lawCode) {
        this.lawCode = lawCode;
    }
    public String getCarNum() {
        return carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPic1() {
        return pic1;
    }
    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }
    public String getPic2() {
        return pic2;
    }
    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }
    public String getPic3() {
        return pic3;
    }
    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }
    public String getPic4() {
        return pic4;
    }
    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }

    public String getDlbm() {
        return dlbm;
    }

    public void setDlbm(String dlbm) {
        this.dlbm = dlbm;
    }

    public String getLdbm() {
        return ldbm;
    }

    public void setLdbm(String ldbm) {
        this.ldbm = ldbm;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }

    public Integer getDlldStatus() {
        return dlldStatus;
    }

    public void setDlldStatus(Integer dlldStatus) {
        this.dlldStatus = dlldStatus;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public boolean isCheckSame() {
        return checkSame;
    }

    public void setCheckSame(boolean checkSame) {
        this.checkSame = checkSame;
    }
}
