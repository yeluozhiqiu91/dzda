package com.igool.ssp.web.service.impl;






import com.igool.rpc.db.model.thrift.Resource;
import com.igool.ssp.web.model.MenuItem;
import com.igool.ssp.web.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
	/***
	 * 
	 * 等到所有菜单
	 ****/

	public List<MenuItem> getMenuItemBySource(List<Resource> sourceList,
											  MenuItem item) {
		List<MenuItem> verticalMenu = new ArrayList<MenuItem>();
		if (sourceList != null) {
			for (Resource source : sourceList) {
				MenuItem inmenuItem = null;
				// 表示第一层，第二层
				if ((source.getPath() ==null||"".equals(source.getPath())||"/index".equals(source.getPath())) && null == item&&(source.getParentId()==-1)) {
					MenuItem menuItem = new MenuItem(source.getName(),
							StringUtils.isEmpty(source.getPath()) ? null
									: source.getPath(), 0, null, false);
					menuItem.setId(source.getModuleId());
					menuItem.setPid(source.getParentId());
					/*menuItem.setParam(source.getParam());
					menuItem.setSelected(source.getIsSelect()==0);
					menuItem.setDisplay(source.getDisplay());
					menuItem.setStyle(source.getIconSkin());
					if (StringUtils.isNotBlank(source.getIconSkin())) {
						menuItem.setStyle(source.getIconSkin());
					}*/
					verticalMenu.add(menuItem);
					// System.out.println(" menuItem () " + menuItem.getId());
					// 根结点的下级
					for (Resource insource : sourceList) {
						if (insource.getParentId() == source.getModuleId()) {
							inmenuItem = new MenuItem(insource.getName(),
									insource.getPath(), menuItem.getLevel() + 1,
									null, false);
							inmenuItem.setId(insource.getModuleId());
							inmenuItem.setPid(menuItem.getId());
							/*inmenuItem.setParam(insource.getParam());
							inmenuItem.setStyle(insource.getIconSkin());
							inmenuItem.setSelected(insource.getIsSelect()==0);
							inmenuItem.setDisplay(insource.getDisplay());*/
							// inmenuItem.setDisplay(StringUtils.isEmpty(insource.getsUrl()
							// || true? 0 : 1);
							menuItem.addMenuItem(inmenuItem);
							// System.out.println(" menuItem () ----" +
							// inmenuItem.getId());
							getMenuItemBySource(sourceList, inmenuItem);
						}
					} // end for
				} // end if
				else {

					if (null != item) {
						if (item.getId() == source.getParentId()) {
							// System.out.println(" menuItem()------- " +
							// source.getSourceId());
							inmenuItem = new MenuItem(source.getName(),
									source.getPath(), item.getLevel() + 1, null,
									false);
							inmenuItem.setId(source.getModuleId());
							inmenuItem.setPid(item.getId());
                            /*inmenuItem.setSelected(source.getIsSelect()==0);
							inmenuItem.setParam(source.getParam());
							inmenuItem.setDisplay(source.getDisplay());
							inmenuItem.setStyle(source.getIconSkin());*/
							// inmenuItem.setDisplay(StringUtils.isEmpty(source.getsUrl())
							// || source.getShow() == 0? 0 : 1);
							item.addMenuItem(inmenuItem);
							getMenuItemBySource(sourceList, inmenuItem);
						}
					}

				}

			}
		}
		return verticalMenu;
	}


	/**
	 * 得到所有可点击节点及节点下面的操作项集合 key:node url value:operate collection
	 ***/
	public void getMenuItemMap(List<MenuItem> verticalMenu,
							   Map<String, List<MenuItem>> map) {
		for (MenuItem menuItem : verticalMenu) {
			if (null != menuItem.getMenuItems()
					&& !menuItem.getMenuItems().isEmpty()) {
				if (!StringUtils.isEmpty(menuItem.getUrl()) ) {
					map.put(menuItem.getUrl(), menuItem.getMenuItems());
				}
				getMenuItemMap(menuItem.getMenuItems(), map);
			}else{
				if (!StringUtils.isEmpty(menuItem.getUrl())) {
					map.put(menuItem.getUrl(), menuItem.getMenuItems());
				}
			}
		}
	}

	/**
	 * 递归处理菜单选中 添加一个参数cacheSet，用来过滤重复的URL
	 **/

	public Integer setItemSelect(List<MenuItem> verticalMenu, String url,
                              Set<String> setUrl) {
		if (null == url) {
			return null;
		}
		Integer pid=null;
		/*for (MenuItem menuItem : verticalMenu) {
			if (null != menuItem.getUrl() && menuItem.getUrl().startsWith(url)) {
				menuItem.setSelected(!setUrl.contains(url));
				setUrl.add(url);
			} else {
				menuItem.setSelected(false);
			}
			if (menuItem.getMenuItems() != null)
				setItemSelect(menuItem.getMenuItems(), url, setUrl);
			// }
		}*/
		for(int i=0;i<verticalMenu.size();i++){
			MenuItem m=verticalMenu.get(i);
			if(m.getUrl()!=null&&m.getUrl().equals(url)){
				m.setSelected(true);
				pid=m.getPid();
				break;
			}else if(m.getMenuItems()!=null){
				pid=setItemSelect(m.getMenuItems(),url,setUrl);
				if(pid!=null){
					return pid;
				}else{
					continue;
				}
			}
		}
		/*for(MenuItem menuItem:verticalMenu){
			if(menuItem.getUrl()!=null&&menuItem.getUrl().equals(url)){
				menuItem.setSelected(true);
				pid=menuItem.getPid();
				return pid;
			}else if(menuItem.getMenuItems()!=null){
				pid=setItemSelect(menuItem.getMenuItems(),url,setUrl);
				return pid;
			}
		}*/
		return pid;
	}

	/*@Override
	public Source getSourceById(Integer mid) {
		return menusMapper.findMenusByUid(mid);
	}*/

	public Integer setItemSelectById(List<MenuItem> verticalMenu,Integer pid){
		Integer topPid=null;
		for(int i=0;i<verticalMenu.size();i++){
			MenuItem m=verticalMenu.get(i);
			if(m.getId()==pid){
				m.setSelected(true);
				topPid=m.getPid();
				break;
			}else if(m.getMenuItems()!=null){
				topPid=setItemSelectById(m.getMenuItems(),pid);
				if(topPid!=null){
					return topPid;
				}else{
					continue;
				}
			}
		}
		/*for(MenuItem menuItem:verticalMenu){
			if(menuItem.getId()==pid){
				menuItem.setSelected(true);
				topPid=menuItem.getPid();
				return topPid;
			}else if(menuItem.getMenuItems()!=null){
				return setItemSelectById(menuItem.getMenuItems(),pid);
			}
		}*/
		return topPid;
	}
}
