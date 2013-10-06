package com.jim.freemarker;

import com.jim.freemarker.model.UserBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 3/13/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFreemarkerUtil {
    FreemarkerUtil fu;
    Map<String, Object> root;

    @Before
    public void setUp() {
        fu = new FreemarkerUtil();
        root = new HashMap<String, Object>();
    }

    @Test//值传递
    public void test01() {
        root.put("username", "jim");
        fu.print("01.ftl", root);
        fu.printFile("01.ftl", root, "target/01.html");
    }

    @Test//传输对象
    public void test02() {
        root.put("user", new UserBean(1, "jim", 18));
        fu.print("02.ftl", root);
        fu.printFile("02.ftl", root, "target/02.html");
    }

    @Test//逻辑 if 标签
    public void test03() {
        root.put("user", new UserBean(1, "zhang", 18));
        fu.print("03.ftl", root);
        fu.printFile("03.ftl", root, "target/03.html");
    }

    @Test//循环 list 标签
    public void test04() {
        root.put("users", Arrays.asList(new UserBean(1, "zhang", 15), new UserBean(2, "wang", 22), new UserBean(3, "zhao", 30)));
        fu.print("04.ftl", root);
        fu.printFile("04.ftl", root, "target/04.html");
    }

    @Test//include标签使用
    public void test05() {
        root.put("username", "admin");
        fu.print("05.ftl", root);
        fu.printFile("05.ftl", root, "target/05.html");
    }

    @Test//空值处理
    public void test06() {
        root.put("user", new UserBean(1, "zhang", 15));
        fu.print("06.ftl", root);
        fu.printFile("06.ftl", root, "target/06.html");
    }

    @Test//声明变量     数字，字符串，布尔，日期
    public void test07() {
        root.put("now", new Date());
        fu.print("07.ftl", root);
        fu.printFile("07.ftl", root, "target/07.html");
    }

    @Test//序列定义
    public void test08() {
        fu.print("08.ftl", root);
    }

    @After
    public void after() {
        System.out.println("\n=======================");
    }
}

