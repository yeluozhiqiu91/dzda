package com.igool.ssp.web.controller;

import com.alibaba.fastjson.JSON;
import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.DeliveryInfo;
import com.igool.rpc.db.model.thrift.FileId;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.TransferService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.ConstantUtil;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.constants.QRCodeUtil;
import com.igool.ssp.web.model.UserAndMenu;
import framework.pisces.data.DataSet;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.thrift.TException;
import org.jdal.dao.Page;
import org.jdal.web.table.PaginatedListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/9/29.
 */
@Controller
@AclAnnotation(pUrl = "/transfer/showBzadjPage")
public class BTransferToAController {

    Logger logger = LoggerFactory.getLogger(BTransferToAController.class);

    @Autowired
    BusinessInfoService businessInfoService;

    @Autowired
    TransferService transferService;

    @Value("#{config['is.test']}")
    boolean isTest;
    /**B转A登记页面
     *
     * @param mav
     * @return
     */
    @RequestMapping("/transfer/showBzadjPage")
    public ModelAndView showBzadjPage(ModelAndView mav){//显示b转a登记页面
        mav.setViewName("transfer/bzadj");//b转a登记
        if(isTest){
            mav.addObject("isTest","isTest");
        }
        return mav;
    }


    @RequestMapping("/transfer/queryAll")
    @AclAnnotation(pUrl = "/transfer/showBzadjPage")
    public ModelAndView showBzaPage(ModelAndView mav) throws TException, IOException {//显示b转a登记页面
        List<FileId> fileIdList=transferService.findAllFileIdsByCkStatus(1);
        //List<FileId> fileIdList=new ArrayList<>();
        int sqlSize=1000;//每条sql包含的fileid个数，每条sql有1000个fileid的话，那就有400条sql，放到一个文件
        /*if(fileIdList.size()>0){
            int sqlCount=0;
            if(fileIdList.size()%sqlSize==0){
                sqlCount=fileIdList.size()/sqlSize;
            }else{
                sqlCount=fileIdList.size()/sqlSize+1;
            }
            File lsfile=new File("d:"+File.separator+"lsztsql"+File.separator+"lszt.sql");
            if(lsfile.exists()){
                lsfile.delete();
            }
            for(int j=0;j<sqlCount;j++){//每次创建一条sql
                StringBuffer sb=createSql(fileIdList,j*sqlSize,sqlSize);
                lsfile=new File("d:"+File.separator+"lsztsql"+File.separator+"lszt.sql");
                FileWriter fileWriter=new FileWriter(lsfile,true);
                fileWriter.write(sb.toString()+"\r\n");
                *//*FileOutputStream fileOutputStream= new FileOutputStream(file);
                fileOutputStream.write(sb.toString().getBytes());
                fileOutputStream.close();*//*
                fileWriter.close();
            }
        }*/
        //以上是更新流水位置和流水状态

        List<FileId> fileIdList3=transferService.findAllForBox();
        int sqlSize3=400;//有4000多个箱子，每个文件400条sql，就是10个文件
        if(fileIdList3.size()>0){
            int sqlCount=0;
            if(fileIdList3.size()%sqlSize==0){
                sqlCount=fileIdList3.size()/sqlSize3;
            }else{
                sqlCount=fileIdList3.size()/sqlSize3+1;
            }
            //int sqlCount=fileIdList3.size()/sqlSize3+1;//sql文件个数
            for(int j=0;j<sqlCount;j++){//每次创建一条sql
                StringBuffer sb=createSql3(fileIdList3,j*sqlSize3,sqlSize3);
                File file=new File("d:"+File.separator+"boxsql"+File.separator+"box"+j+".sql");
                FileOutputStream fileOutputStream= new FileOutputStream(file);
                fileOutputStream.write(sb.toString().getBytes());
                fileOutputStream.close();
            }
        }
        //接下来更新档案编号，由于数据有40多万条，只能分批查，改成每次查1万条，查40多次
        /*int fileCount=transferService.findAllForFileCodeCount();
        int queryCount=0;
        if(fileCount%10000==0){
            queryCount=fileCount/10000;
        }else{
            queryCount=fileCount/10000+1;
        }
        for(int i=0;i<queryCount;i++) {
            //List<FileId> fileIdList2 = transferService.findAllForFileCode();
            List<FileId> fileIdList2 = transferService.findForFileCode(i*10000,10000);
            int sqlSize2 = 10000;//这个记录会有40多万条，分成10个文件就是每个文件4万条
            if (fileIdList2.size() > 0) {
                int sqlCount = 0;
                if (fileIdList2.size() % sqlSize2 == 0) {
                    sqlCount = fileIdList2.size() / sqlSize2;
                } else {
                    sqlCount = fileIdList2.size() / sqlSize2 + 1;
                }
                //int sqlCount=fileIdList2.size()/sqlSize2+1;//sql文件个数
                for (int j = 0; j < sqlCount; j++) {//每次创建一条sql
                    StringBuffer sb = createSql2(fileIdList2, j * sqlSize2, sqlSize2);
                    File file = new File("d:" + File.separator + "filecodesql" + File.separator + "filecode"+i+"_" + j + ".sql");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(sb.toString().getBytes());
                    fileOutputStream.close();
                }
            }
        }*/


        //接下来更新箱号

        mav.setViewName("transfer/bzadj");//b转a登记
        if(isTest){
            mav.addObject("isTest","isTest");
        }
        return mav;
    }
    public StringBuffer createSql(List<FileId> list,int startIndex,int sqlSize){
        StringBuffer sb=new StringBuffer();
        //sb.append("update t_da_business_info set lswz=2,lszt=4 where file_id in(");//已签收
        //sb.append("update t_da_business_info set lswz=1,lszt=1 where file_id in(");//已登记
        sb.append("update t_da_business_info set lswz=1,lszt=0 where file_id in(");//未登记
        for(int j=0;j<sqlSize;j++){
            sb.append(list.get(startIndex+j).getFileId()).append(",");
            if(j+startIndex==list.size()-1){
                break;
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        return sb;

    }
    public StringBuffer createSql2(List<FileId> list,int startIndex,int sqlSize){
        StringBuffer sb=new StringBuffer();
        for(int j=0;j<sqlSize;j++){
            sb.append("update t_da_business_info set file_code='").append(list.get(startIndex+j).getFileCode()).append("'").append(" where file_id=").append(list.get(startIndex+j).getFileId()).append(";");
            if(j+startIndex==list.size()-1){
                break;
            }
        }
        return sb;

    }

    public StringBuffer createSql3(List<FileId> list,int startIndex,int sqlSize){
        StringBuffer sb=new StringBuffer();
        for(int j=0;j<sqlSize;j++){
            sb.append("update t_da_business_info set box='").append(list.get(startIndex+j).getBox()).append("'").append(" where file_id in (").append(list.get(startIndex+j).getFileIds()).append(");");
//            sb.append("update t_da_business_info set box=").append(list.get(startIndex+j).getBox()).append(" where file_id=").append(list.get(startIndex+j).getFileId()).append(";");
            if(j+startIndex==list.size()-1){
                break;
            }
        }
        return sb;

    }
    /**
     * B转A登记，根据流水号查流水
     * @param lsh
     * @return
     */
    @RequestMapping("/transfer/findBusinessForDj")
    @ResponseBody
    public Map<String,Object> findBusinessForDj(String lsh){
        Map<String,Object> map=new HashMap<String,Object>();
        if(lsh.length()==11){
            lsh="1A"+lsh.substring(1)+"0";
        }
        /*if(lsh.length()==13){*/
            try {
                BusinessInfo businessInfo = businessInfoService.findNativeBusinessInfoByLsh(lsh);
                if(businessInfo.getBusinessId()==0){
                    map.put("code","1");
                    map.put("msg","未查询到流水");
                    return map;
                }else{
                    if(businessInfo.getLswz()== ConstantUtil.LSWZ_A){
                        map.put("code","1");
                        map.put("msg","该流水资料非B库档案资料，无法登记!");
                        return map;
                    }else if(businessInfo.getLszt()!=ConstantUtil.A_TO_B_QS){
                        map.put("code","1");
                        map.put("msg","流水状态不符合条件！");
                        return map;
                    }else{
                        map.put("code","0");
                        map.put("msg","成功");
                        map.put("businessInfo",businessInfo);
                        return map;
                    }
                }
            } catch (TException e) {
                e.printStackTrace();
                map.put("code","1");
                map.put("msg","查询异常");
                return map;
            }
        /*}else{
            map.put("code","1");
            map.put("msg","该流水号位数不正确!");
            return map;
        }*/
    }

    /**
     * B转A确定提交
     * @param data
     * @param request
     * @return
     */
    @RequestMapping("/transfer/bzadj")
    @ResponseBody
    public Map<String,Object> Bzadj(String data, HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        String newJson = StringEscapeUtils.unescapeHtml4(data);
        List<BusinessInfo> businessInfos = JSON.parseArray(newJson,BusinessInfo.class);
        UserAndMenu userSession = (UserAndMenu)   request.getSession().getAttribute(KeyConstants.MEMBER);
        try {
            String pcCode = businessInfoService.BToARegister(businessInfos,userSession.getUser().getUserId());
            logger.info(" --B转A登记成功，批次号： "+pcCode);
            map.put("result","OK");
            map.put("code",pcCode);
        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info("--B转A登记失败");
        }
        return map;
    }

    /**
     * 打印批次号
     * @param mav
     * @param pcCode
     * @return
     */
    @RequestMapping("/transfer/printPcCode")
    public ModelAndView printPcCode(ModelAndView mav,String pcCode){
        DeliveryInfo deliveryInfo=null;
        List<BusinessInfo> businessInfoList=null;
        try {
             deliveryInfo=transferService.findDeliveryInfoByPcCode(pcCode);
        } catch (TException e) {
            e.printStackTrace();
        }
        try {
            businessInfoList=businessInfoService.findNativeBusinessInfoByDeliveryPcCode(pcCode);
        } catch (TException e) {
            e.printStackTrace();
        }
        //查出登记时间、登记人
        String txm=QRCodeUtil.encodeBar(pcCode, 110, 40);
        mav.addObject("pcCode",pcCode);//批次号
        mav.addObject("registerDate",deliveryInfo.getRegisterDate());//登记时间
        mav.addObject("registerPersonName",deliveryInfo.getRegisterPersonName());//登记人
        mav.addObject("txm",txm);
        mav.addObject("businessInfoList",businessInfoList);
        mav.setViewName("transfer/printPcCode");
        //mav.setViewName("transfer/bzadj");//b转a登记
        return mav;
    }

    /**B转A签收页面
     *
     * @param mav
     * @return
     */
    @RequestMapping("/transfer/showBzaqsPage")
    @AclAnnotation(pUrl = "/transfer/showBzaqsPage")
    public ModelAndView showBzaqsPage(ModelAndView mav,String startTime,String endTime,@ModelAttribute("paginatedList") PaginatedListAdapter paginatedList,String pcCode){//显示b转a签收页面
        Page<DeliveryInfo> page = (Page<DeliveryInfo>) paginatedList.getModel();
        int startIndex=page.getStartIndex();
        int pageSize=page.getPageSize();
        List<DeliveryInfo> deliveryList=null;
        long deliveryListCount=0l;
        //初始不作查询
        if((startTime==null||"".equals(startTime)&&(endTime==null||"".equals(endTime)))){
            deliveryList=null;
            deliveryListCount=0;
        }else{
            try {
                deliveryList=transferService.getDeliveryListBza(pcCode,startTime,endTime,page.getStartIndex(),page.getPageSize());
                deliveryListCount=transferService.getDeliveryListCountBza(pcCode,startTime,endTime,page.getStartIndex(),page.getPageSize());
            } catch (TException e) {
                e.printStackTrace();
                logger.info("连接数据库异常");
            }
        }
        DataSet<DeliveryInfo> datas =null;
        if(deliveryList!=null&&deliveryList.size()>0){
            datas=new DataSet<DeliveryInfo>(deliveryList,deliveryListCount,deliveryListCount);
        }
        if(datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("startTime",startTime);
        mav.addObject("endTime",endTime);
        mav.addObject("pcCode",pcCode);
        mav.addObject("deliveryList",deliveryList);
        mav.setViewName("transfer/bzaqs");//b转a签收
        return mav;
    }
    @RequestMapping("/transfer/bzaqsDetail")
    @AclAnnotation(pUrl = "/transfer/showBzaqsPage")
    public ModelAndView bzaqs(ModelAndView mav,String pcCode,String businessCode,String startTime,String endTime){//b转a签收页面，对某一批次签收
        List<BusinessInfo> businessInfoList=null;
        try {
            businessInfoList=transferService.findBusinessInfoByPcCodeAndLszt(pcCode,ConstantUtil.B_TO_A_DJ);
        } catch (TException e) {
            e.printStackTrace();
            logger.info("查询待签收数据异常");
        }
        mav.addObject("businessInfoList",businessInfoList);
        mav.addObject("pcCode",pcCode);
        mav.addObject("startTime",startTime);
        mav.addObject("endTime",endTime);
        mav.setViewName("transfer/bzaqsDetail");//b转a签收,对某一批次数据签收
        return mav;
    }

    /**
     * B转A确认签收
     * @param mav
     * @param businessCode
     * @return
     */
    @RequestMapping("/transfer/bzaConfirmQs")
    @AclAnnotation(pUrl = "/transfer/showBzaqsPage")
    public ModelAndView bzaConfirmQs(ModelAndView mav,String businessCode,String pcCode,String startTime,String endTime,HttpServletRequest request){//b转a确认签收
        BusinessInfo businessInfo=null;
        List<BusinessInfo> businessInfoList=null;
        if(businessCode.length()==11){
            businessCode="1A"+businessCode.substring(1)+"0";
        }
        try {
            businessInfo=transferService.findNativeBusinessInfo(businessCode);
            businessInfoList=transferService.findBusinessInfoByPcCodeAndLszt(pcCode,ConstantUtil.B_TO_A_DJ);
        } catch (TException e) {
            e.printStackTrace();
            logger.info("查询待签收流水异常");
        }
        if(businessInfo.getBusinessId()==0){
            mav.addObject("msg",businessCode+"该流水未登记");
        }else {
            //判断签收的流水是否是当前批次的，如果不是的，不让签收
            int i = 0;
            for (BusinessInfo b : businessInfoList) {
                if (b.getCode().equals(businessInfo.getCode())) {
                    i++;
                    break;
                }
            }
            if (i == 0) {
                mav.addObject("msg", businessInfo.getCode() + "不属于当前批次，无法签收");
            } else {
                if (businessInfo.getLszt() == ConstantUtil.B_TO_A_DJ) {
                    UserAndMenu userAndMenu = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
                    try {
                        transferService.BToASign(businessInfo, pcCode, userAndMenu.getUser().getUserId());
                        for (BusinessInfo b : businessInfoList) {
                            if (b.getCode().equals(businessInfo.getCode())) {
                                businessInfoList.remove(b);
                                break;
                            }
                        }
                        mav.addObject("msg", businessInfo.getCode() + "已签收");
                    } catch (TException e) {
                        e.printStackTrace();
                        logger.info("B转A签收出现异常");
                        mav.addObject("msg", businessInfo.getCode() + "B转A签收出现异常");
                    }
                } else {
                    mav.addObject("msg", businessInfo.getCode() + "流水状态不正确，无法签收");
                }
            }
        }
        mav.addObject("businessInfoList",businessInfoList);
        mav.addObject("pcCode",pcCode);
        mav.addObject("startTime",startTime);
        mav.addObject("endTime",endTime);
        mav.setViewName("transfer/bzaqsDetail");//b转a签收,对某一批次数据签收
        return mav;
    }
    @RequestMapping("/transfer/showDeliveryDetail")
    @AclAnnotation(pUrl = "/transfer/showBzaqsPage")
    public ModelAndView showDeliveryDetail(ModelAndView mav,String pcCode,String businessCode){//b转a签收，查看详情页面
        List<BusinessInfo> businessInfoList=null;
        try {
            businessInfoList=transferService.findBusinessInfoByPcCodeAndLszt(pcCode,0);
        } catch (TException e) {
            e.printStackTrace();
            logger.info("查询B转A已签收数据异常");
        }
        mav.addObject("businessInfoList",businessInfoList);
        mav.addObject("pcCode",pcCode);
        mav.setViewName("transfer/bzaqsDetailShow");//b转a签收，查看详情
        return mav;
    }
}
