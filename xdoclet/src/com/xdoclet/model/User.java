package com.xdoclet.model;
/**
 * @hibernate.class 
 * 		table="t_user"
 * @author welcome
 *
 */
public class User {
	private String userId;
	private String userName;
	private Group group;
	/**
	 * @hibernate.id column="userId"
	 * generator-class="assigned"
	 */
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @hibernate.property
	 */
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @hibernate.many-to-one
	 * 		column="groupId"
	 * 		cascade="all"
	 * 		class="com.xdoclet.model.Group"
	 * @param group
	 */
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
