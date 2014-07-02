package com.linkwithweb.osgi.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Ashwin Kumar
 * 
 */
public class MathServiceActivator implements BundleActivator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) {
		MathService service = new MathServiceImpl();
		// Third parameter is a hashmap which allows to configure the service
		// Not required in this example
		context.registerService(MathService.class.getName(), service, null);
		System.out.println("Math Service Registered");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) {
		System.out.println("Goodbye From math service");
	}
}