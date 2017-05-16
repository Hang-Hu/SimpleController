package com.huhang.userlevel.entity;

import java.io.Serializable;

/**
 * Created by joanna on 4/5/17.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private String emailAddress;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean validate(){
        if((("Tom".equals(name))&&("123".equals(password)))||("Huhang".equals(name)&&("123".equals(password))))
            return true;
        else
            return false;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
