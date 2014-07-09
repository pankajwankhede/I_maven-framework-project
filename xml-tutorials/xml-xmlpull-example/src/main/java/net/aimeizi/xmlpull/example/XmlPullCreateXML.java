package net.aimeizi.xmlpull.example;

import java.io.File;
import java.io.FileOutputStream;

import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlSerializer;

/**
 * 使用xmlpull api创建xml
 * @author welcome
 *
 */
public class XmlPullCreateXML {
	
	 public static void main(String[] args) throws Exception{
	        XmlSerializer xmlSerializer=new KXmlSerializer();
	        xmlSerializer.setOutput(new FileOutputStream(new File("target/student.xml")), "utf-8");
	        xmlSerializer.startDocument(null, true);
	        xmlSerializer.startTag(null, "data");
	        for (int i = 0; i < 10; i++) {
	            xmlSerializer.startTag(null, "student");
	            xmlSerializer.attribute(null, "id", ""+(i+1));
	            
	            xmlSerializer.startTag(null, "name");
	            xmlSerializer.text("student"+i);
	            xmlSerializer.endTag(null, "name");
	            
	            xmlSerializer.startTag(null, "age");
	            xmlSerializer.text((i+10)+"");
	            xmlSerializer.endTag(null, "age");
	            
	            
	            xmlSerializer.startTag(null, "sex");
	            if(i%2==0){
	                xmlSerializer.text("女");
	            }else{
	                xmlSerializer.text("男");
	            }
	            xmlSerializer.endTag(null, "sex");
	            
	            
	            xmlSerializer.startTag(null, "address");
	            xmlSerializer.text("陕西西安");
	            xmlSerializer.endTag(null, "address");
	            
	            xmlSerializer.endTag(null, "student");
	        }
	        
	        xmlSerializer.endTag(null, "data");
	        
	        xmlSerializer.endDocument();
	        
	        xmlSerializer.flush();
	        
	    }
	
}
