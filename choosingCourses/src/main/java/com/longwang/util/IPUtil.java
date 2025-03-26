package com.longwang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

/**
 * IP工具类
* @ClassName: IPUtil 
* @author Mr.Long
* @date 2018年3月14日 下午7:45:51 
*
 */
public class IPUtil {

	/**
	 * 获取当前网络IP 
	* @Title: getIpAddr 
	*  @param request
	*  @return  参数说明 
	* @return String    返回类型 
	* @throws
	 */
	public static String getIpAddr(HttpServletRequest request){  
	    String ipAddress = request.getHeader("x-forwarded-for");  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getRemoteAddr();  
	            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
	                //根据网卡取本机配置的IP  
	                InetAddress inet=null;  
	                try {  
	                    inet = InetAddress.getLocalHost();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	                ipAddress= inet.getHostAddress();  
	            }  
	        }  
	        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
	            if(ipAddress.indexOf(",")>0){  
	                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
	            }  
	        }  
	        return ipAddress;   
	}
	
	/**
	 * 获取IP信息
	* @Title: getAdd 
	*  @param request
	*  @return  参数说明 
	* @return String    返回类型 
	* @throws
	 */
	public static String getAdd(HttpServletRequest request) {
        //淘宝IP地址库：http://ip.taobao.com/instructions.php
        String add = null;
        String ip = getIpAddr(request);
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            URLConnection connection = U.openConnection();  
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
            String result = "";
            String line;
            while ((line = in.readLine())!= null){  
                result += line;  
            }
            in.close(); 
            JSONObject jsonObject = JSONObject.fromObject(result);  
            Map<String, Object> map = (Map) jsonObject;
            String code = String.valueOf(map.get("code"));//0：成功，1：失败。
            if("1".equals(code)){//失败
                String data = String.valueOf(map.get("data"));//错误信息
            }else if("0".equals(code)){//成功
                Map<String, Object> data = (Map<String, Object>) map.get("data");

                String country = String.valueOf(data.get("country"));//国家
                String area = String.valueOf(data.get("area"));//
                String city = String.valueOf(data.get("city"));//省（自治区或直辖市）
                String region = String.valueOf(data.get("region"));//市（县）
                add = country+"-"+region+"-"+city;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return add;    
    }
}
