package net.aimeizi.dom.example;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomCreateXML {
	
	public static void main(String[] args) throws Exception{
        DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootElement=document.createElement("root");
        
        for (int i = 0; i < 100; i++) {
            Element bookElement=document.createElement("book");
            bookElement.setAttribute("id", (i+1)+"");
            
            Element nameElement=document.createElement("name");
            nameElement.setTextContent("Spring");
            
            Element priceElement=document.createElement("price");
            priceElement.setTextContent("88");
            
            Element authorElement=document.createElement("author");
            authorElement.setTextContent("李刚");
            
            rootElement.appendChild(bookElement);
            bookElement.appendChild(nameElement);
            bookElement.appendChild(priceElement);
            bookElement.appendChild(authorElement);
        }
        
        document.appendChild(rootElement);
        
        TransformerFactory factory=TransformerFactory.newInstance();
        Transformer transformer=factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.transform(new DOMSource(document), new StreamResult(new File("target/1.xml")));
    }
	
}
