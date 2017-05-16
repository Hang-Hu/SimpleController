package com.huhang.framework.database.model;

import java.util.List;
import java.util.Map;

/**
 * Created by joanna on 4/12/17.
 */
public class MappingDescriptor {

    private ClassDescriptor classDescriptor;

    public void setClassDescriptor(ClassDescriptor classDescriptor) {
        this.classDescriptor = classDescriptor;
    }

    public ClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }

    public static class ClassDescriptor{
        private Map<String, String> classAttribute;
        private Map<String, String> id;
        private List<Map<String, String>> properties;

        public Map<String, String> getClassAttribute() {
            return classAttribute;
        }

        public void setClassAttribute(Map<String, String> classAttribute) {
            this.classAttribute = classAttribute;
        }

        public Map<String, String> getId() {
            return id;
        }

        public void setId(Map<String, String> id) {
            this.id = id;
        }

        public List<Map<String, String>> getProperties() {
            return properties;
        }

        public void setProperties(List<Map<String, String>> properties) {
            this.properties = properties;
        }
    }

}
