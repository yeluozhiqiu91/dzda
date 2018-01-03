package com.igool.ssp.web.service;


import com.igool.ssp.web.model.UserAndMenu;
import com.igool.ssp.web.model.UserInfo;
import com.igool.ssp.web.model.MenuItem;
import org.apache.thrift.TException;

import java.util.List;
import java.util.Map;


public interface ACLService {

	/**得到所有可点击节点及节点下面的操作项集合
	 * key:node url
	 * value:operate collection
	 *
     * @param user***/
	public Map<String,List<MenuItem>> getAllClickNotesByUser(UserAndMenu user)throws TException;

	/***
	 * 得到当前请求的页面操作
	 * ***/
	public List<MenuItem> getPageOperates(UserAndMenu user, String requestUrl)throws TException;

	/**检查该页面下是否有操作权限***/
	public boolean checkOperate(List<MenuItem> menus, String url);

}
