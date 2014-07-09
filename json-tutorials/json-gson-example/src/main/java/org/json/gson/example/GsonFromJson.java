package org.json.gson.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFromJson {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/file.json"));
			// convert the json string back to object
			DataObject obj = gson.fromJson(br, DataObject.class);
			System.out.println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
