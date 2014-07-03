package net.aimeizi.osgi.example.simple.bundle.api.impl;

import net.aimeizi.osgi.example.simple.bundle.api.InfoBroker;

public class InfoBrokerImpl implements InfoBroker {

	public String getInformation() {
		return "Hello felix!";
	}
}
