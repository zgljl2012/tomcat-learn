package com.zgljl2012.tomcat.http;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

import com.zgljl2012.tomcat.Constants;

public class ServletProcessor {
	
	public void process(HttpRequest request, HttpResponse response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf('/')+1);
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		Class<?> myClass = null;
		try {
			myClass = loader.loadClass(Constants.PACKAGE_NAME+"."+servletName);
		} catch(ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			HttpRequestFacade requestFacade = new HttpRequestFacade(request);
			HttpResponseFacade responseFacade = new HttpResponseFacade(response);
			servlet.service(requestFacade, responseFacade);
		} catch(Exception e) {
			System.out.println(e.toString());
		} catch(Throwable e) {
			System.out.println(e.toString());
		}
	}
	
}
