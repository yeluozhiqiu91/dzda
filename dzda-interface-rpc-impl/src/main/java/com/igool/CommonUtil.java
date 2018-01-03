package com.igool;

import org.apache.commons.lang.StringUtils;

import java.io.File;

public class CommonUtil {

	
	/**
     * @param filePath string 文件路径
	 * @return string fileName
	 * **/
	public static String getFileName(String filePath){
		if (StringUtils.isNotEmpty(filePath)){
			int posPath = filePath.lastIndexOf(File.separator);
			int posSeprate = filePath.lastIndexOf("/");
			if ( posPath > -1 ){
				return filePath.substring(posPath+1);
			}
			
			if ( posSeprate > -1 ){
				return filePath.substring(posSeprate+1);
			}
		}
		return filePath;
	}
}