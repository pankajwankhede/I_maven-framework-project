package com.linkwithweb.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Ashwin Kumar
 * 
 */
public class HelloActivator implements BundleActivator {
	public void start(BundleContext context) {
		System.out.println("Hello World");
	}

	public void stop(BundleContext context) {
		System.out.println("Goodbye All");
	}
}