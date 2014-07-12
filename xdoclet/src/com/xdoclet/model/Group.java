package com.xdoclet.model;
import java.util.Set;
/**
 * @hibernate.class
 * 		table="t_group"
 * @author welcome
 *
 */
public class Group {
	private String groupId;
	private String groupName;
	private Set userSets;
	/**
	 * @hibernate.id
	 * 		column="groupId"
	 * 		generator-class="assigned"
	 * @return
	 */
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @hibernate.property
	 * 		column="groupName"
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @hibernate.set  inverse="true"
	 * @hibernate.collection-key column="groupId"
	 * @hibernate.collection-one-to-many 
	 * 		class="com.xdoclet.model.User"
	 * @return
	 */
	public Set getUserSets() {
		return userSets;
	}
	public void setUserSets(Set userSets) {
		this.userSets = userSets;
	}
}
