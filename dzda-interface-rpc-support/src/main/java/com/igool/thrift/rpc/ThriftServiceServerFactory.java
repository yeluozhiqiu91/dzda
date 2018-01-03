package com.igool.thrift.rpc;

import com.facebook.swift.service.ThriftServer;
import com.igool.thrift.rpc.zookeeper.ThriftServerAddressRegister;
import com.igool.thrift.rpc.zookeeper.ThriftServerIpLocalNetworkResolve;
import com.igool.thrift.rpc.zookeeper.ThriftServerIpResolve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端注册服务工厂
 */
public class ThriftServiceServerFactory implements InitializingBean,Closeable {
	private static final Logger log = LoggerFactory.getLogger(ThriftServiceServerFactory.class);
	// 服务注册本机端口
	private Integer port = 8299;
	// 优先级
	private Integer weight = 1;// default
	// 服务实现类
	//private Object service;// serice实现类
	//多服务类支持
	private List<Object> objectList;
	//服务版本号
	private String version;

	//最大框架流(默认)
	private Integer maxFrameSize = 67108864*5;
	//数据缓冲流(默认)
	private Integer receiverBufferSize = 1024*20;
	// 解析本机IP
	private ThriftServerIpResolve thriftServerIpResolve;
	//服务注册
	private ThriftServerAddressRegister thriftServerAddressRegister;
	//服务
	ThriftServer server;

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setMaxFrameSize(Integer maxFrameSize) {
		this.maxFrameSize = maxFrameSize;
	}

	public void setReceiverBufferSize(Integer receiverBufferSize) {
		this.receiverBufferSize = receiverBufferSize;
	}

	/*public void setService(Object service) {
		this.service = service;
	}*/

	public void setVersion(String version) {
		this.version = version;
	}

	public void setThriftServerIpResolve(ThriftServerIpResolve thriftServerIpResolve) {
		this.thriftServerIpResolve = thriftServerIpResolve;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

	public void setThriftServerAddressRegister(ThriftServerAddressRegister thriftServerAddressRegister) {
		this.thriftServerAddressRegister = thriftServerAddressRegister;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (thriftServerIpResolve == null) {
			thriftServerIpResolve = new ThriftServerIpLocalNetworkResolve();
		}
		String serverIP = thriftServerIpResolve.getServerIp();
		if (StringUtils.isEmpty(serverIP)) {
			throw new ThriftException("cant find server ip...");
		}
		String hostname = serverIP + ":" + port + ":" + weight;
		List serviceList = new ArrayList();

		if ( objectList == null ) {
			throw new IllegalClassFormatException("service-class should have ");
		}
		for ( Object object :objectList){
			Class<?> serviceClass = object.getClass();
			Class<?>[] interfaces = serviceClass.getInterfaces();

			serviceList.add(object);
			if (interfaces.length == 0) {
				throw new IllegalClassFormatException("service-class should implements interface");
			}
			// reflect,load "Processor";
			String serviceName = null;
			for (Class<?> clazz : interfaces) {
				serviceName = clazz.getName();
				log.info("{} 服务已启动!",serviceClass);
				// 注册服务
				if (thriftServerAddressRegister != null) {
					thriftServerAddressRegister.register(serviceName, version, hostname);
				}
				log.info("{} 服务已注册到{}!",serviceClass,hostname);
			}
		}

		//初始化配置
		ServerConfig serverConfig = new ServerConfig();
		serverConfig.setMaxFrameSize(this.maxFrameSize);
		serverConfig.setReceiveBufferSize(this.receiverBufferSize);
		log.info("服务配置项:{}",serverConfig);
		ServerCreator serverCreator = new ServerCreator().invoke(serviceList, port, serverConfig);
		server = serverCreator.getServer();
		server.start();

	}

	/**
	 * Closes this stream and releases any system resources associated
	 * with it. If the stream is already closed then invoking this
	 * method has no effect.
	 * <p>
	 * <p> As noted in {@link AutoCloseable#close()}, cases where the
	 * close may fail require careful attention. It is strongly advised
	 * to relinquish the underlying resources and to internally
	 * <em>mark</em> the {@code Closeable} as closed, prior to throwing
	 * the {@code IOException}.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void close() throws IOException {
		server.close();
	}
}
