package com.jimqiao.job;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/23/13
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestJobHelper {
    @Test
    public void testHello() throws Exception {
        JobHelper helper = new JobHelper();
        helper.hello();
    }

    @Test
    public void testSpring() throws Exception {
        System.out.println("Test start.");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-jms.xml");
        //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
        //context.getBean("startQuertz");
        System.out.print("Test end..");
    }
}
