package org.xml.to.properties;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Properties2XML {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
    	props.setProperty("email.support", "sxyx2008@163.com");
    	props.setProperty("email.password", "*********");
    	props.setProperty("email.host", "smtp.163.com");
    	props.setProperty("email.port", "25");
    	
    	OutputStream os = new FileOutputStream("target/email-config.xml");
    	props.storeToXML(os, "email config","UTF-8");
    	 
    	System.out.println("Done");
	}

}
