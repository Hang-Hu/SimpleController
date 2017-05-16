package com.huhang.framework.ioc;

import com.huhang.framework.ioc.exception.ContextException;
import com.huhang.framework.reader.XmlReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by joanna on 4/12/17.
 */
public class ContextXmlReader extends XmlReader{
    public List<BeanInfo> readConfiguration(String fileName) throws ContextException {
        List<BeanInfo> beanInfos=new LinkedList<>();
        Document document = readXml(fileName);
        Element rootElement = document.getRootElement();
        for(Iterator<Element> i = rootElement.elementIterator("bean"); i.hasNext(); ){
            Element element = i.next();
            BeanInfo beanInfo =new BeanInfo();
            beanInfo.setId(element.attributeValue("id"));
            String clazz=element.attributeValue("class");
            if((clazz!=null)&&(!clazz.equals(""))) {
                beanInfo.setClazz(clazz);
            }else{
                throw new ContextException("Bean "+beanInfo.getId()+" doesn't have class attribute");
            }
            readProperties(element, beanInfo);
            beanInfos.add(beanInfo);
        }
        return beanInfos;
    }

    private void readProperties(Element element, BeanInfo beanInfo) throws ContextException {
        for(Iterator<Element> j = element.elements("property").iterator(); j.hasNext(); ){
            List<Map<String, String>> properties = beanInfo.getProperties();
            Map<String, String> property=new HashMap<>();
            Element propertyElement = j.next();
            String name=propertyElement.attributeValue("name");
            if(name==null||name.equals("")){
                throw new ContextException("No property name for bean "+beanInfo.getId());
            }else{
                property.put("name", name);
            }
            String ref=propertyElement.attributeValue("ref");
            if((ref!=null)&&(!ref.equals("")))
                property.put("ref", ref);
            String value=propertyElement.attributeValue("value");
            if((value!=null)&&(!value.equals("")))
                property.put("value", value);
            if((ref!=null)&&(value!=null))
                throw new ContextException("Ref and value can't appear in the same property for property: "+name);
            properties.add(property);
        }
    }


}
