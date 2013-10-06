package com.jim.powermock.mockito.first;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentBean {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String info = String.format("{id : %d, name : %s}", id, name);
        return info;
    }
}
