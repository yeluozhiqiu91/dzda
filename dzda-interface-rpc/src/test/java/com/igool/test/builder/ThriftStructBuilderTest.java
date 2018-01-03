/**
 * 
 */
package com.igool.test.builder;

import com.igool.test.ICommonUserService;
import com.igool.test.file.service.thrift.FileService;
import com.igool.test.id.service.thrift.IdGenerateService;
import com.igool.test.queue.service.thrift.DistributeQueueservice;
import com.sohu.thrift.generator.builder.ThriftFileBuilder;
import com.sohu.thrift.generator.utils.ParameterNameDiscoverer;
import org.testng.annotations.Test;
//import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author alex
 *
 * createTime:2012-12-6 下午3:24:05
 */
public class ThriftStructBuilderTest {
	private static final ParameterNameDiscoverer parameterNameDiscoverer = new ParameterNameDiscoverer();
	private ThriftFileBuilder fileBuilder = new ThriftFileBuilder();
	
	@Test
	public void toOutputstream() throws Exception {
		this.fileBuilder.buildToOutputStream(ICommonUserService.class, System.out);
		System.out.println("#############################################ICommonUserService");
		this.fileBuilder.buildToOutputStream(FileService.class, System.out);
		System.out.println("#############################################FileService");
		this.fileBuilder.buildToOutputStream(DistributeQueueservice.class, System.out);
		System.out.println("#############################################");
		this.fileBuilder.buildToOutputStream(IdGenerateService.class, System.out);
		System.out.println("#############################################");

	}

	//@Test
	public void testParam(){
		Method[] methods = ICommonUserService.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.print(" method "+ method.getName());
			String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
			for(String name : paramNames){
				System.out.print(" name "+name);
			}
		}
	}
	
}
