package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.FindRegister;
import com.igool.rpc.db.model.thrift.Vehicle;
import com.igool.rpc.db.service.thrift.CarInfoRegisterService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.util.ConstantsEnum;
import com.igool.util.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AclAnnotation(pUrl = "/hbda/carInfo/carInfoRegister")
/*@RequestMapping("/hbda/carInfo")*/
public class CarInfoRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(CarInfoRegisterController.class);

	@Autowired
	CarInfoRegisterService carInfoRegisterService;

	@RequestMapping(value = "/hbda/carInfo/carInfoRegister")
	public ModelAndView carInfoRegister(HttpServletRequest request) throws BizException {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("carInfo/carInfoRegister");

		return modelAndView;
	}

	@RequestMapping(value = "/hbda/carInfo/ajaxCarInfo")
	@ResponseBody
	public Map ajaxCarInfo(HttpServletRequest request,String singleSfz,String name,String registertType) {
		logger.info("  ajaxCarInfo  begin--  singleSfz : "+singleSfz+"  name : "+name);
		Map map = new HashMap();

		try {
			List<Vehicle> vehicles = carInfoRegisterService.findCarInfoBySfzAndName(singleSfz,name,registertType);
			if(vehicles.size()>0){
				if( registertType.equals("1") ){
					List<FindRegister> findRegisterLsit =	carInfoRegisterService.findCarInfoByVehicles(vehicles,singleSfz);
					for(FindRegister f :findRegisterLsit){
						for(Vehicle v :vehicles){
							if(f.getHphm().equals(v.getHphm()) && f.getLetSfz().equals(v.getSfzmhm()) && f.getHpzl().equals(v.getHpzl())){
								v.setIsLet(1);
							}
						}
					}
				}
				for(Vehicle v :vehicles){
						v.setHpzl(ConstantsEnum.Hpzl.getHpzlByType(v.getHpzl()).name());
				}
			}
			map.put("data",vehicles);
		}catch (Exception e){
			e.printStackTrace();
			if(e instanceof  BizException){
				BizException bizException = (BizException)e;
				map.put("result",bizException.getMessage());
			}
			logger.error("ajaxCarInfo error  {}",e.getMessage());
		}
		logger.info("  ajaxCarInfo   end");
		return map;
	}

	@RequestMapping(value = "/hbda/carInfo/ajaxAddAccredit")
	@ResponseBody
	public Map ajaxAddAccredit(HttpServletRequest request,String registertType,String accreditSfz,String hphms,String unitName,String carSfzs,String hpzls) {
		logger.info("  ajaxAddAccredit  begin--  registertType : "+registertType+"  accreditSfz : "+accreditSfz+"  hphms : "+hphms+"  unitName : "+unitName+"  carSfzs : "+carSfzs+"  hpzls : "+hpzls);
		Map map = new HashMap();

		FindRegister findRegister = new FindRegister();
		findRegister.setCreateTime(new Date()+"");
		findRegister.setType(Integer.parseInt(registertType));
		findRegister.setUnitName(unitName);
		findRegister.setLetSfz(accreditSfz);
		findRegister.setHpzl(hpzls);
		findRegister.setHphm(hphms);
		findRegister.setCarSfz(carSfzs);
		UserAndMenu userSession = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
//		String sessionId = request.getSession().getId();
//		UserDTO userDTO = (UserDTO) SessionUtil.getAttr(sessionId, "userDTO");
		findRegister.setUserId(userSession.getUser().getUserId());

		try {
			List<FindRegister> findRegisterLsit =	carInfoRegisterService.findCarInfoByFindRegister(accreditSfz,hphms,carSfzs,hpzls);
			if(findRegisterLsit.size()==0){
				carInfoRegisterService.addAccredit(findRegister);
				logger.info("  ajaxAddAccredit   添加成功");
				map.put("result","OK");
			}else {
//				String[] strings = carCode.split(",");
				String data="";
				for(FindRegister f :findRegisterLsit){
					if(accreditSfz.equals(f.getLetSfz())){
						data +=f.getHphm()+",";
					}
				}
				logger.info("  ajaxAddAccredit   添加失败！ 重复授权");
				map.put("result","NO");
				map.put("data",data);
			}
		}catch (Exception e){
			e.printStackTrace();
			logger.info("  ajaxAddAccredit   error {}",e.getMessage());
			map.put("result","ERROR");
		}
		logger.info("  ajaxAddAccredit   end");
		return map;
	}



}
