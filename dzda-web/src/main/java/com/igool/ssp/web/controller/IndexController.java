package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.thrift.rpc.ThriftClientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wang on 2017/9/8.
 */
@Controller
@AclAnnotation(pUrl="/index",description="aaa")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value ="/")
    public String index(){
        return "redirect:index";
    }

    /**
     * 首页
     * @param view
     * @param request
     * @return
     */
    @RequestMapping(value ="/index")
    public ModelAndView homePage(ModelAndView view, HttpServletRequest request){

        UserAndMenu userSession = (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        view.setViewName("index");
        return view;
    }
}
