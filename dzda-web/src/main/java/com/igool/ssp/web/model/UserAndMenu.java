package com.igool.ssp.web.model;

import com.igool.rpc.db.model.thrift.*;
import com.igool.rpc.db.model.thrift.UserInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017/9/8.
 */
public class UserAndMenu implements Serializable{
    private com.igool.rpc.db.model.thrift.UserInfo user;
    private Map<String, List<MenuItem>> clickMenu;

    public com.igool.rpc.db.model.thrift.UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Map<String, List<MenuItem>> getClickMenu() {
        return clickMenu;
    }

    public void setClickMenu(Map<String, List<MenuItem>> clickMenu) {
        this.clickMenu = clickMenu;
    }
}
