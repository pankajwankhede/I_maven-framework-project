package org.xml.to.properties;

import java.io.InputStream;
import java.util.Properties;

/**
 * xml转properties
 * @author welcome
 *
 */
public class Xml2Properties {

	public static void main(String[] args) throws Exception {
		
		Properties props = new Properties();
		
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("email-config.xml");
		
		props.loadFromXML(inputStream);
		
		String email = props.getProperty("email.address");
		String password = props.getProperty("email.password");
		String host = props.getProperty("email.host");
		String port = props.getProperty("email.port");
		 
    	System.out.println(email);
    	System.out.println(password);
    	System.out.println(host);
    	System.out.println(port);
		
	}
	
}
