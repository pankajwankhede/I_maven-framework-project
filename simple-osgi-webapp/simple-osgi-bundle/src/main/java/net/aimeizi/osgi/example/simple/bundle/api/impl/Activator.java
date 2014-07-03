package net.aimeizi.osgi.example.simple.bundle.api.impl;

import java.util.Properties;

import net.aimeizi.osgi.example.simple.bundle.api.InfoBroker;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	public void start(BundleContext ctx) throws Exception {
		ctx.registerService(InfoBroker.class.getName(),
				new InfoBrokerImpl(), new Properties());
	}

	public void stop(BundleContext ctx) throws Exception {
	}
}
