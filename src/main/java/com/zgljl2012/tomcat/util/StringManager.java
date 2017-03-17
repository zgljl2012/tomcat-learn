package com.zgljl2012.tomcat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.zgljl2012.tomcat.Constants;

public class StringManager {
	
	private String packageName;
	
	private Properties prop = new Properties();
	
	public static LocalStrings localStrings = LocalStrings.DEFAULT;
	
	public static enum LocalStrings {
		
		DEFAULT("LocalStrings"),
		
		;
		
		String name;
		
		private LocalStrings(String name) {
			this.name = name;
		}
		
		public String getPath() {
			return name+".properties";
		}
	}
	
	private static Hashtable<String, StringManager> managers = new Hashtable<>();
	
	public StringManager(String packageName) {
		this.packageName = packageName;
		String path = this.packageName.replace('.', '/') + "/" 
				+ localStrings.getPath();
		File file = new File(Constants.SRC, path);
		try {
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			prop = new Properties();
		}
		
	}

	public synchronized static StringManager getManager(String packageName) {
		StringManager mgr = managers.get(packageName);
		if(mgr == null) {
			mgr = new StringManager(packageName);
			managers.put(packageName, mgr);
		}
		return mgr;
	}
	
	public String getString(String key) {
		return prop.getProperty(key);
	}
	
	public String getPackageName() {
		return this.packageName;
	}
}
