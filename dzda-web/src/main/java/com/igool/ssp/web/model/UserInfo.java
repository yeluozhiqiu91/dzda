package com.igool.ssp.web.model;


import com.igool.rpc.db.model.thrift.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class UserInfo  implements Serializable {

  private User user;
  private Map<String, List<MenuItem>> clickMenu;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, List<MenuItem>> getClickMenu() {
        return clickMenu;
    }

    public void setClickMenu(Map<String, List<MenuItem>> clickMenu) {
        this.clickMenu = clickMenu;
    }
}