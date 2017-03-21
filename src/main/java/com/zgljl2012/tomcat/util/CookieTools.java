package com.zgljl2012.tomcat.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

public class CookieTools {

	public static String getCookieHeaderName(Cookie cookie) {
		if(cookie != null) {
			return cookie.getName();
		}
		return null;
	}

	public static String getCookieHeaderValue(Cookie cookie) {
		if(cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	public static Cookie[] parseCookieHeader(String value) {
		List<Cookie> cookies = new ArrayList<>();
		System.out.println("-------->>>\ncookies:\n");
		System.out.println(value);
		return cookies.toArray(new Cookie[cookies.size()]);
	}

}
