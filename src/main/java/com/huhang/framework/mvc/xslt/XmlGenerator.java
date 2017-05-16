package com.huhang.framework.mvc.xslt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//value in textView will be changed based on current user.
public class XmlGenerator {


	public void updateXML(String path,String name,int age){
        Document document= null;
        try {
            document = readDocument(path, name, age);
            writeToFile(document, path);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private Document readDocument(String path,String name, int age) throws DocumentException{
		SAXReader reader=new SAXReader();
		Document document=reader.read(path);
		Element root=document.getRootElement();
		List listOfTextView=document.selectNodes("view/body/form/textView");
		for(Iterator<Element> i=listOfTextView.listIterator();i.hasNext();){
			Element textView=i.next();
			if(textView.selectSingleNode("name").getText().equals("userName")){
				textView.selectSingleNode("value").setText(name);
			}
			if(textView.selectSingleNode("name").getText().equals("userAge")){
				textView.selectSingleNode("value").setText(""+age);
			}
		}
		System.out.println(document);
		return document;
	}
	private void writeToFile(Document document, String path) throws IOException{
		XMLWriter writer=new XMLWriter(new FileWriter(path));
		writer.write(document);
		writer.close();
		//here written back in program, but may not actually written by OS.
	}
}
