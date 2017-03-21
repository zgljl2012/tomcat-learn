package com.zgljl2012.tomcat.startup;

import com.zgljl2012.tomcat.connector.http.HttpConnector;

public final class Bootstrap {
	
	public static void main(String[] args) {
		HttpConnector connector = new HttpConnector();
		connector.start();
	}

}
