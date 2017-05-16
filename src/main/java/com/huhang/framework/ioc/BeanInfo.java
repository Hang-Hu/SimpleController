package com.huhang.framework.ioc;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by joanna on 4/5/17.
 */
class BeanInfo {
    private String id;
    private String clazz;
    private List<Map<String, String>> properties=new LinkedList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<Map<String, String>> getProperties() {
        return properties;
    }

    public void setProperties(List<Map<String, String>> properties) {
        this.properties = properties;
    }
}
