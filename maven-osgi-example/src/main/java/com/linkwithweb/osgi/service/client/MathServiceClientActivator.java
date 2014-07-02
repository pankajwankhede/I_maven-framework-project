package com.linkwithweb.osgi.service.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.linkwithweb.osgi.service.MathService;

/**
 * @author Ashwin Kumar
 * 
 */
public class MathServiceClientActivator implements BundleActivator {
	MathService service;
	private BundleContext context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) {
		this.context = context;
		// Register directly with the service
		ServiceReference reference = context
				.getServiceReference(MathService.class.getName());
		service = (MathService) context.getService(reference);
		System.out.println(service.add(1, 2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) {
		System.out.println(service.add(5, 6));
	}
}