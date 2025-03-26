package com.longwang.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * response工具类
* @ClassName: ResponseUtil 
* @author Mr.Long
* @date 2018年3月25日 下午3:33:31 
*
 */
public class ResponseUtil {

	public static void write(Object o,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
			out = response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
	}
}
