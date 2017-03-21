package com.zgljl2012.tomcat.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.zgljl2012.tomcat.util.StringUtils;

/**
 * 连接器
 * @author zgljl2012
 */
public class HttpConnector implements Runnable {

	boolean stopped = false;
	
	// 监听的端口号
	int port = 8080;
	
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			// 监听一个Socket
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// 死循环获取socket
		while(!stopped) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch(Exception e) {
				continue;
			}
			HttpProcessor processor = new HttpProcessor(this);
			processor.process(socket);
		}
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
		StringUtils.println("系统启动，正在监听端口" + port);
	}
}
