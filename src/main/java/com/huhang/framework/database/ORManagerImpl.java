package com.huhang.framework.database;

import com.huhang.framework.database.model.MappingDescriptor;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by joanna on 4/12/17.
 */
public class ORManagerImpl implements ORManager{
    SqlImplementor sqlImplementor;
    DatabaseConfiguration databaseConfiguration;
    public <T> T get(Class<T> clazz, String id){
        MappingDescriptor.ClassDescriptor classDescriptor =
                databaseConfiguration.getMappingDescriptor(clazz.getName()).getClassDescriptor();
        T t;
        try {
            t=clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
    private boolean setterInjection(Object target, String propertyName, Object property){
        try {
            Field field=target.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(target, property);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
