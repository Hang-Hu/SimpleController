package com.huhang.userlevel.log;

import java.io.FileWriter;
import java.io.IOException;

import com.huhang.userlevel.interceptor.ActionLog;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class LogWriter {

	public void log(ActionLog actionLog){
        String path=Thread.currentThread().getContextClassLoader().getResource("log.xml").getPath();
        Document logDocument= null;
        try {
            logDocument = readDocument(path, actionLog);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            writeToFile(logDocument,path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private Document readDocument(String path, ActionLog actionLog) throws DocumentException{
		SAXReader reader=new SAXReader();
		Document logDocument=reader.read(path);
		Element log=logDocument.getRootElement();
		Element action=log.addElement("action");
		action.addElement("name").addText(actionLog.getName());
		action.addElement("s-time").addText(actionLog.getStartTime().toString());
		action.addElement("e-time").addText(actionLog.getEndTime().toString());
		action.addElement("result").addText(actionLog.getResult());
		System.out.println("Write "+log.getName());
        System.out.println(path);
        return logDocument;
	}
	private void writeToFile(Document logDocument,String path) throws IOException{
		XMLWriter writer=new XMLWriter(new FileWriter(path));
		writer.write(logDocument);
		writer.close();
	}
}
