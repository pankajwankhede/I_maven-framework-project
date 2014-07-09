package org.json.jackson.example;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class Map2JsonString {

	public static void main(String[] args) {
		try {
			 
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
	 
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "mkyong");
			map.put("age", "29");
	 
			//convert map to JSON string
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
	 
			System.out.println(json);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	}

}
