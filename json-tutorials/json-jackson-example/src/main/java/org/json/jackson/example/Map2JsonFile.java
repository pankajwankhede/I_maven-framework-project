package org.json.jackson.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class Map2JsonFile {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "mkyong");
		map.put("age", 29);
	 
		List<Object> list = new ArrayList<Object>();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");
	 
		map.put("messages", list);
	 
		try {
	 
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/user.json"), map);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
