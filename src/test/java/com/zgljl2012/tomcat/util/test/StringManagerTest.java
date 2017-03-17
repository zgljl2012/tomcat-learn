package com.zgljl2012.tomcat.util.test;

import org.junit.Test;

import com.zgljl2012.tomcat.util.StringManager;

import junit.framework.Assert;

public class StringManagerTest {
	
	private String key = "TEST";
	
	@Test
	public void testNoLocal() {
		StringManager sm = StringManager.getManager("com.zgljl2012.tomcat.util.test");
		Assert.assertEquals(null, sm.getString(key));
	}
	
	@Test
	public void testHaveLocal() {
		StringManager sm = StringManager.getManager("com.zgljl2012.tomcat.util");
		Assert.assertEquals("Hello, this is a Test!", sm.getString(key));
	}
	
}
