package com.javacodegeeks.enterprise.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.javacodegeeks.enterprise.ws.WebServiceInterface")
public class WebServiceImpl implements WebServiceInterface{

	public String printMessage() {
		return "Hello from Java Code Geeks Server";
	}

}