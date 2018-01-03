package com.igool.ssp.web.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.igool.rpc.db.model.thrift.BusinessInfo;
import com.igool.rpc.db.model.thrift.DeliveryInfo;
import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.rpc.db.service.thrift.BusinessInfoService;
import com.igool.rpc.db.service.thrift.ImageInfoService;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import com.igool.rpc.db.service.thrift.UserInfoService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.ConstantUtil;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.util.QRCodeUtil;
import com.igool.thrift.rpc.ThriftClientProxy;
import framework.pisces.data.DataSet;
import framework.pisces.data4j.redis.shared.SharedRedisTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author H J .
 * @date 2017/9/22.
 */
@Controller
@AclAnnotation(pUrl = "/hbda/businessInfo/aToBRegister")
public class BusinessInfoController {

    Logger logger = LoggerFactory.getLogger(BusinessInfoController.class);
    @Autowired
    BusinessInfoService businessInfoService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    SharedRedisTemplate sharedRedisTemplate;

    @AclAnnotation(pUrl = "/hbda/businessInfo/aToBRegister")
    @RequestMapping("/hbda/businessInfo/aToBRegister")
    public String aToBRegister() {
        return "businessInfo/aToBRegister";
    }

    @RequestMapping("/hbda/businessInfo/getBusinessInfoByLsh")
    @ResponseBody
    public Map getBusinessInfoByLsh(String code) {
        logger.info(" getBusinessInfoByLsh -begin  code : " + code);
        Map map = new HashMap();
        try {
            BusinessInfo businessInfo = businessInfoService.findBusinessInfoByCode(code);
            if (businessInfo.getCode() == null) {
                logger.info(" getBusinessInfoByLsh -- A转B登记 查无流水 ");
                map.put("result", "null");
                return map;
            }
            logger.info(" getBusinessInfoByLsh -- A转B登记 流水状态 " + businessInfo.getLszt());
            if (businessInfo.getLszt() == ConstantUtil.A_TO_B_DJ) {
                map.put("result", ConstantUtil.A_TO_B_DJ);
                return map;
            } else if (businessInfo.getLszt() == ConstantUtil.A_TO_B_QS || businessInfo.getLszt() == ConstantUtil.B_TO_A_DJ) {
                map.put("result", ConstantUtil.A_TO_B_QS);
                return map;
            }
            map.put("result", "data");
            map.put("data", businessInfo);
        } catch (TException e) {
            e.printStackTrace();
            map.put("result", "error");
            logger.error(" getBusinessInfoByLsh -- A转B登记异常 :" + e.getMessage());
        }
        logger.info(" getBusinessInfoByLsh -begin  end ");
        return map;
    }

    @RequestMapping("/hbda/businessInfo/addAToBRegister")
    @ResponseBody
    public Map addAToBRegister(String data, String box, HttpServletRequest request) {
        logger.info(" addAToBRegister -begin  box :" + box + " + data.length :" + data.length());
        Map map = new HashMap();
        try {
            String newJson = StringEscapeUtils.unescapeHtml4(data);
            List<BusinessInfo> businessInfos = JSON.parseArray(newJson, BusinessInfo.class);
            UserAndMenu userSession = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
            logger.info(" addAToBRegister userSession " + userSession);
            //添加一个5秒失效的KEY，如果5秒内有值，就不用操作；
            sharedRedisTemplate.boundValueOps(box + userSession.getUser().getUserId()).expire(KeyConstants.FIVE_SECOND, TimeUnit.SECONDS);
            boolean hasFlag = sharedRedisTemplate.boundValueOps(box + userSession.getUser().getUserId()).setIfAbsent(box + userSession.getUser().getUserId());
            DeliveryInfo deliveryInfo = businessInfoService.addAToBRegister(businessInfos, box, userSession.getUser().getUserId());
            if (deliveryInfo.getCode() != null && !deliveryInfo.getCode().equals("") && hasFlag) {
                String barcode15 = QRCodeUtil.encodeBar(deliveryInfo.getCode(), 110, 40);
                logger.info("addAToBRegister -- 添加（修改）完成 ");
                map.put("result", "OK");
                map.put("deliveryInfo", deliveryInfo);
                map.put("barcode15", barcode15);
                map.put("userName", userSession.getUser().getName());
            } else {
                map.put("result", "boxIsExist");
            }
        } catch (TException e) {
            e.printStackTrace();
            logger.error("addAToBRegister  TException--  " + e.getMessage());
            map.put("result", "NO");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addAToBRegister  TException--  " + e.getMessage());
            map.put("result", "NO");
        }
        logger.info(" addAToBRegister -end ");
        return map;
    }


    @AclAnnotation(pUrl = "/hbda/businessInfo/aToBSign")
    @RequestMapping("/hbda/businessInfo/aToBSign")
    public String aToBSign(String bs, String code, String box, String start, String end, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList, HttpServletRequest request) {
        logger.info(" aToBSign -begin   - bs : " + bs + "   code : " + code + "  box :" + box + "  start : " + start + "  end : " + end);
        if (bs != null && bs.equals("1")) {
            Page<DeliveryInfo> deliveryInfoDataSet = (Page<DeliveryInfo>) paginatedList.getModel();
            try {
                List<DeliveryInfo> deliveryInfos = businessInfoService.findDeliveryByCodeAndBoxAndDate(code, box, start, end, deliveryInfoDataSet.getStartIndex(), deliveryInfoDataSet.getPageSize());
                Long deliveryCount = businessInfoService.findDeliveryByCodeAndBoxAndDateCount(code, box, start, end, deliveryInfoDataSet.getStartIndex(), deliveryInfoDataSet.getPageSize());

                deliveryInfoDataSet.setData(deliveryInfos);
                deliveryInfoDataSet.setCount(deliveryCount.intValue());
                request.setAttribute("bs", bs);
                request.setAttribute("code", code);
                request.setAttribute("box", box);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        logger.info(" aToBSign -end ");
        return "businessInfo/aToBSign";
    }

    @RequestMapping("/hbda/businessInfo/ajaxLookSignDetailByCode")
    @ResponseBody
    public Map ajaxLookSignDetailByCode(String code, String bs) {
        Map map = new HashMap();
        logger.info(" ajaxLookSignDetailByCode -  begin  code ; " + code + " bs" + bs);
        try {
            List<BusinessInfo> businessInfos = businessInfoService.ajaxLookSignDetailByCode(code, bs);

            map.put("data", businessInfos);
        } catch (TException e) {
            e.printStackTrace();
            logger.error(" ajaxLookSignDetailByCode - error  " + e.getMessage());
        }
        logger.info(" ajaxLookSignDetailByCode -end ");
        return map;
    }

    @RequestMapping("/hbda/businessInfo/signBusinessInfoByLsh")
    @ResponseBody
    public Map signBusinessInfoByLsh(String code, String deliveryId, String signingId, HttpServletRequest request) {
        logger.info("signBusinessInfoByLsh -begin  code :" + code + "  deliveryId :" + deliveryId + "  signingId :" + signingId);
        Map map = new HashMap();

        try {
            BusinessInfo businessInfo = businessInfoService.findBoxByDeliveryIdAndLsh(code, deliveryId);
            if (businessInfo.getBusinessId() == 0) {
                map.put("result", "null");
            } else {
                businessInfo.setBox(signingId); //将signingId存入box方便读取
                UserAndMenu userAndMenu = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
                int jjzt = businessInfoService.signBoxInAToB(businessInfo, userAndMenu.getUser().getUserId(), deliveryId);
                if (jjzt == 1) {
                    map.put("result", "OK");
                } else {
                    map.put("data", businessInfo);
                    map.put("result", "data");
                }
            }
        } catch (TException e) {
            e.printStackTrace();
            map.put("result", "error");
        }
        logger.info("signBusinessInfoByLsh -end ");
        return map;
    }

    @AclAnnotation(pUrl = "/hbda/businessInfo/updateOrPrint")
    @RequestMapping("/hbda/businessInfo/updateOrPrint")
    public String updateOrPrint(String bs, String code, String queryStartTime, String queryEndTime, @ModelAttribute("paginatedList") PaginatedListAdapter paginatedList, HttpServletRequest request) {
        logger.info("updateOrPrint -begin  bs :" + bs + "  code :" + code);
        if (bs != null && bs.equals("1")) {
            Page<DeliveryInfo> deliveryInfoDataSet = (Page<DeliveryInfo>) paginatedList.getModel();
            UserAndMenu userAndMenu = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
            try {
                List<UserInfo> userInfos = userInfoService.getAllUser();
                List<DeliveryInfo> deliveryInfos =
                        businessInfoService.getBusinessInfoListByDateOrCodeRefUser(code, userAndMenu.getUser(), queryStartTime, queryEndTime, deliveryInfoDataSet.getStartIndex(), deliveryInfoDataSet.getPageSize(), userInfos);
                int count = businessInfoService.getBusinessInfoListByDateOrCodeRefUserCount(code, userAndMenu.getUser(), queryStartTime, queryEndTime, userInfos);

                deliveryInfoDataSet.setData(deliveryInfos);
                deliveryInfoDataSet.setCount(count);
                request.setAttribute("bs", bs);
                request.setAttribute("code", code);
                request.setAttribute("queryStartTime", queryStartTime);
                request.setAttribute("queryEndTime", queryEndTime);
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        logger.info("updateOrPrint -end ");
        return "businessInfo/updateOrPrint";
    }

    @RequestMapping("/hbda/businessInfo/updateBox")
    @ResponseBody
    public Map updateBox(String box, String beforeBox) {
        logger.info("updateBox -start  beforeBox : " + beforeBox + "  box:" + box);
        Map map = new HashMap();
        try {
            int i = businessInfoService.updateBox(box, beforeBox);
            if (i == 1) {
                map.put("result", "OK");
            } else {
                map.put("result", "NO");
            }
        } catch (TException e) {
            e.printStackTrace();
            map.put("result", "error");
        }
        logger.info("updateBox -end ");
        return map;
    }

    @AclAnnotation(pUrl = "/hbda/businessInfo/updateOrPrint")
    @RequestMapping("/hbda/businessInfo/updateOrPrintDetail")
    public String updateOrPrintDetail(String box, String deliveryId, String deliveryCode, String registerPersonName, String registerDate, String realJjlx, HttpServletRequest request) {

        try {
            List<BusinessInfo> businessInfos = businessInfoService.getBusinessInfoListByBox(box, deliveryId, deliveryCode);
            Map<String, BusinessInfo> map = new HashMap<>();
            for (BusinessInfo b : businessInfos) {
                map.put(b.getFileCode(), b);
            }
            String barcode15 = QRCodeUtil.encodeBar(deliveryCode, 110, 40);
            request.setAttribute("businessInfos", businessInfos);
            String json = JSON.toJSONString(map);
            request.setAttribute("businessInfoMap", json);
            request.setAttribute("box", box);
            request.setAttribute("deliveryId", deliveryId);
            request.setAttribute("deliveryCode", deliveryCode);
            request.setAttribute("registerPersonName", registerPersonName);
            request.setAttribute("registerDate", registerDate);
            request.setAttribute("barcode15", barcode15);
            request.setAttribute("realJjlx", realJjlx);
        } catch (TException e) {
            e.printStackTrace();
        }

        return "businessInfo/updateOrPrintDetail";
    }

    @RequestMapping("/hbda/businessInfo/ajaxDelDeliveryRel")
    @ResponseBody
    public Map ajaxDelDeliveryRel(String ids, String box, String deliveryId, String deliveryCode, HttpServletRequest request) {
        Map map = new HashMap();
        logger.info("ajaxDelDeliveryRel -begin  ids :" + ids + "  box :" + box + "   deliveryId :" + deliveryId + "   deliveryCode :" + deliveryCode);
        try {
            UserAndMenu userAndMenu = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
            businessInfoService.delDeliveryRel(ids, box, deliveryId, userAndMenu.getUser().getUserId(), deliveryCode);
            map.put("result", "OK");
        } catch (TException e) {
            e.printStackTrace();
            map.put("result", "error");
            logger.info("ajaxDelDeliveryRel -error : " + e.getMessage());
        }
        logger.info("ajaxDelDeliveryRel -end  ");
        return map;
    }


    @AclAnnotation(pUrl = "/hbda/businessInfo/aToBRegister")
    @RequestMapping("/hbda/businessInfo/businessInfoPrint")
    public String businessInfoPrint(String deliveryCode, String box, String imgSrc,
                                    String registerPerson, String registerDate, HttpServletRequest request) {

        try {
            logger.info("businessInfoPrint - print  ");
            List<BusinessInfo> businessInfos = null;
            businessInfos = businessInfoService.findFileInfoByBox(box);
            request.setAttribute("deliveryCode", deliveryCode);
            request.setAttribute("box", box);
            request.setAttribute("imgSrc", imgSrc);
            request.setAttribute("registerPerson", registerPerson);
            request.setAttribute("registerDate", registerDate);
            request.setAttribute("businessInfos", businessInfos);
        } catch (TException e) {
            e.printStackTrace();
        }
        return "businessInfo/businessInfoPrint";
    }

    @AclAnnotation(pUrl = "/hbda/businessInfo/exportBoxNotSignToExcelHtml")
    @RequestMapping("/hbda/businessInfo/exportBoxNotSignToExcelHtml")
    public String exportBoxNotSignToExcelHtml(HttpServletRequest request, HttpServletResponse response, String box, String bs) {
        return "businessInfo/exportBoxNotSignToExcel";
    }

    @AclAnnotation(pUrl = "/hbda/businessInfo/exportBoxNotSignToExcelHtml")
    @RequestMapping("/hbda/businessInfo/exportBoxNotSignToExcel")
    public void exportBoxNotSignToExcel(HttpServletRequest request, HttpServletResponse response, String box) {
        try {
            logger.info("exportBoxNotSignToExcel - begin  -- box : "+box);
            String fileName = box + "_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls";
            // 创建工作簿
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFCellStyle style = workBook.createCellStyle();
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
            //创建工作单
            HSSFSheet sheet = workBook.createSheet(fileName);
            sheet.setDefaultColumnWidth((short) 20);
            sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));
            sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 1));
            sheet.addMergedRegion(new Region(1, (short) 2, 1, (short) 5));
            //创建行
            HSSFRow row = sheet.createRow(0);
            row.setHeight((short) 600);
            //填充标题头信息
            //创建列
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            //填充数据
            cell.setCellValue("\"未签收\"流水需处理的数据清单");
            //创建行
            HSSFRow row1 = sheet.createRow(1);
            row1.setHeight((short) 500);
            HSSFCell row1Cell1 = row1.createCell(0);
            row1Cell1.setCellStyle(style);
            row1Cell1.setCellValue("箱号");
            HSSFCell row1Cell2 = row1.createCell(2);
            row1Cell2.setCellStyle(style);
            row1Cell2.setCellValue(box);

            HSSFRow row2 = sheet.createRow(2);
            row2.setHeight((short)500);
            HSSFCell row2Cell1 = row2.createCell(0);
            row2Cell1.setCellStyle(style);
            row2Cell1.setCellValue("序号");
            HSSFCell row2Cell2 = row2.createCell(1);
            row2Cell2.setCellStyle(style);
            row2Cell2.setCellValue("流水号");
            HSSFCell row2Cell3 = row2.createCell(2);
            row2Cell3.setCellStyle(style);
            row2Cell3.setCellValue("序号");
            HSSFCell row2Cell4 = row2.createCell(3);
            row2Cell4.setCellStyle(style);
            row2Cell4.setCellValue("流水号");
            HSSFCell row2Cell5 = row2.createCell(4);
            row2Cell5.setCellStyle(style);
            row2Cell5.setCellValue("序号");
            HSSFCell row2Cell6 = row2.createCell(5);
            row2Cell6.setCellStyle(style);
            row2Cell6.setCellValue("流水号");

            List<String> codes = businessInfoService.findNotSignCodeByBox(box);
            int rowNum=3;
            for(int i=0;i<codes.size();){

                HSSFRow rowi = sheet.createRow(rowNum++);
                rowi.setHeight((short)400);
                HSSFCell rowiCell1 = rowi.createCell(0);
                rowiCell1.setCellStyle(style);
                rowiCell1.setCellValue(i+1);
                HSSFCell rowiCell2 = rowi.createCell(1);
                rowiCell2.setCellStyle(style);
                rowiCell2.setCellValue(i>=codes.size()?"":codes.get(i++));

                if(i<codes.size()){
                    HSSFCell rowiCell3 = rowi.createCell(2);
                    rowiCell3.setCellStyle(style);
                    rowiCell3.setCellValue(i+1);
                    HSSFCell rowiCell4 = rowi.createCell(3);
                    rowiCell4.setCellStyle(style);
                    rowiCell4.setCellValue(i>=codes.size()?"":codes.get(i++));
                }

                if(i<codes.size()){
                    HSSFCell rowiCell5 = rowi.createCell(4);
                    rowiCell5.setCellStyle(style);
                    rowiCell5.setCellValue(i+1);
                    HSSFCell rowiCell6 = rowi.createCell(5);
                    rowiCell6.setCellStyle(style);
                    rowiCell6.setCellValue(i>=codes.size()?"":codes.get(i++));
                }
            }

            String userAgent = request.getHeader("user-agent");

            if (userAgent.toLowerCase().indexOf("msie") != -1) {
                fileName = URLEncoder.encode(fileName, "utf-8");
            } else {
                fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
            }
            //设置响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //将excel文件响应给客户
            workBook.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("exportBoxNotSignToExcel - UnsupportedEncodingException {} ",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("exportBoxNotSignToExcel - IOException {} ",e.getMessage());
        } catch (TException e) {
            e.printStackTrace();
            logger.error("exportBoxNotSignToExcel - TException {} ",e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("exportBoxNotSignToExcel - Exception {} ",e.getMessage());
        }
        logger.info("exportBoxNotSignToExcel - end ");
    }
}
