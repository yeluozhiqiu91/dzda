package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.PlateTypeService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.util.StringUtils;
import framework.pisces.data.DataSet;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/9/25.
 */
@Controller
@AclAnnotation(pUrl = "/query/fileList")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    BusinessInfoService businessInfoService;
    @Autowired
    PlateTypeService plateTypeService;
    @Value("#{config['online.time']}")
    String onlineTime;
    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 档案查询
     * @param mav
     * @param request
     * @param businessInfo
     * @param paginatedList
     * @return
     * @throws TException
     */
    @RequestMapping("/query/fileList")
    public ModelAndView fileList(ModelAndView mav, HttpServletRequest request, @ModelAttribute BusinessInfo businessInfo,@ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException {
        Page<BusinessInfo> page = (Page<BusinessInfo>) paginatedList.getModel();
        List<BusinessInfo> businessInfoList=null;
        long businessInfoCount=0L;
        DataSet<BusinessInfo> datas =null;
        String plateType= StringUtils.toStringByNotNull(businessInfo.getPlateType());
        String plateCode= StringUtils.toStringByNotNull(businessInfo.getPlateCode());
        String fileCode=StringUtils.toStringByNotNull(businessInfo.getFileCode());
        String carCode=StringUtils.toStringByNotNull(businessInfo.getCarCode());
        String code=StringUtils.toStringByNotNull(businessInfo.getCode());
        if(!plateType.equals("")||!plateCode.equals("")||!fileCode.equals("")||!carCode.equals("")||!code.equals("")){
            businessInfoList=businessInfoService.getBusinessInfoListForShow(businessInfo,page.getStartIndex(),page.getPageSize());
            businessInfoCount=businessInfoService.getBusinessInfoListForShowCount(businessInfo,page.getStartIndex(),page.getPageSize());
        }
        if(businessInfoList!=null&&businessInfoList.size()>0){
            datas=new DataSet<BusinessInfo>(businessInfoList,businessInfoCount,businessInfoCount);
        }
        if(datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        List<PlateType> plateTypeList=plateTypeService.getAllPlateType();
        mav.addObject("businessInfo",businessInfo);
        mav.addObject("plateTypeList",plateTypeList);
        mav.addObject("businessInfoList", businessInfoList);
        mav.setViewName("query/fileList");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/query/fileList1")
    public Map fileList1(@ModelAttribute BusinessInfo businessInfo, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException {
        Map<String,Object> map = new HashMap();
        Page<BusinessInfo> page = (Page<BusinessInfo>) paginatedList.getModel();
        List<BusinessInfo> businessInfoList=null;
        long businessInfoCount=0L;
        DataSet<BusinessInfo> datas =null;
        String plateType= StringUtils.toStringByNotNull(businessInfo.getPlateType());
        String plateCode= StringUtils.toStringByNotNull(businessInfo.getPlateCode());
        String fileCode=StringUtils.toStringByNotNull(businessInfo.getFileCode());
        String carCode=StringUtils.toStringByNotNull(businessInfo.getCarCode());
        String code=StringUtils.toStringByNotNull(businessInfo.getCode());
        if(!plateType.equals("")||!plateCode.equals("")||!fileCode.equals("")||!carCode.equals("")||!code.equals("")){
            businessInfoCount=businessInfoService.getBusinessInfoListForShowCount(businessInfo,page.getStartIndex(),page.getPageSize());
        }
        if(businessInfoCount>0){
            map.put("result", "data");
            return map;
        }else{
            logger.info(" +++==========================fileList1 -- 查无该记录 ");
            map.put("result", "null");
            return map;
        }

    }

    /**
     * 交接统计
     * @param mav
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/query/jjList")
    @AclAnnotation(pUrl = "/query/jjList")
    public ModelAndView jjList(ModelAndView mav,String startTime,String endTime,@ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException, ParseException {
        //如果时间范围是旧的，就查旧数据，如果是新的，就查新数据，必须要有开始时间或者结束时间
        Page<Jjtj> page = (Page<Jjtj>) paginatedList.getModel();
        DataSet<Jjtj> datas =null;
        long count=0L;
        List<Jjtj> jjtjList=new ArrayList<Jjtj>();
        if((startTime==null||"".equals(startTime))&&(endTime==null||"".equals(endTime))){
            mav.addObject("jjtjList",jjtjList);
            mav.setViewName("query/jjList");
            return mav;
        }
        jjtjList=businessInfoService.getDeliveryAndSignList(startTime,endTime,page.getStartIndex(),page.getPageSize());
        count=businessInfoService.getDeliveryAndSignListCount(startTime,endTime,page.getStartIndex(),page.getPageSize());
        if(jjtjList!=null&&jjtjList.size()>0){
            datas=new DataSet<Jjtj>(jjtjList,count,count);
        }
        if(datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("jjtjList",jjtjList);
        mav.addObject("startTime",startTime);
        mav.addObject("endTime",endTime);
        mav.setViewName("query/jjList");
        return mav;
    }

    /**
     * 流水明细
     * @param mav
     * @param startTime
     * @param endTime
     * @return
     * @throws TException
     */
    @RequestMapping("/query/lsmxList")
    @AclAnnotation(pUrl = "/query/jjList")
    public ModelAndView lsmxList(ModelAndView mav, String startTime, String endTime, int jjlx, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException, ParseException {
        // List<Lsmx> lsmxList=null;
        /*if(sf.parse(endTime).compareTo(sf.parse("2017-09-29 00:00:00"))<0){
            lsmxList=businessInfoService.getOldLsmxList(startTime,endTime,jjlx);
        }else{*/
        //  lsmxList=businessInfoService.getNewLsmxList(startTime,endTime,jjlx);
        //}
        // mav.addObject("lsmxList",lsmxList);
        /*mav.addObject("jjlx", jjlx);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);
        mav.setViewName("query/lsmxList");
        return mav;*/
        logger.info("lsmxList");
        Page<Lsmx> page = (Page<Lsmx>) paginatedList.getModel();
        List<Lsmx> lsmxList = null;
        long lsmxListCount = 0L;
        DataSet<Lsmx> datas = null;

        lsmxList = businessInfoService.getNewLsmxList(startTime, endTime, jjlx, page.getStartIndex(), page.getPageSize());
        lsmxListCount = businessInfoService.getNewLsmxListCount(startTime, endTime, jjlx, page.getStartIndex(), page.getPageSize());
        if (lsmxList != null && lsmxList.size() > 0) {
            datas=new DataSet<Lsmx>(lsmxList,lsmxListCount,lsmxListCount);
        }
        if (datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }

        mav.addObject("lsmxList", lsmxList);
        mav.addObject("jjlx", jjlx);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);
        mav.setViewName("query/lsmxList");
        return mav;
    }


    /**
     * 档案明细
     *
     * @param mav
     * @param startTime
     * @param endTime
     * @return
     * @throws TException
     */
    @RequestMapping("/query/damxList")
    @AclAnnotation(pUrl = "/query/jjList")
    public ModelAndView damxList(ModelAndView mav, String startTime, String endTime, int jjlx, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException, ParseException {
        Page<Lsmx> page = (Page<Lsmx>) paginatedList.getModel();
        List<Lsmx> damxList = null;
        long damxListCount = 0L;
        DataSet<Lsmx> datas = null;
        /*if(sf.parse(endTime).compareTo(sf.parse("2017-09-29 00:00:00"))<0){
            damxList=businessInfoService.getOldDamxList(startTime,endTime,jjlx);
        }else{*/
        damxList = businessInfoService.getNewDamxList(startTime, endTime, jjlx, page.getStartIndex(), page.getPageSize());
        damxListCount = businessInfoService.getNewDamxListCount(startTime, endTime, jjlx, page.getStartIndex(), page.getPageSize());
        if (damxList != null && damxList.size() > 0) {
            datas=new DataSet<Lsmx>(damxList,damxListCount,damxListCount);
        }
        if (datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("damxList", damxList);
        mav.addObject("jjlx", jjlx);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);
        mav.setViewName("query/damxList");
        return mav;
    }

    /**
     * 问题档案列表
     * @param mav
     * @param startTime
     * @param endTime
     * @param paginatedList
     * @return
     * @throws TException
     */
    @RequestMapping("/query/wtdaList")
    @AclAnnotation(pUrl = "/query/wtdaList")
    public ModelAndView wtdaList(ModelAndView mav, String startTime, String endTime, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException {
        Page<Wtda> page = (Page<Wtda>) paginatedList.getModel();
        int startIndex = page.getStartIndex();
        int pageSize = page.getPageSize();
        List<Wtda> wtdaList = null;
        long wtdaListCount = 0l;
        //默认初始不作查询
        if ((startTime == null || "".equals(startTime) && (endTime == null || "".equals(endTime)))) {
            wtdaList = null;
            wtdaListCount = 0l;
        } else {
            wtdaList = businessInfoService.getWtdaList(startTime, endTime, page.getStartIndex(), page.getPageSize());
            wtdaListCount = businessInfoService.getWtdaListCount(startTime, endTime, page.getStartIndex(), page.getPageSize());
        }
        DataSet<Wtda> datas = null;
        if (wtdaList != null && wtdaList.size() > 0) {
            datas = new DataSet<Wtda>(wtdaList, wtdaListCount, wtdaListCount);
        }
        if (datas != null) {
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);
        mav.addObject("wtdaList", wtdaList);
        mav.setViewName("query/wtdaList");
        return mav;
    }


    /**
     * 金信融威签收明细
     * @param mav
     * @param startTime
     * @param endTime
     * @param jjlx
     * @return
     * @throws TException
     */
    @RequestMapping("/query/jxrwqsmxList")
    @AclAnnotation(pUrl = "/query/jjList")
    public ModelAndView jxrwwqsList(ModelAndView mav, String startTime, String endTime, int jjlx) throws TException {
        List<Jjtj> jjtjList = null;
        if ((startTime == null || "".equals(startTime)) && (endTime == null || "".equals(endTime))) {
            mav.addObject("jjtjList", jjtjList);
            mav.setViewName("query/jxrwjjList");
            return mav;
        }
        jjtjList = businessInfoService.getJxrwqsList(startTime, endTime);
        mav.addObject("jjlx", jjlx);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);
        mav.setViewName("query/jxrwjjList");
        return mav;
    }
}
