package com.zgljl2012.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	/**
	 * WEB_ROOT是静态资源存储位置
	 */
	
	/**
	 * Shutdown command
	 */
	private static final String SHUTDOWN_COMMAND = "/shutdown";
	
	// the shutdown command received
	private boolean shutdown = false;
	
	/**
	 * 入口函数
	 * @param args
	 */
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
			
	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		// 创建服务器
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		while(!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				// create request object and parse
				Request request = new Request(input);
				request.parse();
				
				// create response object
				Response response = new Response(output);
				response.setRequest(request);
				
				// 检查url是访问servlet还是静态资源
				String uri = request.getUri();
				if(uri.startsWith("/servlet")) {
					ServletProcessor processor = new ServletProcessor();
					processor.process(request, response);
				} else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				// close the socket
				socket.close();
				// 检查url是否为关闭命令
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
}
