package com.huhang.framework.database;

import com.huhang.framework.database.model.MappingDescriptor;
import com.huhang.framework.reader.XmlReader;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.*;

/**
 * Created by joanna on 4/12/17.
 */
public class DatabaseXmlReader extends XmlReader {
    public MappingDescriptor readConfiguration(String fileAddress){
        MappingDescriptor mappingDescriptor=new MappingDescriptor();
        Document document = readXml(fileAddress);
        Element rootElement=document.getRootElement();
        for(Iterator<Element> i=rootElement.elementIterator("class");i.hasNext();){
            Element classElement =  i.next();
            MappingDescriptor.ClassDescriptor classDescriptor=new MappingDescriptor.ClassDescriptor();
            classDescriptor.setClassAttribute(readClassAttribute(classElement.attributes()));
            classDescriptor.setId(readId(classElement.element("id")));
            classDescriptor.setProperties(readProperties(classElement.elements("property")));
            mappingDescriptor.setClassDescriptor(classDescriptor);
        }
        return mappingDescriptor;
    }

    private Map<String, String> readClassAttribute(List<Attribute> attributes) {
        Map<String, String> attributeMap=new HashMap<>();
        for(Attribute attribute:attributes){
            attributeMap.put(attribute.getName(), attribute.getValue());
        }
        return attributeMap;
    }

    private List<Map<String, String>> readProperties(List<Element> propertyList) {
        List<Map<String, String>> propertyMaps=new LinkedList<>();
        for(Iterator<Element> i=propertyList.listIterator(); i.hasNext(); ){
            Element propertyElement = i.next();
            Map<String, String> propertyMap=new HashMap<>();
            for(Iterator<Attribute> j = propertyElement.attributeIterator(); j.hasNext(); ){
                Attribute attribute = j.next();
                propertyMap.put(attribute.getName(), attribute.getValue());
            }
            propertyMaps.add(propertyMap);
        }
        return propertyMaps;
    }

    private Map<String,String> readId(Element id) {
        Map<String, String> idMap=new HashMap<>();
        for(Iterator<Attribute> i = id.attributeIterator(); i.hasNext(); ){
            Attribute attribute= i.next();
            idMap.put(attribute.getName(), attribute.getValue());
        }
        return idMap;
    }
    
}
