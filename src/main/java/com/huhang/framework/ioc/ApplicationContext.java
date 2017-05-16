package com.huhang.framework.ioc;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by joanna on 4/5/17.
 */
public enum ApplicationContext {
    INSTANCE;
    private Configuration configuration;
    private Map<String, Object> beans;



    ApplicationContext(){
        configuration=new Configuration("context.xml");
        beans=new HashMap<>();
        instantiateBeans();
    }
    private void instantiateBeans(){
        List<BeanInfo> beanInfos = configuration.getBeanInfos();
        for(Iterator<BeanInfo> i=beanInfos.iterator(); i.hasNext(); ){
            BeanInfo beanInfo = i.next();
            Object target=getBean(beanInfo.getId());
            //deal with properties: interceptor and fields
            for(Map<String, String> property:beanInfo.getProperties()) {
                //interceptor
                if(property.get("name").equals("interceptor")){
                    ActionBeanDecorator actionBeanDecorator =
                            new ActionBeanDecorator(target, getBean(property.get("ref")));
                    beans.put(beanInfo.getId(), actionBeanDecorator);
                }else{//normal conditions, setter injection
                    String value=property.get("value");
                    if (value != null) {
                        setterInjection(target, property.get("name"), value);
                    }
                    String ref = property.get("ref");
                    if (ref != null) {
                        setterInjection(target, property.get("name"), getBean(ref));
                    }
                }
            }
        }
    }
    public Object getBean(String id){
        if(beans.get(id)!=null) {
            return beans.get(id);
        }else{
            Object object=instantiateBean(configuration.getBeanInfo(id));
            beans.put(id, object);
            return object;
        }
    }
    private Object instantiateBean(BeanInfo beanInfo){
        Object object=null;
        try {
            object=Class.forName(beanInfo.getClazz()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
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
