package com.zgljl2012.tomcat.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {

	boolean stopped = false;
	
	private String scheme = "http";
	
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		while(!stopped) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch(Exception e) {
				continue;
			}
			HttpProcessor processor = new HttpProcessor(socket);
			processor.process(socket);
		}
		
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	

}
