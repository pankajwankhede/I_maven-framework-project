package com.wss.lsl.spring.demo.action;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -3886423907181027182L;
	
	private String j_username;
	private String j_password;
	private String activity_id;
	private String activity_name;
	private String startDate;
	private String endDate;
	
	public String getJ_username() {
		return j_username;
	}
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	public String getJ_password() {
		return j_password;
	}
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
	public String getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String login() throws Exception {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		j_username = userDetails.getUsername();
		j_password = userDetails.getPassword();
		System.out.println("username: "+j_username);
		System.out.println("password: "+j_password);
		activity_id = "01";
		activity_name="海岸城七月活动";
		startDate="2012/07/26";
		endDate="2012/10/25";
		return SUCCESS;
	}
	
	public void validateLogin() {
	}

}
