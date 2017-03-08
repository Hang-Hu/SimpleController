package com.huhang.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class LogWriter {
	protected ActionLog actionLog;
	public LogWriter(ActionLog actionLog) {
		this.actionLog=actionLog;
	}
	public void log(String path) throws IOException, DocumentException{
		Document logDocument=readDocument(path);
		writeToFile(logDocument,path);
	}
	public Document readDocument(String path) throws DocumentException{
		SAXReader reader=new SAXReader();
		Document logDocument=reader.read(path);
		Element log=logDocument.getRootElement();
		Element action=log.addElement("action");
		action.addElement("name").addText(actionLog.getName());
		action.addElement("s-time").addText(actionLog.getStartTime().toString());
		action.addElement("e-time").addText(actionLog.getEndTime().toString());
		action.addElement("result").addText(actionLog.getResult());
		System.out.println("Write "+log.getName());
		return logDocument;
	}
	public void writeToFile(Document logDocument,String path) throws IOException{
		XMLWriter writer=new XMLWriter(new FileWriter(path));
		writer.write(logDocument);
		writer.close();
	}
}
