package com.greendao.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by itsdon on 16/7/30.
 */
@Entity
public class User {
 
    @Unique
    private String name;
    private String age;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    @Generated(hash = 2102286658)
    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
