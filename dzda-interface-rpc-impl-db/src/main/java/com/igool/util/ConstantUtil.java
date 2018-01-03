package com.igool.util;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class ConstantUtil {


    public static final int A_TO_B_DJ=1;//A转B登记
    public static final int A_TO_B_QS=2;//A转B签收
    public static final int B_TO_A_DJ=3;//B转A登记
    public static final int B_TO_A_QS=4;//B转A签收

    public static final int A=1;//A库
    public static final int B=2;//B库

    public static final int STATUS_YDJ=0;//已登记
    public static final int STATUS_YQS=1;//已签收
    public static final int STATUS_QSZ=2;//签收中

    public static final int CK_STATUS_0=0;//出库
    public static final int CK_STATUS_1=1;//未出库
    public static final int CK_STATUS_2=2;//出库中

    public static final int WT_FILE=0;//问题档案
    public static final int ZC_FILE=1;//正常档案

    public static final int QS_BUSINESS=0;//流水缺失
    public static final int ZC_BUSINESS=1;//正常流水
    public static final int ZL_QS_BUSINESS=2;//资料缺失

    public static final int NO_PICK=0;//  未采集(0),
    public static final int OK_PICK=1;//  已采集(1),
    public static final int DO_PICK=2;//  采集中(2);

}
