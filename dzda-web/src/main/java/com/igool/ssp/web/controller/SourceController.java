package com.igool.ssp.web.controller;

import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.service.thrift.RoleResourceService;
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
import java.util.List;

@Controller
@AclAnnotation(pUrl="/source/manager")
public class SourceController {
    Logger logger = LoggerFactory.getLogger(SourceController.class);
    @Autowired
    RoleResourceService roleResourceService;

    /**
     * 资源列表
     * @param mav
     * @return
     */
    @RequestMapping(value ="/source/manager")
    public ModelAndView userManager(ModelAndView mav){
        mav.setViewName("source/sourceList");
        return mav;
    }

    @RequestMapping("/getTree")
    @ResponseBody
    public List<TreeView> getTree(HttpServletRequest request) throws Exception {
        List<Resource> list=roleResourceService.getResourceList();
        List listJson=new ArrayList();
        for(Resource s:list){
            TreeView tree=new TreeView();
            tree.setId(s.getModuleId());
            tree.setpId(s.getParentId());
            tree.setName(s.getName());
            listJson.add(tree);
        }
        return  listJson;
    }

    /**
     * 新增资源页面
     * @param mav
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value ="/source/add")
    public ModelAndView addResourcePage(ModelAndView mav, @RequestParam(value="id")Integer id , HttpServletRequest request){
        mav.setViewName("source/add");
        mav.addObject("pid",id);
        return mav;
    }

    /**
     * 新增资源保存
     * @param source
     * @param request
     * @return
     */
    @RequestMapping("/addSource")
    public String addSource(@ModelAttribute Resource source, HttpServletRequest request)
    {
        logger.info("添加资源 "+source.getName());
        try {
            roleResourceService.addResource(source);
        }catch (TException e){
            logger.error("资源添加失败");
            logger.error(e.getMessage());
        }
        return "redirect:/source/manager";
    }

    /**
     * 显示资源修改页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/source/edit")
    public String showUpdateSource(HttpServletRequest request, @RequestParam(value="id")String id)
    {
        logger.info("进入资源修改页面Id= "+id);
        //根据id获取资源信息
        Resource source=null;
        try {
            source = roleResourceService.findResourceById(Integer.parseInt(id));
        }catch (TException e){
            logger.error("资源修改失败");
            logger.error(e.getMessage());
        }
        request.setAttribute("source",source);
        return "source/edit";
    }

    /**
     * 修改资源保存
     * @param source
     * @param request
     * @return
     */
    @RequestMapping("/updateSource")
    public String updateSource(@ModelAttribute Resource source,HttpServletRequest request) throws TException {
        logger.info("资源修改"+source.getName());
        UserAndMenu userAndMenu= (UserAndMenu) request.getSession().getAttribute(KeyConstants.MEMBER);
        if(userAndMenu!=null&&userAndMenu.getUser()!=null){
            source.setUpdateUser(userAndMenu.getUser().getUserId());
        }
        roleResourceService.updateResource(source);
        return "redirect:/source/manager";
    }
}
