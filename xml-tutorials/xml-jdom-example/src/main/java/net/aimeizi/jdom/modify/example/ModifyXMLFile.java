package net.aimeizi.jdom.modify.example;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ModifyXMLFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			SAXBuilder builder = new SAXBuilder();

			Document doc = (Document) builder.build(ClassLoader
					.getSystemResource("file.xml"));
			Element rootNode = doc.getRootElement();

			// update staff id attribute
			Element staff = rootNode.getChild("staff");
			staff.getAttribute("id").setValue("2");

			// add new age element
			Element age = new Element("age").setText("28");
			staff.addContent(age);

			// update salary value
			staff.getChild("salary").setText("7000");

			// remove firstname element
			staff.removeChild("firstname");

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("target/file.xml"));

			// xmlOutput.output(doc, System.out);

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
}
