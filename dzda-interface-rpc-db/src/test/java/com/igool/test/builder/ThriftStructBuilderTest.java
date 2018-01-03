/**
 * 
 */
package com.igool.test.builder;

import com.igool.test.dzda.service.thrift.CacheService;
import com.igool.test.dzda.service.thrift.ImagePhotoRefService;
import com.sohu.thrift.generator.builder.ThriftFileBuilder;
import com.sohu.thrift.generator.utils.ParameterNameDiscoverer;
import org.testng.annotations.Test;

/**
 * @author hongliuliao
 *
 * createTime:2012-12-6 下午3:24:05
 */
public class ThriftStructBuilderTest {
	private static final ParameterNameDiscoverer parameterNameDiscoverer = new ParameterNameDiscoverer();
	private ThriftFileBuilder fileBuilder = new ThriftFileBuilder();
	
	@Test
	public void toOutputstream() throws Exception {

		this.fileBuilder.buildToOutputStream(CacheService.class, System.out);
//		System.out.println("#############################################");

	}

	//@Test
	public void testParam(){
		/*Method[] methods = ICommonUserService.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.print(" method "+ method.getName());
			String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
			for(String name : paramNames){
				System.out.print(" name "+name);
			}
		}*/
	}
	
}
