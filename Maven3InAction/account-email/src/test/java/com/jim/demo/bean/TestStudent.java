package com.jim.demo.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/29/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestStudent {
    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-override.xml");
        Student stu = (Student) ctx.getBean("stu");
        System.out.println(stu);

    }
}
