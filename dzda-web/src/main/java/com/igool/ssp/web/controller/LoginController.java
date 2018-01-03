package com.igool.ssp.web.controller;

import com.facebook.swift.service.ThriftClient;
import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.rpc.db.service.thrift.UserInfoService;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.thrift.rpc.ThriftClientProxy;
import com.stnts.common.util.encryption.MD5;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import framework.pisces.web4j.servlet.utils.RequestUtil;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2017/9/7.
 */
@Controller
public class LoginController {
    Logger logger= LoggerFactory.getLogger(LoginController.class);
    public static final String MEMBER="member";
    @Autowired
    UserInfoService userInfoService;
    @RequestMapping(value ="/login")
    public ModelAndView login(ModelAndView mav){
        //mav.setViewName("forward:/WEB-INF/views/login.jsp");
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value ="/ajaxSbmi")
    @ResponseBody
    public Map<String,String> ajaxSbmi(@RequestParam("username")String account, @RequestParam("password")String pwd,
                                       @RequestParam(value = "verifycode",defaultValue = "")String verify, HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","0");
        map.put("message","成功");
        String verifyCode = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if( verify.equalsIgnoreCase(verifyCode) )
        {
            UserInfo user = null;
            try {
                user = userInfoService.findUserByAccountAndPwd(account, MD5.md5crypt(pwd));
            } catch (TException e) {
                logger.error("数据库连接接异常");
                e.printStackTrace();
            }
            if( user == null )
            {
                map.put("code","1");
                map.put("message","账号或密码错误!");
            }
            else
            {
                if( user.getStatus() == 1 )
                {
                    UserAndMenu userInfo=new UserAndMenu();
                    userInfo.setUser(user);
                    request.getSession().setAttribute(MEMBER, userInfo);
                }
                else
                {
                    map.put("code","1");
                    map.put("message","账号未启用!");
                }


            }
        }
        else
        {
            map.put("code","1");
            map.put("message","验证码错误!");
        }
        return map;
    }

    @RequestMapping(value ="/logout")
    public String exit(HttpServletRequest request){
        UserAndMenu user = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        if(user != null)
        {
            String ip = RequestUtil.getProxyIp(request);
            logger.info("获取操作IP"+ ip);
            String agent  = request.getHeader("user-agent");
            logger.info("获取客户端浏览器和操作"+ agent );
            UserAgent userAgent = new UserAgent( agent );
            Browser brower = userAgent.getBrowser();
            Version version = userAgent.getBrowserVersion();
            String ver = version.getVersion();
            logger.info("获取客户端浏览器的版本号"+ ver );
            request.getSession().removeAttribute(KeyConstants.MEMBER);

        }else {
            logger.info("用户退出日志记录失败");
        }
        return "login";
    }
}
