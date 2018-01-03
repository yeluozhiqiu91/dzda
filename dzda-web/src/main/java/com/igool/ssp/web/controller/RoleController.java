package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.Roles;
import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.rpc.db.service.thrift.RoleResourceService;
import com.igool.rpc.db.service.thrift.RoleService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.constants.TreeView;
import com.igool.ssp.web.constants.ViewJson;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.thrift.rpc.ThriftClientProxy;
import framework.pisces.data.DataSet;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.thrift.TException;
import org.jdal.dao.Page;
import org.jdal.web.table.PaginatedListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/9/13.
 */
@Controller
@AclAnnotation(pUrl = "hbda/auth/role/jsgl")
public class RoleController {
    Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    RoleService roleService;
    @Autowired
    RoleResourceService roleResourceService;
    @RequestMapping("hbda/auth/role/jsgl")
    public ModelAndView rolePage(ModelAndView mav, HttpServletRequest request, @RequestParam(value="roleName", defaultValue="") String roleName,@ModelAttribute("paginatedList") PaginatedListAdapter paginatedList){
        //设置参数
        Roles role = new Roles();
        role.setName(roleName);
        //查询条件
        mav.addObject("roleName", roleName);
        Page<Roles> page = (Page<Roles>) paginatedList.getModel();
        List<Roles> rolesList=null;
        long rolesListCount=0L;
        try {
            rolesList=roleService.getRoleList(role,page.getStartIndex(),page.getPageSize());
            rolesListCount=roleService.getRoleListCount(role,page.getStartIndex(),page.getPageSize());
        } catch (TException e) {
            e.printStackTrace();
        }
        DataSet<Roles> datas =null;
        if(rolesList!=null&&rolesList.size()>0){
            datas=new DataSet<Roles>(rolesList,rolesListCount,rolesListCount);
        }
        if(datas!=null){
            page.setCount(datas.getTotalRecords().intValue());
            page.setData(datas.getRows());
        }
        mav.addObject("roles", rolesList);
        mav.setViewName("role/rolelist");
        return mav;
    }

    @RequestMapping(value ="hbda/auth/role/add")
    public ModelAndView addRolePage(ModelAndView mav,HttpServletRequest request){
        mav.setViewName("role/add");
        return mav;
    }

    /*
    * 异步验证角色是否存在
    * */
    @RequestMapping(value ="/ajaxVerifyRoleName",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public  String ajaxVerifyName(@RequestParam("name") String name, HttpServletResponse response)
    {
        logger.info("检查角色重复 "+name);
        Roles role = null;
        try {
            role = roleService.ajaxVerifyName(name);
        } catch (TException e) {
            e.printStackTrace();
            logger.debug("查询角色是否重复，数据库异常");
            return "数据库异常";
        }
        return role.getName()==null?null:name+"已经存在";
    }
    /*
    * 添加角色
    * */
    @RequestMapping("/addRole")
    public String addUser(@ModelAttribute Roles role, HttpServletRequest request) throws TException {
        logger.info("添加角色 "+role.getName());
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        if(userAndMenu!=null&&userAndMenu.getUser()!=null){
            role.setCreateUser(userAndMenu.getUser().getUserId());
        }
        role.setStatus(1);//默认创建就启用
        roleService.addRole(role);
        return "redirect:/hbda/auth/role/jsgl";
    }
    /*
    *  显示修改角色信息页面
    * */
    @RequestMapping("/role/edit")
    public String showUpdateUser(HttpServletRequest request,@RequestParam("rid") String rid) throws TException {
        logger.info("进入角色修改页面Id= "+rid);
        //根据uid获取用户信息
        Roles role = roleService.findRoleByRid(Integer.parseInt(rid));
        request.setAttribute("role",role);

        return "role/edit";
    }
    /*
  * 修改角色
  * */
    @RequestMapping("/updateRole")
    public String updateUser(@ModelAttribute Roles role,HttpServletRequest request) throws TException {
        logger.info("用户修改账户"+role.getName());
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        if(userAndMenu!=null&&userAndMenu.getUser()!=null){
            role.setUpdateUser(userAndMenu.getUser().getUserId());
        }
        roleService.updateRole(role);
        return "redirect:/hbda/auth/role/jsgl";
    }

    /**
     * 菜单授权
     * @param mav
     * @param request
     * @param rid
     * @return
     */
    @RequestMapping("/roleSource")
    public ModelAndView roleSource(ModelAndView mav, HttpServletRequest request, Integer rid){
        mav.addObject("rid", rid);
        mav.setViewName("role/roleSourcelist");
        return mav;
    }

    @RequestMapping("/getSourceTree")
    @ResponseBody
    public List<Resource> roleSourceTree(ModelAndView mav, HttpServletRequest request, Integer rid) throws TException {
        List<Resource> sourcelist=roleService.getRoleSourceList(rid);
        List listJson=new ArrayList();
        for(Resource s:sourcelist){
            TreeView tree=new TreeView();
            tree.setId(s.getModuleId());
            tree.setpId(s.getParentId());
            tree.setName(s.getName());
            tree.setChecked(s.getIsSelected()==0);
            listJson.add(tree);
        }
        return listJson;
    }

    /**
     * 菜单授权保存
     * @param sids
     * @param rid
     * @param request
     * @return
     */
    @RequestMapping("/saveRoleSource")
    @ResponseBody
    public ViewJson saveSource(String sids, Integer rid, HttpServletRequest request) throws TException {
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        ViewJson json=new ViewJson();
        roleResourceService.saveRoleSource(sids,rid,userAndMenu.getUser().getUserId());
        json.setSuccess(true);
        return json;
    }
}
