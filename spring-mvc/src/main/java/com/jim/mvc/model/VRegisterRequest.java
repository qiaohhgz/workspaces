package com.jim.mvc.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 10/31/13
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class VRegisterRequest {
    private String name;
    private List<VHobby> hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VHobby> getHobby() {
        return hobby;
    }

    public void setHobby(List<VHobby> hobby) {
        this.hobby = hobby;
    }
}
