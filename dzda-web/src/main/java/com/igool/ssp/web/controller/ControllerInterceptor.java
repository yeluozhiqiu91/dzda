package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.model.MenuItem;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.service.ACLService;
import com.igool.ssp.web.service.MenuService;
import framework.pisces.core.exception.AuthorizationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.security.acl.Acl;
import java.util.*;

/**
 * Created by wang on 2017/9/8.
 */
public class ControllerInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
    @Autowired
    private MenuService menuService;
    @Autowired
    private ACLService aclService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        logger.info("ControllerInterceptor.preHandle()");
        HttpSession session = request.getSession();
        if (handler instanceof ResourceHttpRequestHandler || handler instanceof ParameterizableViewController) {
            return true;
        }

        logger.info("user session {}",session.getAttribute(KeyConstants.MEMBER));

        HandlerMethod headMethod = (HandlerMethod) handler;

        if (session != null && session.getAttribute(KeyConstants.MEMBER) != null) {
            UserAndMenu user = (UserAndMenu) session.getAttribute(KeyConstants.MEMBER);

            String[] requestUrl = headMethod.getMethodAnnotation(RequestMapping.class).value();
            AclAnnotation annotation = headMethod.getMethod().getDeclaringClass().getAnnotation(AclAnnotation.class);
            AclAnnotation methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AclAnnotation.class);
            boolean hasResource = false;

            if (requestUrl != null && requestUrl.length > 0) {
                // 如果是home.do直接返回成功
                if (requestUrl[0].indexOf("logout") > -1) {
                    return true;
                }

                // get pUrl
                String pUrl = StringUtils.EMPTY;
                if (methodAnnotation != null) {
                    pUrl = methodAnnotation.pUrl();
                } else if (annotation != null) {
                    pUrl = annotation.pUrl();
                }
                if (!StringUtils.isEmpty(pUrl)) {
                    Map<String, List<MenuItem>> clickMenu = new HashMap<String, List<MenuItem>>();
                    if (user.getClickMenu() == null || user.getClickMenu().isEmpty()) {
                        clickMenu = aclService.getAllClickNotesByUser(user);
                        user.setClickMenu(clickMenu);
                    } else {
                        clickMenu = user.getClickMenu();
                    }

                    //重新设置用户点击菜单信息
                    session = request.getSession(false);
                    if (session != null)
                    {
                        session.setAttribute(KeyConstants.MEMBER, user);
                    }
                    if(user.getUser().getIsSuperAdmin()==0 &&!clickMenu.containsKey(requestUrl[0]) ){
                        hasResource = aclService.checkOperate(clickMenu.get(pUrl), requestUrl[0]);
                    }else{
                        hasResource=true;
                    }
                }
            }
            if (!hasResource) {
                logger.info("权限不足:"+request.getRequestURI());
                throw new AuthorizationException();
            }
            return true;
        } else {
            String[] logUrl = headMethod.getMethodAnnotation(RequestMapping.class).value();
            boolean isLog = false;
            boolean isError = false;
            if (logUrl != null && logUrl.length > 0) {
                isLog = logUrl[0].indexOf("log") > -1;
                isError = logUrl[0].indexOf("400") > -1 || logUrl[0].indexOf("404") > -1 || logUrl[0].indexOf("500") > -1
                        || logUrl[0].indexOf("error") > -1;
            }
            if (isError) {
                logger.info("error redict url {}",logUrl[0]);
                /*response.sendRedirect(logUrl[0]);
                return false;*/
                //直接抛出异常
                throw new Exception();
            } else {
                // logger.debug(" headMethod is login? " + isLog + "^^^^" +
                // headMethod.getMethodAnnotation(RequestMapping.class).value()[0]);
                logger.info(" redict url {}",logUrl[0]);
                if (!isLog ) {
                    response.sendRedirect("/login.do");
                    return false;
                }
            }
            return isLog;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("ControllerInterceptor.postHandle()");
        if (handler instanceof ResourceHttpRequestHandler || handler instanceof ParameterizableViewController) {
            return;
        }
        HttpSession session = request.getSession(false);
        HandlerMethod headMethod = (HandlerMethod) handler;

        if (session != null) {
            UserAndMenu user = (UserAndMenu) session.getAttribute(KeyConstants.MEMBER);
            if (user != null) {
                List<MenuItem> menusList = menuService.getMenuItemBySource(user.getUser().getResourceList(), null);
                if (menusList != null) {
                    // 设置菜单选 中
                    Set<String> urlSet = new HashSet<String>();
                    String[] currentUrl = headMethod.getMethodAnnotation(RequestMapping.class).value();
                    AclAnnotation methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AclAnnotation.class);
                    //AclAnnotation methodAnnotation = headMethod.getMethod().getAnnotation(AclAnnotation.class);
                    //String temp=headMethod.getMethodAnnotation(AclAnnotation.class).pUrl();
                    // 修改菜单选中参数来源
                    AclAnnotation annotation = headMethod.getMethod().getDeclaringClass().getAnnotation(AclAnnotation.class);
                    String selectUrl = StringUtils.EMPTY;
                    if (annotation != null && !StringUtils.isEmpty(annotation.pUrl())) {
                        // 如果该方法权限注解不为空，则选中该菜单
                        if (methodAnnotation != null && !StringUtils.isEmpty(methodAnnotation.pUrl()) && currentUrl != null) {
                            selectUrl = methodAnnotation.pUrl();
                        } else {
                            selectUrl = annotation.pUrl();
                        }
                    }
                    Integer pid=menuService.setItemSelect(menusList, selectUrl, urlSet);
                    if(pid!=null){
                        Integer topPid=menuService.setItemSelectById(menusList,pid);
                        if(topPid!=null){
                            menuService.setItemSelectById(menusList,topPid);
                        }
                    }
                    MenuItem indexItem=null;
                    if (modelAndView != null) {//把首页调到第一个
                        for(int i=0;i<menusList.size();i++){
                            if(menusList.get(i).getKey().equals("首页")){
                                indexItem=menusList.get(i);
                                menusList.remove(i);
                                break;
                            }
                        }
                        menusList.add(0,indexItem);
                        modelAndView.addObject("menusList", menusList);
                    }
                    if(selectUrl.equals("/index")&&modelAndView!=null){
                        modelAndView.addObject("isIndex","isIndex");
                    }
                }
                if(modelAndView!=null){
                    modelAndView.addObject("loginUser",user.getUser());
                }
            }

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.debug("ControllerInterceptor.afterCompletion()");
    }
}
