package com.zgljl2012.tomcat.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

public class HttpProcessor {
	
	private HttpConnector connector;
	
	private HttpRequest request;
	
	private HttpResponse response;
	
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}
	
	public void process(Socket socket) {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();
			
			// create request object and parse
			request = new HttpRequest(input);
			request.parse();
			
			// create response object
			response = new HttpResponse(output);
			response.setRequest(request);
			
			response.setHeader("server", "Tomcat-learn Servlet Container");
			
			parseRequest(input, output);
			parseHeaders(input);
			
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
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void parseRequest(SocketInputStream input, OutputStream output) throws IOException, ServletException {
		HttpRequestLine requestLine = new HttpRequestLine();
		input.readRequestLine(requestLine);
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		String uri = null;
		String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
		
		if(method.length() <= 0) {
			throw new ServletException("Missing HTTP request method");
		} else if(requestLine.uriEnd < 1) {
			throw new ServletException("Missing HTTP request URI");
		}
		
		int question = requestLine.indexOf("?");
		if(question >= 0) {
			request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question -1));
		} else {
			request.setQueryString(null);
			uri = new String(requestLine.uri, 0, requestLine.uriEnd);
		}
		
		
		
	}
	
}
