package com.igool.ssp.web.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
	 * <br>
	 * StringUtils.isNotEmptyByTrim("") = false <br>
	 * StringUtils.isNotEmptyByTrim("  ") = false <br>
	 * StringUtils.isNotEmptyByTrim(null) = false <br>
	 * StringUtils.isNotEmptyByTrim("123") = true <br>
	 * StringUtils.isNotEmptyByTrim(" 123 ") = true
	 */
/**
	 * <br>
	 * StringUtils.isNotEmptyByTrim("") = true <br>
	 * StringUtils.isNotEmptyByTrim("  ") = true <br>
	 * StringUtils.isNotEmptyByTrim(null) = true <br>
	 * StringUtils.isNotEmptyByTrim("123") = false <br>
	 * StringUtils.isNotEmptyByTrim(" 123 ") = false
	 */
/**
	 * <br>
	 * StringUtils.toStringByTrim("") = "" <br>
	 * StringUtils.toStringByTrim("  ") = "" <br>
	 * StringUtils.toStringByTrim(null) = null <br>
	 * StringUtils.toStringByTrim("123") = "123" <br>
	 * StringUtils.toStringByTrim(" 123 ") = "123"
	 */
/**
	 * <br>
	 * StringUtils.toStringByTrim("") = "" <br>
	 * StringUtils.toStringByTrim("  ") = "  " <br>
	 * StringUtils.toStringByTrim(null) = "" <br>
	 * StringUtils.toStringByTrim("123") = "123" <br>
	 * StringUtils.toStringByTrim(" 123 ") = "123"
	 */


/**
	 * <br>
	 * StringUtils.toInteger("") = null <br>
	 * StringUtils.toInteger("  ") = null <br>
	 * StringUtils.toInteger(null) = null <br>
	 * StringUtils.toInteger("123") = 123 <br>
	 * StringUtils.toInteger(" 123 ") = 123 <br>
	 * StringUtils.toInteger("ABC") throws NumberFormatException
	 */
public class StringUtils {

	public static enum UserPurType{
		超级管理员("0"),
		普通管理员("1");
		
		private String type;

		private UserPurType(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
	}
	
	public static String endLengthString(String text, int len) {
		if(text == null || text.length() <= len)
			return text;
		
		if(len <= 0)
			return "";
		
		return text.substring(text.length() - len, text.length());
	}
	
	/**
	 * <br>
	 * StringUtils.isNotEmptyByTrim("") = false <br>
	 * StringUtils.isNotEmptyByTrim("  ") = false <br>
	 * StringUtils.isNotEmptyByTrim(null) = false <br>
	 * StringUtils.isNotEmptyByTrim("123") = true <br>
	 * StringUtils.isNotEmptyByTrim(" 123 ") = true
	 */
	public static boolean isNotEmptyByTrim(String text) {
		if (text == null)
			return false;

		if (text.trim().isEmpty())
			return false;

		return true;
	}

	/**
	 * <br>
	 * StringUtils.isNotEmptyByTrim("") = true <br>
	 * StringUtils.isNotEmptyByTrim("  ") = true <br>
	 * StringUtils.isNotEmptyByTrim(null) = true <br>
	 * StringUtils.isNotEmptyByTrim("123") = false <br>
	 * StringUtils.isNotEmptyByTrim(" 123 ") = false
	 */
	public static boolean isEmptyByTrim(String text) {
		if (text == null)
			return true;

		if (text.trim().isEmpty())
			return true;
		if(text.trim().equals("null")){
			return true;
		}

		if(text.trim().equals("undefined")){
			return true;
		}

		return false;
	}

	/**
	 * <br>
	 * StringUtils.toStringByTrim("") = "" <br>
	 * StringUtils.toStringByTrim("  ") = "" <br>
	 * StringUtils.toStringByTrim(null) = null <br>
	 * StringUtils.toStringByTrim("123") = "123" <br>
	 * StringUtils.toStringByTrim(" 123 ") = "123"
	 */
	public static String toStringByTrim(String text) {
		if (text == null)
			return null;

		return text.trim();
	}

	/**
	 * <br>
	 * StringUtils.toStringByTrim("") = "" <br>
	 * StringUtils.toStringByTrim("  ") = "  " <br>
	 * StringUtils.toStringByTrim(null) = "" <br>
	 * StringUtils.toStringByTrim("123") = "123" <br>
	 * StringUtils.toStringByTrim(" 123 ") = "123"
	 */
	public static String toStringByNotNull(String text) {
		if (text == null)
			return "";

		return text;
	}
	
	/**
	 * <br>
	 * StringUtils.toInteger("") = null <br>
	 * StringUtils.toInteger("  ") = null <br>
	 * StringUtils.toInteger(null) = null <br>
	 * StringUtils.toInteger("123") = 123 <br>
	 * StringUtils.toInteger(" 123 ") = 123 <br>
	 * StringUtils.toInteger("ABC") throws NumberFormatException
	 */
	public static Integer toInteger(String text) throws NumberFormatException {
		if (isNotEmptyByTrim(text))
			return Integer.valueOf(text.trim());
		else
			return null;
	}

	public static boolean equals(String text1, String text2) {
		if(text1 == text2)
			return true;
		
		if(text1 == null || text2 == null)
			return false;
		
		return text1.equals(text2);
	}
	
	public static String[] splitPreserveAllTokens(String text, String str) {
		List<String> arrays = new ArrayList<String>();

		if(!text.endsWith(str)) {
			text = text + str;
		}
		
		int k = -1;
		while ((k = text.indexOf(str)) != -1) {
			arrays.add(text.substring(0, k));
			
			text = text.substring(k + str.length());
		}

		return arrays.toArray(new String[arrays.size()]);
	}
	
	public static String parseDouble(double d , String format){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(d);
	}
	
	public static String parseDouble(double d){
		String format = "#.##";
		return parseDouble(d, format);
	}

	public static boolean isEmptyOrNull(Object obj){
		return null == obj || "".equals(obj.toString().trim()) || "null".equals(obj.toString().trim());
	}

	public static String formatData(String data, int front, int back){

		if(data == null)
			return "";

		StringBuffer sb = new StringBuffer();
		if(data != null && data.length() > front + back){
			sb.append(data.substring(0,front));
			for (int i = front; i < data.length() - back; i++) {
				sb.append("*");
			}
			sb.append(data.substring(data.length()-back));
			return sb.toString();
		} else if(data.length() > back){
			for (int i = 0; i < data.length() - back; i++) {
				sb.append("*");
			}
			sb.append(data.substring(data.length()-back));
			return sb.toString();
		}else{
			return data;
		}
	}

	public static String changeString( String str, String str2, int start, int end ){
		if(str==null){
			return "";
		}

		String x = "";

		for( int i=0; i<end-start; i++ ){
			x += str2;
		}

		String replaceAll = str.replaceAll("(.{"+(start)+"})(.{"+(end-start)+"})(.*)", "$1"+x+"$3");
		return replaceAll;
	}
	public static boolean isEmpty(String text) {
		return (text == null || text.trim().isEmpty()) ? true : false;
	}
}
