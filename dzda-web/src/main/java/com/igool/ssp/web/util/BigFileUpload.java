package com.igool.ssp.web.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class BigFileUpload {

	Logger logger = LoggerFactory.getLogger(com.igool.ssp.web.util.BigFileUpload.class);



	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 *
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public String send(String url, String filePath) throws IOException {
		logger.info("send -- star");
		String reult = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			//要上传的文件的路径  filePath
			//把一个普通参数和文件上传给下面这个地址    是一个servlet
			HttpPost httpPost = new HttpPost(url);

			File file = new File(filePath);
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.setCharset(Consts.UTF_8)
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart("media", new FileBody(file)).build();

			httpPost.setEntity(reqEntity);

			logger.info("发起请求的页面地址 " + httpPost.getRequestLine());
			//发起请求   并返回请求的响应
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				logger.info("----------------------------------------");
				//打印响应状态
				logger.info("statusLine:"+response.getStatusLine());
				//获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					//打印响应长度
					logger.info("Response content length: " + resEntity.getContentLength());
					//打印响应内容
					logger.info(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
				}
				//销毁
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		}finally{
			httpClient.close();
		}

		return reult;
	}

	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 *
	 * @param url
	 *            请求地址 form表单url地址
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public String upload(String url, String name, InputStream inputStream) throws IOException {
		logger.info("upload() -- star, url:"+url+", name:"+name+", inputStream :"+inputStream);
		String reult = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
			//要上传的文件的路径  filePath
			//把一个普通参数和文件上传给下面这个地址    是一个servlet
			HttpPost httpPost = new HttpPost(url);

			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.setCharset(Consts.UTF_8)
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addBinaryBody(name,inputStream, ContentType.DEFAULT_BINARY, name).build();
			//.addPart("media", new FileBody(file)).build();

			httpPost.setEntity(reqEntity);

			logger.info("发起请求的页面地址 " + httpPost.getRequestLine());
			//发起请求   并返回请求的响应
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				logger.info("----------------------------------------");
				//打印响应状态
				logger.info("statusLine:"+response.getStatusLine());
				//获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					//打印响应长度
					logger.info("Response content length: " + resEntity.getContentLength());
					//打印响应内容
					reult = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
					logger.info(reult);
				}
				//销毁
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		}finally{
			httpClient.close();
		}

		return reult;
	}

}
