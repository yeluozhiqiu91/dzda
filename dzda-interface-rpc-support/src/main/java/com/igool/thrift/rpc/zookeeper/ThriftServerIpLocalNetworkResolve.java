package com.igool.thrift.rpc.zookeeper;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析网卡Ip
 *
 */
public class ThriftServerIpLocalNetworkResolve implements ThriftServerIpResolve {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	//缓存
	private String serverIp;
	
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Override
	public String getServerIp() {
		if (serverIp != null) {
			return serverIp;
		}
		//增加判断逻辑，如果有外网IP，先使用外网
		String netIp = StringUtils.EMPTY;
		String localIp = StringUtils.EMPTY;
		// 一个主机有多个网络接口
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = netInterfaces.nextElement();
				// 每个网络接口,都会有多个"网络地址",比如一定会有lookback地址,会有siteLocal地址等.以及IPV4或者IPV6 .
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress address = addresses.nextElement();
					if(address instanceof Inet6Address){
						continue;
					}
					if (address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
						localIp = address.getHostAddress();
						logger.info("resolve local server ip :"+ serverIp);
						continue;
					}
					if (!address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
						netIp = address.getHostAddress();
						logger.info("resolve net  server ip :"+ serverIp);
						break;
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return serverIp = netIp.length() > 0 ? netIp : localIp;
	}

	@Override
	public void reset() {
		serverIp = null;
	}
}
