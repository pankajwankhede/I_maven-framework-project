package org.json.fastjson.example;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class FastjsonDecode {

	public static void main(String[] args) {
		String jsonString = "{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,\"name\":\"guest\"},{\"id\":3,\"name\":\"root\"}]}";
		Group group = JSON.parseObject(jsonString, Group.class);
		
		System.out.println(group.getId());
		System.out.println(group.getName());
		List<User> users = group.getUsers();
		for (User user : users) {
			System.out.println(user.getId()+"\t"+user.getName());
		}
		
	}

}
