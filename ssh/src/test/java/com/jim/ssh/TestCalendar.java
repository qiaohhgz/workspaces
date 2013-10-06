package com.jim.ssh;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/20/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestCalendar {
    @Test
    public void test1() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DATE, -10);

        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.DATE, -1);

        System.out.println(c.before(c2));
        Date time = c2.getTime();
        System.out.println(c.before(time));

    }
}
