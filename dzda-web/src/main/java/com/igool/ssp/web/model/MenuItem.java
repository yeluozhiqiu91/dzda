
package com.igool.ssp.web.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:菜单ITEM.
 **/

public class MenuItem implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2769563593388918737L;
    private String key;
    private String url;
    private int level;
    private int id;
    private int pid;
    private List<MenuItem> menuItems;
    private boolean selected;
    // 0 显示，1 不显示
    private int display;
    // 参数
    private String param;
    
    //style
    private String style;

    //jackson转到为PO需要
    public MenuItem(){
        
    }
    
    public MenuItem(String key, String url, int level, List<MenuItem> menuItems, boolean selected) {
        this.key = key;
        this.url = url;
        this.level = level;
        this.menuItems = menuItems;
        this.selected = selected;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItems == null) {
            menuItems = new ArrayList<MenuItem>();
        }
        menuItems.add(menuItem);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
