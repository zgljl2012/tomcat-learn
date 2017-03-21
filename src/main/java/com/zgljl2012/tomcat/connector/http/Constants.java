package com.zgljl2012.tomcat.connector.http;

public class Constants {
	
	public static final String WEB_ROOT = "src/main/webapp";
	
	public static String PACKAGE_NAME = "com.zgljl2012.tomcat";
	
	public static final String SRC = "src/main/java";
	
	public static final String Package = "com.zgljl2012.tomcat.http";
	
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
    
    public static final int PROCESSOR_IDLE = 0;
    
    public static final int PROCESSOR_ACTIVE = 1;
	
	public interface IStatusCode {
		String value();
	}
	
	public static enum StatusCode implements IStatusCode {
		
		ERROR_404 {
			@Override
			public String value() {
				return "HTTP/1.1 404 File Not Found\r\n" + 
						"Content-Type: text/html\r\n" + 
						"Content-Length: 23\r\n" + 
						"<h1>404<br/>File Not Found</h1>";
			}
		}
		
		;

		public String value() {
			return null;
		}
		
	}
	
}
