package com.jim.demo.bean;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/29/13
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Student {
    private Integer id;
    private String userName;
    private String password;

    public void init() {
        System.out.println("init method");
    }

    public Student() {
        System.out.println("constructor method");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("set id = " + id);
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("set user name = " + userName);
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("set password = " + password);
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + " id = " + this.getId() + " userName = " + this.getUserName() + " password = " + this.getPassword();
    }
}
