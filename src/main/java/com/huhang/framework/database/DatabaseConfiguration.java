package com.huhang.framework.database;

import com.huhang.framework.database.model.MappingDescriptor;

import java.util.List;

/**
 * Created by joanna on 4/12/17.
 */
public class DatabaseConfiguration {
    private List<MappingDescriptor> mappingDescriptors;

    private DatabaseXmlReader databaseXmlReader;
    private SessionFactory sessionFactory;
    public MappingDescriptor getMappingDescriptor(String className){
        for(MappingDescriptor mappingDescriptor:mappingDescriptors){
            System.out.println(mappingDescriptor.getClassDescriptor().getClassAttribute().get("name"));
            if(mappingDescriptor.getClassDescriptor().getClassAttribute().get("name").equals(className)){
                return mappingDescriptor;
            }
        }
        return null;
    }
    public void setMappingDescriptors(){
        List<String> mappingLocations = sessionFactory.getMappingLocations();
        for(String mappingLocation: mappingLocations){
            String classpath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
            readMappingDescriptor(
                    classpath+mappingLocation.substring(mappingLocation.indexOf(":"), mappingLocation.length()));
        }
    }
    public void readMappingDescriptor(String mappingFileAddress){
        mappingDescriptors.add(databaseXmlReader.readConfiguration(mappingFileAddress));
    }
}
