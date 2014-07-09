package net.aimeizi.dom.example;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 使用dom解析xml
 * @author welcome
 *
 */
public class DomXmlParser {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = documentBuilder.parse(ClassLoader.getSystemResourceAsStream("books.xml"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Element element = document.getDocumentElement();
		NodeList nodeList = element.getElementsByTagName("book");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element book = (Element) nodeList.item(i);
			String id = book.getAttribute("id");

			Element bookname = (Element) book.getElementsByTagName("name")
					.item(0);
			String name = bookname.getFirstChild().getNodeValue();

			Element bookauthor = (Element) book.getElementsByTagName("author")
					.item(0);
			String author = bookauthor.getFirstChild().getNodeValue();

			Element bookprice = (Element) book.getElementsByTagName("price")
					.item(0);
			String price = bookprice.getFirstChild().getNodeValue();

			Element bookpubinfo = (Element) book
					.getElementsByTagName("pubinfo").item(0);
			String pubinfo = bookpubinfo.getFirstChild().getNodeValue();

			System.out.println(id + "," + name + "," + author + "," + price
					+ "," + pubinfo);

		}

	}

}
