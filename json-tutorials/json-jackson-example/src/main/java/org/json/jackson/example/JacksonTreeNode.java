package org.json.jackson.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class JacksonTreeNode {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {

			BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/user.json"));
			JsonNode rootNode = mapper.readTree(fileReader);

			/*** read ***/
			JsonNode nameNode = rootNode.path("name");
			System.out.println(nameNode.getTextValue());

			JsonNode ageNode = rootNode.path("age");
			System.out.println(ageNode.getIntValue());

			JsonNode msgNode = rootNode.path("messages");
			Iterator<JsonNode> ite = msgNode.getElements();

			while (ite.hasNext()) {
				JsonNode temp = ite.next();
				System.out.println(temp.getTextValue());
			}

			/*** update ***/
			((ObjectNode) rootNode).put("nickname", "new nickname");
			((ObjectNode) rootNode).put("name", "updated name");
			((ObjectNode) rootNode).remove("age");

			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("target/user.json"), rootNode);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
