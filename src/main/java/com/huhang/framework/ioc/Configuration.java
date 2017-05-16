package com.huhang.framework.ioc;

import com.huhang.framework.ioc.exception.ContextException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by joanna on 4/5/17.
 */
class Configuration {
    private List<BeanInfo> beanInfos;

    public List<BeanInfo> getBeanInfos() {
        return beanInfos;
    }
    public BeanInfo getBeanInfo(String id){
        for(BeanInfo beanInfo:beanInfos){
            if(beanInfo.getId().equals(id))
                return beanInfo;
        }
        return null;
    }
    public Configuration(String fileName){
        try {
            this.beanInfos = new ContextXmlReader().readConfiguration(fileName);
        } catch (ContextException e) {
            e.printStackTrace();
        }
    }


}
