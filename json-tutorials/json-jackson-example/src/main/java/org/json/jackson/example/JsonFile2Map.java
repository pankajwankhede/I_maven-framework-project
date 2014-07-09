package org.json.jackson.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonFile2Map {

	public static void main(String[] args) {

		try {

			ObjectMapper mapper = new ObjectMapper();

			// read JSON from a file
			Map<String, Object> map = mapper.readValue(
					new File("src/main/resources/user.json"),
					new TypeReference<Map<String, Object>>() {
					});

			System.out.println(map.get("name"));
			System.out.println(map.get("age"));

			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("messages");

			for (String msg : list) {
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
