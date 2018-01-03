package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.Department;
import com.igool.rpc.db.service.thrift.DepartmentService;
import com.igool.ssp.web.annotation.AclAnnotation;
import com.igool.ssp.web.constants.KeyConstants;
import com.igool.ssp.web.constants.TreeView;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.thrift.rpc.ThriftClientProxy;
import org.apache.thrift.TException;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/10/24.
 */
@Controller
@AclAnnotation(pUrl = "/department/manager")
public class DepartmentController {
    Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    DepartmentService departmentService;
    /**
     * 部门列表
     * @param mav
     * @return
     */
    @RequestMapping(value ="/department/manager")
    public ModelAndView userManager(ModelAndView mav){
        mav.setViewName("department/departmentList");
        return mav;
    }

    @RequestMapping("/department/getTree")
    @ResponseBody
    public List<TreeView> getTree(HttpServletRequest request) throws Exception {
        List<Department> list=departmentService.getDepartmentList();
        List listJson=new ArrayList();
        for(Department s:list){
            TreeView tree=new TreeView();
            tree.setId(s.getDepartmentId());
            tree.setpId(s.getParentId());
            tree.setName(s.getName());
            listJson.add(tree);
        }
        return  listJson;
    }

    /**
     * 新增部门页面
     * @param mav
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value ="/department/add")
    public ModelAndView addDepartmentPage(ModelAndView mav, @RequestParam(value="id")Integer id , HttpServletRequest request) throws TException {
        List<Department> list=departmentService.getDepartmentList();
        mav.addObject("departmentList",list);
        mav.setViewName("department/add");
        mav.addObject("pid",id);
        return mav;
    }

    /**
     * 新增资源保存
     * @param department
     * @param request
     * @return
     */
    @RequestMapping("/addDepartment")
    public String addDepartment(@ModelAttribute Department department, HttpServletRequest request)
    {
        logger.info("添加部门 "+department.getName());
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        try {
            department.setCreateUser(userAndMenu.getUser().getUserId());
            departmentService.addDepartment(department);
            //departmentService.addDepartment(department);
        }catch (TException e){
            logger.error("资源添加失败");
            logger.error(e.getMessage());
        }
        return "redirect:/department/manager";
    }


    /**
     * 显示部门修改页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/department/edit")
    public String showUpdateDepartment(HttpServletRequest request, @RequestParam(value="id")String id) throws TException {
        logger.info("进入部门修改页面Id= "+id);
        List<Department> list=departmentService.getDepartmentList();
        //根据id获取资源信息
        Department department=null;
        try {
            department = departmentService.findDepartmentById(Integer.parseInt(id));
            for(Department d:list){
                if(d.getDepartmentId()==department.getDepartmentId()){
                    list.remove(d);
                    break;
                }
            }
        }catch (TException e){
            logger.error("部门修改失败");
            logger.error(e.getMessage());
        }
        if(department.getParentId()!=-1){
            request.setAttribute("departmentList",list);
        }

        request.setAttribute("department",department);
        return "department/edit";
    }

    /**
     * 修改资源保存
     * @param department
     * @param request
     * @return
     */
    @RequestMapping("/updateDepartment")
    public String updateDepart(@ModelAttribute Department department,HttpServletRequest request) throws TException {
        logger.info("部门修改,"+department.getName());
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        departmentService.updateDepartment(department);
        return "redirect:/department/manager";
    }

    @RequestMapping("/deleteDepartment")
    @ResponseBody
    public Map<String,String> deleteDepart(HttpServletRequest httpServletRequest,String departmentId){
        Map<String,String> map=new HashMap<String,String>();
        int childrenCount=0;
        int personCount= 0;
        try {
            personCount = departmentService.getPersonCount(Integer.parseInt(departmentId));
            childrenCount=departmentService.getChildrenCount(Integer.parseInt(departmentId));
            if(childrenCount>0){
                map.put("result","failed");
                map.put("msg","此部门存在下级部门，不可删除");
            }else{
                if(personCount>0){
                    map.put("result","failed");
                    map.put("msg","此部门存在人员，不可删除");
                }else{
                    departmentService.deleteDepartmentById(Integer.parseInt(departmentId));
                    map.put("result","success");
                    map.put("msg","删除成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","failed");
            map.put("msg","数据库异常，删除失败");
        }
        return map;
    }
}
