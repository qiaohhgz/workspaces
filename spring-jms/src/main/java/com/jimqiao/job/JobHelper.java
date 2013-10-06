package com.jimqiao.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/23/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobHelper {
    public void hello() {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeString = format.format(time);
        System.out.println("timeString = " + timeString);
    }
}
