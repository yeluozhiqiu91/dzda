package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.FileInfoService;
import com.igool.rpc.db.service.thrift.ImageInfoService;
import com.igool.rpc.db.service.thrift.PlateTypeService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.ConstantUtil;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.util.StringUtils;
import framework.pisces.data.DataSet;
import org.apache.thrift.TException;
import org.jdal.dao.Page;
import org.jdal.web.table.PaginatedListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/10/11.
 */
@AclAnnotation(pUrl = "hbda/auth/Image/showSmy")
@Controller
public class ImageController {
    Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    BusinessInfoService businessInfoService;
    @Autowired
    PlateTypeService plateTypeService;
    @Autowired
    ImageInfoService imageInfoService;
    @Autowired
    FileInfoService fileInfoService;
    @RequestMapping("hbda/auth/Image/showSmy")
    public ModelAndView showLsPage(ModelAndView mav, String businessCode, HttpServletRequest request){
        UserAndMenu userSession = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        //先在本地找，未找到就到同步库找并同步过来,在impl中做
        BusinessInfo businessInfo=null;
        if(businessCode!=null&&!businessCode.equals("")){
            //如果流水号是11位，就转为13位
            if(businessCode.length()==11){
                businessCode="1A"+businessCode.substring(1)+"0";
            }
            try {
                businessInfo=businessInfoService.findBusinessInfoByCode(businessCode);
                //通过判断是否有id来判断是从同步库查到的，还是镜像库查到的
                if(businessInfo==null||businessInfo.getCode()==null){
                    mav.addObject("msg","未查询到流水号");
                }else{
                    if(businessInfo.getBusinessId()==0){//本地没有，需要添加到本地
                        //本地根据档案编号查档案，若本地无档案则创建档案记录
                        FileInfo fileInfo=fileInfoService.findFileInfoByFileCode(businessInfo.getFileCode());
                        Integer fileId=fileInfo.getFileId();
                        if(fileInfo.getFileId()==0){//本地无档案
                            businessInfoService.addFileInfo(businessInfo);
                            fileInfo=fileInfoService.findFileInfoByFileCode(businessInfo.getFileCode());
                        }
                        businessInfo.setFileId(fileInfo.getFileId());
                        businessInfo.setLszt(0);
                        businessInfo.setLswz(ConstantUtil.LSWZ_A);
                        businessInfo.setQsStatus(ConstantUtil.QS_STATUS_ZC);
                        businessInfoService.saveBusinessInfo(businessInfo);
                    }
                    if(businessInfo.getLswz()==ConstantUtil.LSWZ_A){//在A库不能影像
                        mav.addObject("lswz","A");
                    }
                    businessInfo=businessInfoService.findNativeBusinessInfoByLsh(businessCode);
                    //得到业务类型和号牌种类
                    mav.addObject("businessInfo",businessInfo);
                }
            } catch (TException e) {
                e.printStackTrace();
                logger.info("查询流水信息异常，流水号："+businessCode+","+e.getMessage());
                mav.addObject("msg","查询流水信息异常");
            }
        }
        mav.addObject("userId",userSession.getUser().getUserId());
        mav.setViewName("image/showLsPage");
        return mav;
    }

    @RequestMapping("/hbda/auth/Image/yxcx")
    @AclAnnotation(pUrl = "/hbda/auth/Image/yxcx")
    public ModelAndView imageQuery(ModelAndView mav, HttpServletRequest request, @ModelAttribute BusinessInfo businessInfo, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException {
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        Page<BusinessInfo> page = (Page<BusinessInfo>) paginatedList.getModel();
        List<BusinessInfo> businessInfoList = null;
        long businessInfoCount = 0L;
        DataSet<BusinessInfo> datas =null;
        String plateType = StringUtils.toStringByNotNull(businessInfo.getPlateType());
        String plateCode = StringUtils.toStringByNotNull(businessInfo.getPlateCode());
        String fileCode = StringUtils.toStringByNotNull(businessInfo.getFileCode());
        String carCode = StringUtils.toStringByNotNull(businessInfo.getCarCode());
        String code = StringUtils.toStringByNotNull(businessInfo.getCode());
        if (!plateType.equals("") || !plateCode.equals("") || !fileCode.equals("") || !carCode.equals("") || !code.equals("")) {
            businessInfoList=businessInfoService.getBusinessInfoListForImage(businessInfo,page.getStartIndex(),page.getPageSize());

            businessInfoCount=businessInfoService.getBusinessInfoListForImageCount(businessInfo,page.getStartIndex(),page.getPageSize());
        }
        if(businessInfoList!=null&&businessInfoList.size()>0){
            datas=new DataSet<BusinessInfo>(businessInfoList,businessInfoCount,businessInfoCount);
        }
        if(datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("businessList",businessInfoList);
        List<PlateType> plateTypeList=plateTypeService.getAllPlateType();
        mav.addObject("plateTypeList",plateTypeList);
        mav.addObject("businessInfo",businessInfo);
        mav.addObject("userId",userAndMenu.getUser().getUserId());
        mav.setViewName("image/yxcx");
        return mav;

    }

    @RequestMapping("/hbda/auth/Image/smlstj")
    @AclAnnotation(pUrl = "/hbda/auth/Image/smlstj")
    public String smlstj() throws TException {
        return "image/smlstj";
    }

    @RequestMapping("/hbda/auth/Image/ajaxSmlstj")
    @ResponseBody
    public Map ajaxSmlstj( String startTime, String endTime, HttpServletRequest request) {
        logger.info("ajaxSmlstj -begin  startTime :" + startTime + "  endTime :" + endTime);
        Map map = new HashMap();
        BusinessImageCount businessImageCount= null;
        try {
            businessImageCount = imageInfoService.findBusinessImageCount(startTime,endTime);
            map.put("result","OK");
            map.put("data",businessImageCount);

        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info("ajaxSmlstj -error :"+e.getMessage());
        }
        logger.info("ajaxSmlstj -end  ");
        return map;
    }

    @RequestMapping("/hbda/auth/Image/ajaxStreamNum")
    @ResponseBody
    public Map ajaxStreamNum( String startTime, String endTime, HttpServletRequest request) {
        logger.info("ajaxStreamNum -begin  startTime :" + startTime + "  endTime :" + endTime);
        Map map = new HashMap();
        try {
            List<ImageInfo> imageInfos = imageInfoService.ajaxStreamNum(startTime,endTime);
            map.put("result","OK");
            map.put("data",imageInfos);

        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info("ajaxStreamNum -error :"+e.getMessage());
        }
        logger.info("ajaxStreamNum -end  ");
        return map;
    }

    @RequestMapping("/hbda/auth/Image/ajaxFileCodeDetail")
    @ResponseBody
    public Map ajaxFileCodeDetail( String startTime, String endTime, HttpServletRequest request) {
        logger.info("ajaxFileCodeDetail -begin  startTime :" + startTime + "  endTime :" + endTime);
        Map map = new HashMap();
        try {
            List<BusinessInfo> businessInfos = imageInfoService.fileCodeDetail(startTime,endTime);
            map.put("result","OK");
            map.put("data",businessInfos);

        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info("ajaxFileCodeDetail -error :"+e.getMessage());
        }
        logger.info("ajaxFileCodeDetail -end  ");
        return map;
    }

    @RequestMapping("/hbda/auth/Image/smrytj")
    @AclAnnotation(pUrl = "/hbda/auth/Image/smrytj")
    public String smrytj(String bs,String startTime,String endTime,HttpServletRequest request) {
        logger.info("smrytj -begin  startTime :" + startTime + "  endTime :" + endTime + "   bs : " + bs);
        if(bs != null && bs.equals("1")){
            try {
                List<UserImageCount> userImageCountList = imageInfoService.userImageCount(startTime,endTime);
                int num = imageInfoService.findImagePhotoNumByDate(startTime,endTime);
                request.setAttribute("userImageCountList",userImageCountList);
                request.setAttribute("imagePhotoNum",num);
                request.setAttribute("startTime",startTime);
                request.setAttribute("endTime",endTime);
                request.setAttribute("bs",bs);
            } catch (TException e) {
                e.printStackTrace();
                logger.info("smrytj -error :"+e.getMessage());
            }
        }
        logger.info("smrytj -end  ");
        return "image/smrytj";
    }

    @RequestMapping("/hbda/auth/Image/ajaxBusinessNumDetail")
    @ResponseBody
    public Map ajaxBusinessNumDetail( String startTime, String endTime,String userId, HttpServletRequest request) {
        logger.info("ajaxBusinessNumDetail -begin  startTime :" + startTime + "  endTime :" + endTime + "  userId :" + userId);
        Map map = new HashMap();
        try {
            List<ImageInfo> imageInfos = imageInfoService.businessNumDetailByDateAndUserId(startTime,endTime,userId);
            map.put("result","OK");
            map.put("data",imageInfos);

        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info("ajaxBusinessNumDetail -error :"+e.getMessage());
        }
        logger.info("ajaxBusinessNumDetail -end  ");
        return map;
    }

    @RequestMapping("/hbda/auth/Image/ajaxDriverNumDetail")
    @ResponseBody
    public Map ajaxDriverNumDetail( String startTime, String endTime,String userId, HttpServletRequest request) {
        logger.info("ajaxDriverNumDetail -begin  startTime :" + startTime + "  endTime :" + endTime + "  userId :" + userId);
        Map map = new HashMap();
        try {
            List<BusinessInfo> businessInfos = imageInfoService.driverNumDetail(startTime,endTime,userId);
            map.put("result","OK");
            map.put("data",businessInfos);

        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info("ajaxDriverNumDetail -error :"+e.getMessage());
        }
        logger.info("ajaxDriverNumDetail -end  ");
        return map;
    }
}
