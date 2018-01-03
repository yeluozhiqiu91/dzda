package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.model.thrift.Dictionary;
import com.igool.rpc.db.service.thrift.DictionaryService;
import com.igool.rpc.db.service.thrift.ImageInfoService;
import com.igool.rpc.db.service.thrift.VehicleService;
import com.igool.ssp.web.util.ConstantsUtil;
import net.sf.json.JSONObject;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wang on 2017/11/15.
 */
@Controller
@RequestMapping("/vehicle")
public class VehicleController {
    Logger logger = LoggerFactory.getLogger(VehicleController.class);

    public static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    VehicleService vehicleService;
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    ImageInfoService imageInfoService;
    @Value("#{config['FILEURL']}")
    private String FILEURL;
    @RequestMapping("/queryPage")
    public ModelAndView queryPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vehicle/vehicle");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/findAllVehicle",produces = "application/json;charset=UTF-8")//要加这个produces，不然返回的是xml
    public JSONObject findAllVehicle(HttpServletRequest request, String sfz, String queryType, String companyName){
        //记录查询日志
        if(sfz!=null&&!sfz.equals("")){
            VehicleOperator vehicleOperator=new VehicleOperator();
            vehicleOperator.setSfz(sfz);
            vehicleOperator.setCompany(companyName);
            vehicleOperator.setCzlx(ConstantsUtil.OPERATORTYPE_CX);
            try {
                vehicleService.addVehicleOperator(vehicleOperator);
            } catch (TException e) {
                e.printStackTrace();
                logger.info("记录查询日志失败：sfz:"+sfz);
            }
        }
        JSONObject json=new JSONObject();
        List<FindRegister> findRegisterList=null;
        try {
            findRegisterList=vehicleService.getFindRegisterByTypeAndStr(sfz);
        } catch (TException e) {
            e.printStackTrace();
            logger.info("查询授权信息失败，sfz:"+sfz);
        }
        if(findRegisterList==null||findRegisterList.size()==0){
            json.put("result","fail");
            json.put("msg","您还未获得查询授权，请前往窗口办理授权");
            return json;
        }else{
            StringBuffer sfzs=new StringBuffer();
            StringBuffer hphms=new StringBuffer();
            StringBuffer hpzls=new StringBuffer();
            for(FindRegister f:findRegisterList){
                sfzs.append(f.getCarSfz().trim()).append(",");
                hphms.append(f.getHphm().trim()).append(",");
                hpzls.append(f.getHpzl().trim()).append(",");
            }
            sfzs.deleteCharAt(sfzs.length()-1);
            hphms.deleteCharAt(hphms.length()-1);
            hpzls.deleteCharAt(hpzls.length()-1);
            try {
                List<VehicleDetailDTO> vehicleDetailDTOList = vehicleService.findAllVehicleBySfz(sfzs.toString(),hphms.toString(),hpzls.toString());
                if (vehicleDetailDTOList != null && vehicleDetailDTOList.size() > 0) {
                    List<Dictionary> allDictionary = dictionaryService.findAllDictionary();
                    Map<String, String> hpzlMap = new HashMap<String,String>();//号牌种类
                    Map<String, String> cllxMap = new HashMap<String,String>();//车辆类型
                    Map<String, String> csysMap = new HashMap<String,String>();//车身颜色
                    Map<String, String> syxzMap = new HashMap<String,String>();//使用性质
                    Map<String, String> ztMap = new HashMap<String,String>();//机动车状态
                    fullFillMap(allDictionary, hpzlMap, cllxMap, csysMap, syxzMap, ztMap);
                    for (VehicleDetailDTO v : vehicleDetailDTOList) {
                        List<VehicleFlow> flowList=v.getVehicleflow();
                        if(flowList!=null&&flowList.size()>0){
                            List<Business> businessList=new ArrayList<Business>();
                            int i=1;
                            for(VehicleFlow ve:flowList){
                                Business business=new Business();
                                business.setIncrement(String.valueOf(i));
                                business.setName(ConstantsUtil.businessType.get(ve.getYwlx()));
                                business.setNumber(ve.getLsh());
                                business.setPlateNo(v.getPlateNo());
                                business.setTime(ve.getSqrq());
                                businessList.add(business);
                                i++;
                            }
                            if(businessList.size()>0){//这个用来判断此条流水是否已影像，已影像的才在前端显示“影像查看”按钮
                                List<ImageInfo> imageInfos=vehicleService.findAllImageInfoByBusinesses(businessList);
                                for(ImageInfo imageInfo:imageInfos){
                                    for(Business b:businessList){
                                        if(b.getNumber().equals(imageInfo.getCode())){
                                            b.setIsImage(1);
                                        }
                                    }
                                }
                                v.setBusiness(businessList);
                            }
                        }
                        //为了显示星号，需要处理一下
                        v.setNameHide(transName(v.getName()));
                        v.setVinHide(transClsbdh(v.getVin()));
                        v.setEngineNoHide(transFdjh(v.getEngineNo()));
                        v.setMobileHide(transSjhm(v.getMobile()));

                        for(FindRegister f:findRegisterList){
                            if(f.getHphm().equals(v.getPlateNo())&&f.getHpzl().equals(v.getPlateType())){
                                v.setSyq(f.getType());
                                break;
                            }
                        }
                        //将号牌种类、车辆类型、车身颜色、使用性质、机动车状态转为中文
                        v.setPlateType(hpzlMap.get(v.getPlateType()));
                        v.setVehicleType(cllxMap.get(v.getVehicleType()));
                        v.setCarColor(getCsys(csysMap, v.getCarColor()));//颜色可能有多种
                        v.setUseType(syxzMap.get(v.getUseType()));
                        v.setCarStatus(getZt(ztMap, v.getCarStatus()));//机动车状态可能有多种
                        v.setPlateNo("鄂"+v.getPlateNo());
                    }
                    json.put("data", vehicleDetailDTOList);
                    json.put("querySfz",sfz);
                    json.put("queryType",queryType);
                    json.put("companyName",companyName);
                    json.put("result", "SUCCESS");
                    return json;
                }else{
                    json.put("result", "fail");
                    json.put("msg","未查询到车辆");
                    return json;
                }
            }catch (Exception e){
                e.printStackTrace();
                json.put("result", "fail");
                json.put("msg","查询出现异常，请联系管理员");
                return json;
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/findAllImage",produces = "application/json;charset=UTF-8")
    public JSONObject findAllImage(HttpServletRequest request,String hphm,String ywlx,String lsh,String sqsj){
        JSONObject json=new JSONObject();
        ImageDetailDTO imageDetailDTO=new ImageDetailDTO();
        imageDetailDTO.setPlateNo(hphm);
        imageDetailDTO.setName(ywlx);
        imageDetailDTO.setNumber(lsh);
        imageDetailDTO.setTime(sqsj);
        try{
            List<ImageDetail> imageDetails=vehicleService.findAllImageByLsh(lsh);
            if(imageDetails!=null&&imageDetails.size()>0){
                for(ImageDetail i:imageDetails){
                    if(i.getSrc()==null||i.getSrc().equals("")){
                        i.setSrc(FILEURL+i.getImageCode());
                    }
                    i.setPrimarySrc(i.getSrc());
                }
                imageDetailDTO.setPic(imageDetails);
            }
            json.put("data",imageDetailDTO);
            json.put("result","SUCCESS");
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            json.put("result","fail");
            return json;
        }
    }

    private void fullFillMap(List<Dictionary> allDictionary, Map<String, String> hpzlMap, Map<String, String> cllxMap, Map<String, String> csysMap, Map<String, String> syxzMap, Map<String, String> ztMap) {
        if(allDictionary!=null&&allDictionary.size()>0){
            for(Dictionary d:allDictionary){
                switch (d.getType()){
                    case "plateType":
                        hpzlMap.put(d.getValue(),d.getName());
                        break;
                    case "carType":
                        cllxMap.put(d.getValue(),d.getName());
                        break;
                    case "color":
                        csysMap.put(d.getValue(),d.getName());
                        break;
                    case "useType":
                        syxzMap.put(d.getValue(),d.getName());
                        break;
                    case "jdczt":
                        ztMap.put(d.getValue(),d.getName());
                        break;
                    default:
                        break;
                }
            }
        }
    }


    //显示姓名最后一位，其余为星号
    public static String transName(String name){
        if(name==null){
            return "";
        }else{
            if(name.length()<=1){
                return name;
            }else{
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<name.length()-1;i++){
                    sb.append("*");
                }
                sb.append(name.charAt(name.length()-1));
                return sb.toString();
            }
        }
    }
    //车辆识别代号（显示后六位，其余为星号)
    public static String transClsbdh(String str){
        if(str==null){
            return "";
        }else{
            if(str.length()<=6){
                return str;
            }else{
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<str.length()-6;i++){
                    sb.append("*");
                }
                sb.append(str.substring(str.length()-6,str.length()));
                return sb.toString();
            }
        }

    }
    //发动机号首位为*号
    public static String transFdjh(String str){
        if(str==null){
            return "";
        }else{
            if(str.length()<=1){
                return str;
            }else{
                return "*"+str.substring(1);
            }
        }
    }
    //手机号码（显示前三位和后四位，中间的位数用星号表示）
    public static String transSjhm(String str){
        if(str==null){
            return "";
        }else{
            if(str.length()<=7){
                return str;
            }else{
                StringBuffer sb=new StringBuffer();
                sb.append(str.substring(0,3));
                for(int i=0;i<str.length()-7;i++){
                    sb.append("*");
                }
                sb.append(str.substring((str.length()-4),(str.length())));
                return sb.toString();
            }
        }
    }

    public static String getCsys(Map<String,String> map,String str){
        if(str!=null&&str.length()==1){
            return map.get(str);
        }else{
            if(str!=null&&str.length()>1){
                StringBuffer sb=new StringBuffer();
                String[] arr=str.split("");
                for(int i=0;i<arr.length;i++){
                    if(!arr[i].equals("")){
                        sb.append(map.get(arr[i]));
                    }
                }
                return sb.toString();
            }else{
                return "";
            }
        }
    }
    //多种车身颜色拼接到一起,或者多种机动车状态拼接到一起
    public static String getZt(Map<String,String> map,String str){
        if(str!=null&&str.length()==1){
            return map.get(str);
        }else{
            if(str!=null&&str.length()>1){
                StringBuffer sb=new StringBuffer();
                String[] arr=str.split("");
                for(int i=0;i<arr.length;i++){
                    if(!arr[i].equals("")){
                        sb.append(map.get(arr[i]));
                        if(i!=arr.length-1){
                            sb.append(",");
                        }
                    }
                }
                return sb.toString();
            }else{
                return "";
            }
        }
    }


    /*@ResponseBody
    @RequestMapping("/findAllVehicleTest")
    public JSONObject findAllVehicleTest(HttpServletRequest request, String sfz){
        JSONObject json=new JSONObject();
        List<VehicleDetailDTO> vehicleDetails=new ArrayList<>();
        List<Business> businessList=new ArrayList<>();
        VehicleDetailDTO vehicleDetail1=new VehicleDetailDTO();
        Business business1=new Business();
        business1.setId("21");
        business1.setIncrement("1");
        business1.setName("补牌换证");
        business1.setNumber("201648277278");
        business1.setTime("2017-03-20");

        Business business2=new Business();
        business2.setId("21");
        business2.setIncrement("1");
        business2.setName("补牌换证");
        business2.setNumber("201648277278");
        business2.setTime("2017-03-20");
        businessList.add(business1);
        businessList.add(business2);
        vehicleDetail1.setBusiness(businessList);
        vehicleDetail1.setName("**军");
        vehicleDetail1.setPlateNo("鄂A76650");
        vehicleDetail1.setVin("LFV328R98113");
        vehicleDetail1.setEngineNo("09817");
        vehicleDetail1.setPlateType("号牌种类");
        vehicleDetail1.setVehicleType("小型普通客车");
        vehicleDetail1.setBrands("五菱");
        vehicleDetail1.setModel("五菱牌FV98iPY");
        vehicleDetail1.setCarColor("carColor");
        vehicleDetail1.setUseType("家用");
        vehicleDetail1.setCarStatus("状态");
        vehicleDetail1.setRegisterDate("2010-10-22");
        vehicleDetail1.setPeriod("2013-10-22");
        vehicleDetail1.setMobile("15922110978");
        vehicleDetails.add(vehicleDetail1);
        vehicleDetails.add(vehicleDetail1);
        json.put("result","SUCCESS");
        json.put("data",vehicleDetails);
        return json;
    }*/

    /*@ResponseBody
    @RequestMapping("/findAllImageTest")
    public JSONObject findAllImageTest(HttpServletRequest request,String hphm,String ywlx,String lsh,String sqsj){
        JSONObject json=new JSONObject();
        ImageDetailDTO imageDetailDTO=new ImageDetailDTO();
        imageDetailDTO.setPlateNo(hphm);
        imageDetailDTO.setName(ywlx);
        imageDetailDTO.setNumber(lsh);
        imageDetailDTO.setTime(sqsj);
        List<ImageDetail> imageDetailList=new ArrayList<>();
        ImageDetail imageDetail1=new ImageDetail();
        imageDetail1.setName("资料目录");
        *//*imageDetail1.setImageCode("00120171103860e76bb");*//*
        imageDetail1.setSrc("http://localhost:8680/file/filedownload?fileId=00120171103860e76bb");
        ImageDetail imageDetail2=new ImageDetail();
        imageDetail1.setName("受理凭证");
        *//*imageDetail1.setImageCode("002201711037710ee8e");*//*
        imageDetail1.setSrc("http://localhost:8680/file/filedownload?fileId=002201711037710ee8e");
        imageDetailList.add(imageDetail1);
        imageDetailList.add(imageDetail2);
        imageDetailDTO.setPic(imageDetailList);
        json.put("data",imageDetailDTO);
        json.put("result","SUCCESS");
        logger.info("b.json"+json.toString());
        return json;
    }*/
}
