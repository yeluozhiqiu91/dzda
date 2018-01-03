/**
 * Copyright 2016 CY Inc.
 * All rights reserved.
 * (注意：本内容仅限于车小丫公司内部传阅，禁止外泄以及用于其他的商业目的)
 * Date: 2016年4月5日
 * 文件名称: MenuService.java
 * 作者: Administrator
**/
package com.igool.ssp.web.service;



import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.Source;
import com.igool.ssp.web.model.MenuItem;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 类描述:菜单服务.
**/
public interface MenuService {

    /***
     *
     * 将列表转换成树结构
     * ****/

    public List<MenuItem> getMenuItemBySource(List<Resource> sourceList, MenuItem item);
    
    /**
     * 得到所有可点击节点及节点下面的操作项集合
     * key:node url
     * value:operate collection
     * ***/
    public void getMenuItemMap(List<MenuItem> verticalMenu, Map<String, List<MenuItem>> map);
    
    /**
     * 递归处理菜单选中
     * 添加一个参数cacheSet，用来过滤重复的URL
     * **/
    public Integer setItemSelect(List<MenuItem> verticalMenu, String url, Set<String> setUrl);


    public Integer setItemSelectById(List<MenuItem> menusList, Integer pid);
}
