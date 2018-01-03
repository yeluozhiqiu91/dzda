package com.igool.main;

import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//服务端启动
public class Server {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	public static void main(String[] args) {
		try {
			//ApplicationContext cxa = new GenericXmlApplicationContext("classpath:spring-context-thrift-server.xml");
			new GenericXmlApplicationContext("classpath:spring-context-motan-server.xml");
			MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
