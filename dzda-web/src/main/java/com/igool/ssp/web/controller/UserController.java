package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.Department;
import com.igool.rpc.db.model.thrift.Roles;
import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.rpc.db.service.thrift.DepartmentService;
import com.igool.rpc.db.service.thrift.RoleService;
import com.igool.rpc.db.service.thrift.UserInfoService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.constants.ViewJson;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.thrift.rpc.ThriftClientProxy;
import com.stnts.common.util.encryption.MD5;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/9/6.
 */
@Controller
@AclAnnotation(pUrl = "/user/userList")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RoleService roleService;

    @Autowired
    DepartmentService departmentService;

    @Value("#{config['default.password']}")
    String defaultPassword;

    @RequestMapping("/user/userList")
    public ModelAndView userPage(ModelAndView mav,HttpServletRequest request, @RequestParam(value="userName", defaultValue="") String userName,@ModelAttribute("paginatedList") PaginatedListAdapter paginatedList) throws TException{
        UserInfo userInfo=new UserInfo();
        userInfo.setName(userName);
        mav.addObject("userName", userName);
        Page<UserInfo> page = (Page<UserInfo>) paginatedList.getModel();
        List<UserInfo> userList=null;
        long userListCount=0L;
        userList=userInfoService.getUserInfoList(userInfo,page.getStartIndex(),page.getPageSize());
        userListCount=userInfoService.getUserInfoListCount(userInfo,page.getStartIndex(),page.getPageSize());
        DataSet<UserInfo> datas =null;
        if(userList!=null&&userList.size()>0){
            datas=new DataSet<UserInfo>(userList,userListCount,userListCount);
        }
        if(datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("userList",userList);
        mav.setViewName("user/userList");
        return mav;
    }
    /*
    * 新增用户
    * */
    @RequestMapping(value = "/user/add")
    public ModelAndView reportImport(ModelAndView mav, HttpServletRequest request) {
        List<Department> list=null;
        try {
            list=departmentService.getDepartmentList();
        } catch (TException e) {
            e.printStackTrace();
        }
        if(list!=null&&list.size()>0){
            mav.addObject("departmentList",list);
        }
        mav.setViewName("user/add");
        return mav;
    }

    /*
    * 新增用户保存
    * */
    @RequestMapping("/addUser")
    public String addUser(@ModelAttribute UserInfo user, HttpServletRequest request) throws TException{
        logger.info("添加人员 " + user.getName());
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        if(userAndMenu!=null&&userAndMenu.getUser()!=null){
            user.setCreateUser(userAndMenu.getUser().getUserId());
        }
        user.setPassword(MD5.md5crypt("123"));//默认密码
        userInfoService.addUser(user);
        return "redirect:/user/userList";
    }

    /*
    *  显示修改用户信息页面
    * */
    @RequestMapping("/user/edit")
    public String showUpdateUser(HttpServletRequest request, @RequestParam("uid") String uid) throws TException {
        logger.info("进入用户修改页面Id= " + uid);
        //User userSession = (User) request.getSession().getAttribute(KeyConstants.MEMBER);
        //根据uid获取用户信息
        UserInfo user = userInfoService.findUserById(uid);
        List<Department> list=departmentService.getDepartmentList();
        if(list!=null&&list.size()>0){
            request.setAttribute("departmentList",list);
        }
        request.setAttribute("user", user);

        return "user/edit";
    }

    /**
     * 修改用户保存
     * @param request
     * @param user
     * @return
     * @throws TException
     */
    @AclAnnotation(pUrl="/user/userList")
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request, @ModelAttribute UserInfo user) throws TException {

        userInfoService.updateUser(user);

        return "redirect:user/userList";
    }


    /**
     * 用户角色配置
     * @param mav
     * @param request
     * @param uid
     * @return
     * @throws TException
     */
    @AclAnnotation(pUrl="/user/userList")
    @RequestMapping("/userRole")
    public ModelAndView  roleList(ModelAndView mav, HttpServletRequest request, Integer uid) throws TException {
        List<Roles> rolelist=roleService.getUserRoleList(uid);
        mav.addObject("roleList",rolelist);
        mav.addObject("uid", uid);
        mav.setViewName("user/userRolelist");
        return mav;
    }

    @AclAnnotation(pUrl="/user/userList")
    @RequestMapping("/saveUserRole")
    @ResponseBody
    public ViewJson saveRoles(String rids, Integer uid, HttpServletRequest request) throws TException {
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        ViewJson json=new ViewJson();
        roleService.saveUserRole(rids, uid,userAndMenu.getUser().getUserId());
        json.setSuccess(true);
        return json;
    }

    /**
     * 修改自己资料
     * @param request
     * @param uid
     * @return
     * @throws TException
     */
    @AclAnnotation(pUrl = "/user/userList")
    @RequestMapping("/user/editSelf")
    public String showEditSelf(HttpServletRequest request, @RequestParam("uid") String uid) throws TException {
        logger.info("进入资料修改页面Id= " + uid);
        //User userSession = (User) request.getSession().getAttribute(KeyConstants.MEMBER);
        //根据uid获取用户信息
        UserInfo user = userInfoService.findUserById(uid);
        request.setAttribute("user", user);

        return "user/editSelf";
    }

    @AclAnnotation(pUrl = "/user/userList")
    @RequestMapping("/user/editPassword")
    public String editPassword(HttpServletRequest request, @RequestParam("uid") String uid) throws TException {
        logger.info("进入密码修改页面Id= " + uid);
        UserInfo user = userInfoService.findUserById(uid);
        request.setAttribute("user", user);
        return "user/editPassword";
    }

    @RequestMapping(value ="/savePassword")
    @ResponseBody
    public Map<String,String> savePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletResponse response,HttpServletRequest request) throws TException {
        Map<String,String> map = new HashMap<String,String>();
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);

        UserInfo user = userAndMenu.getUser();

        if( !user.getPassword().equals(MD5.md5crypt(oldPassword))){
            map.put("code","1");
            map.put("message","原密码输入错误!");
        }else{
            user.setPassword(MD5.md5crypt(newPassword));
            userInfoService.updatePassword(user);
            map.put("code","0");
            map.put("message","修改成功");
        }
        return map;
    }

    @RequestMapping("/resetPassword")
    public String updateUser(HttpServletRequest request, @RequestParam("uid") String uid) throws TException {

        logger.info("密码重置Id= " + uid);
        userInfoService.resetPassword(uid,MD5.md5crypt(defaultPassword));
        return "redirect:user/userList";
    }
}
