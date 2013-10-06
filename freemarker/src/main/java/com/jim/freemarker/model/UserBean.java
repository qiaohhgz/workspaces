package com.jim.freemarker.model;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 3/13/13
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserBean {
    private int id;
    private String name;
    private int age;
    private GroupBean group;

    public UserBean(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }
}
