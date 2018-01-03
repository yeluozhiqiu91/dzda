package com.igool.main;

import com.igool.file.service.thrift.FileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//服务端启动
public class Server {

	public static void main(String[] args) {
		try {
			ApplicationContext app = new GenericXmlApplicationContext("classpath:spring-context-thrift-server.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
