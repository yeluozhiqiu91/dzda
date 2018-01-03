package com.igool.ssp.web.service.impl;



import com.igool.rpc.db.dao.thrift.SourceRolesDao;
import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.Source;
import com.igool.rpc.db.service.thrift.RoleResourceService;
import com.igool.rpc.db.service.thrift.UserInfoService;
import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.model.UserInfo;
import com.igool.ssp.web.model.MenuItem;
import com.igool.ssp.web.service.ACLService;
import com.igool.ssp.web.service.MenuService;
import com.igool.thrift.rpc.ThriftClientProxy;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ACLServiceImpl implements ACLService {

    private static final Logger logger = LoggerFactory.getLogger(ACLServiceImpl.class);

    @Autowired
    private MenuService menuService;
    @Autowired
    RoleResourceService roleResourceService;

    @Override
    public Map<String, List<MenuItem>> getAllClickNotesByUser(UserAndMenu user) throws TException{
        Map<String, List<MenuItem>> map = new HashMap<String, List<MenuItem>>();
        MenuItem item = null;
        if (null == user.getUser().getResourceList())
        {
            List<Resource> menusList =roleResourceService.getAdminPermissionList(user.getUser());
            user.getUser().setResourceList(menusList);
        }

        menuService.getMenuItemMap(menuService.getMenuItemBySource(user.getUser().getResourceList(), item), map);
        return map;
    }

    @Override
    public List<MenuItem> getPageOperates(UserAndMenu user, String requestUrl)throws TException {
        // TODO Auto-generated method stub
        return getAllClickNotesByUser(user).get(requestUrl);
    }

    @Override
    public boolean checkOperate(List<MenuItem> menus, String url) {
        if (menus != null && !menus.isEmpty()) {
            for (MenuItem item : menus) {
                if (isCurrentOperate(item.getUrl(), url))
                    return true;
            }
        }
        return false;
    }

    private boolean isCurrentOperate(String urlReg, String url) {
        logger.debug("urlReq {}, url {}",urlReg, url);
        if (!StringUtils.isEmpty(urlReg) && !StringUtils.isEmpty(url)) {
            return urlReg.indexOf(url) > -1;
          /*  Pattern mobNO = Pattern.compile(urlReg);
            Matcher matcher = mobNO.matcher(url);
            return matcher.find();*/
        }
        return false;
    }
}
