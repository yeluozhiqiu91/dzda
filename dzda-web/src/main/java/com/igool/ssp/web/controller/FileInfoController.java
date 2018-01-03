package com.igool.ssp.web.controller;

import com.alibaba.fastjson.JSON;
import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.BusinessTypeDetail;
import com.igool.rpc.db.model.thrift.DeliveryInfo;
import com.igool.rpc.db.model.thrift.FileInfo;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.BusinessTypeService;
import com.igool.rpc.db.service.thrift.FileInfoService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.ConstantUtil;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.util.QRCodeUtil;
import com.igool.thrift.rpc.ThriftClientProxy;
import org.apache.commons.lang3.StringEscapeUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @author H J .
 * @date 2017/9/22.
 */
@Controller
@AclAnnotation(pUrl = "/hbda/fileInfo/fileInfoCheck")
public class FileInfoController {

    Logger logger = LoggerFactory.getLogger(FileInfoController.class);
    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    BusinessInfoService businessInfoService;

    @AclAnnotation(pUrl = "/hbda/fileInfo/fileInfoCheck")
    @RequestMapping("/hbda/fileInfo/fileInfoCheck")
    public String fileInfoCheck(String bs,String code,HttpServletRequest request) {
        logger.info(" fileInfoCheck -begin ");
        if (bs != null && bs.equals("1")) {

            try {
                Map<FileInfo,List<BusinessInfo>> map = fileInfoService.findFileInfoAndBusinessInfoByFileCode(code);
                Set<FileInfo> set = map.keySet();
                Iterator<FileInfo> iterator = set.iterator();
                FileInfo fileInfo = null;
                while (iterator.hasNext()){
                    fileInfo = iterator.next();
                }
                if (fileInfo!=null){
                    List<BusinessInfo> businessInfos = map.get(fileInfo);
                    request.setAttribute("fileInfo",fileInfo);
                    request.setAttribute("businessInfos",businessInfos);
                }
                request.setAttribute("bs",bs);
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        logger.info(" fileInfoCheck -end ");
        return "fileInfo/fileInfoCheck";
    }


    @RequestMapping("/hbda/fileInfo/updateLshStatus")
    @ResponseBody
    public Map updateLshStatus(String type,String businessId,String fileId) {
        Map map = new HashMap();
        logger.info(" updateLshStatus -begin  type : "+type +"  businessId : "+businessId + "  fileId : "+ fileId);
        try {
            fileInfoService.updateLshStatus(type,businessId,fileId);
            map.put("result","OK");
        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
            logger.info(" updateLshStatus -error : "+e.getMessage());
        }
        logger.info(" updateLshStatus -end ");
        return map;
    }

   /*
   *   ajax 获取缺失资料
   * */
    @RequestMapping("/hbda/fileInfo/getBusinessTypeDetailByBusinessType")
    @ResponseBody
    public Map getBusinessTypeDetailByBusinessType(String businessId,String businessType) {
        Map map = new HashMap();

        try {
           List<BusinessTypeDetail> businessTypeDetails = fileInfoService.getBusinessTypeDetailByBusinessType(businessId,businessType);

            map.put("result","OK");
            map.put("data",businessTypeDetails);
        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
        }

        return map;
    }

    /*
    *   ajax 修改缺失资料
    * */
    @RequestMapping("/hbda/fileInfo/updateQsZl")
    @ResponseBody
    public Map updateQsZl(String businessId,String businessDetailIds,String fileId,HttpServletRequest request) {
        Map map = new HashMap();

        try {
            UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
            businessInfoService.updateQsZl(businessId,businessDetailIds,userAndMenu.getUser().getUserId(),fileId);
            map.put("result","OK");
        } catch (TException e) {
            e.printStackTrace();
            map.put("result","NO");
        }

        return map;
    }

}
