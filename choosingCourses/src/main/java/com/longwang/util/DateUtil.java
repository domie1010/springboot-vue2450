package com.longwang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
* @ClassName: DateUtil 
* @author Mr.Long
* @date 2018年3月12日 下午9:13:58 
*
 */
public class DateUtil {

	/**
	 * 日期对象转字符串
	* @Title: formatDate 
	*  @param date  日期
	*  @param format  格式
	*  @return  参数说明 
	* @return String    返回类型 
	* @throws
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 字符串转日期对象
	* @Title: formatString 
	*  @param str  字符串
	*  @param format  格式
	*  @return
	*  @throws Exception  参数说明 
	* @return Date    返回类型 
	* @throws
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * 获取时间字符串
	* @Title: getCurrentDateStr 
	*  @return
	*  @throws Exception  参数说明 
	* @return String    返回类型 
	* @throws
	 */
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
	
}
