package com.huhang.framework.reader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by joanna on 4/12/17.
 */
public abstract class XmlReader {
    public Document readXml(String fileName){
        SAXReader reader=new SAXReader();
        Document document=null;
        String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
            document=reader.read(new FileInputStream(path+fileName));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return document;
    }
}
