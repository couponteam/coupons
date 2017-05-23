package com.masou.coupon.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Order(1)
public class ChasetUtil implements HandlerInterceptor {

	public static String getUtf8String(String str){
		String rtvString="";
		try {
			if(getEncoding(str).equals("ISO-8859-1"))
			{
				rtvString = new String(str.getBytes("iso8859-1"), "utf-8");
			}
//			else if(getEncoding(str).equals("UTF-8"))
//			{
//				rtvString = new String(str.getBytes("iso8859-1"), "utf-8");
//			}
			else{
				rtvString= str;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println("search : - > " + getEncoding(keywordstr));
		return rtvString;
	}
	
	public static String getEncoding(String str)
	  {
	    String encode = "GB2312";
	    try
	    {
  	  if (str.equals(new String(str.getBytes(encode), encode))) {
	        return encode;
	      }
  	  encode = "ISO-8859-1";
  	  if (str.equals(new String(str.getBytes(encode), encode))) {
	        return encode;
	  	  }
  	  encode = "UTF-8";
  	  if (str.equals(new String(str.getBytes(encode), encode))) {
	        return encode;
	      }
  	  encode = "GBK";
  	  if (str.equals(new String(str.getBytes(encode), encode))) {
	        return encode;
	      }
	    }
	    catch (Exception localException)
	    { 
	      encode = "UTF-8";
	      try
	      {
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	          return encode;
	        }
	      }
	      catch (Exception localException1)
	      {
	    	encode = "ISO-8859-1";
	        try
	        {
	          if (str.equals(new String(str.getBytes(encode), encode))) {
	            return encode;
	          }
	        }
	        catch (Exception localException2)
	        {
	          encode = "GBK";
	          try
	          {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	              return encode;
	            }
	          }
	          catch (Exception localException3) {}
	        }
	      }
	    }
	    return "";
	  }

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

		httpServletRequest.setCharacterEncoding("utf-8");
		httpServletResponse.setCharacterEncoding("utf-8");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	}
}
