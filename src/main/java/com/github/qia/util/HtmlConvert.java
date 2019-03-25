package com.github.qia.util;

public class HtmlConvert {
	 public static String Text2Html(String str) {
		    if (str == null) {
		        return "";
		    }else if (str.length() == 0) {
		        return "";
		    }
		    str = str.replaceAll("\n", "<br />");
		    str = str.replaceAll("\r", "<br />");
		    return str;
		}
	 
	   public static String HtmlToText(String str) {
	        if (str == null) {
	            return "";
	        }else if (str.length() == 0) {
	            return "";
	        }
	        str = str.replaceAll("<br />", "\n");
	        str = str.replaceAll("<br />", "\r");    
	        return str;
	    }
}
