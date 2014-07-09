package org.json.gson.example;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Gson2Json {

	public static void main(String[] args) {
		
		DataObject obj = new DataObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	 
		// convert java object to JSON format,
		// and returned as JSON formatted string
		String json = gson.toJson(obj);
	 
		try {
			//write converted json data to a file named "file.json"
			FileWriter writer = new FileWriter("src/main/resources/file.json");
			writer.write(json);
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		System.out.println(json);
		
	}

}
