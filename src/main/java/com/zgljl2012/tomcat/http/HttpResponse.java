package com.zgljl2012.tomcat.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import com.zgljl2012.tomcat.Constants;
import com.zgljl2012.tomcat.Constants.StatusCode;

public class HttpResponse implements ServletResponse {
	
	private static final int BUFFER_SIZE = 1024;
	
	HttpRequest request;
	
	OutputStream output;
	
	PrintWriter writer;
	
	public HttpResponse(OutputStream output) {
		this.output = output;
	}
	
	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	
	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(Constants.WEB_ROOT, request.getUri());
			fis = new FileInputStream(file);
			int ch = fis.read(bytes, 0, BUFFER_SIZE);
			while(ch != -1) {
				output.write(bytes, 0, ch);
				ch = fis.read(bytes, 0, BUFFER_SIZE);
			}
		} catch(FileNotFoundException e) {
			output.write(StatusCode.ERROR_404.value().getBytes());
		} finally {
			if(fis != null) {
				fis.close();
			}
		}
	}
	
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		OutputStreamWriter osr = new OutputStreamWriter(this.output, getCharacterEncoding());
		writer = new PrintWriter(osr);
		return writer;
	}

	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}

	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}

	public void setContentType(String type) {
		// TODO Auto-generated method stub
		
	}

	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

}
