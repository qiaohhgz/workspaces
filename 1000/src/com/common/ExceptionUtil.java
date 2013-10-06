package com.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: petrop01
 * Date: Oct 28, 2011
 * Time: 7:07:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionUtil {

    public static String getStackTrace(Throwable t)
    {
        if (t==null) return "";

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        pw.println();
        t.printStackTrace(pw);
        pw.println();
        pw.flush();
        String s = sw.toString();
        pw.close();
        pw = null;
        return s;
    }

    public static String getExceptionInfo(Exception e){
        if (e==null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        sb.append("Exception Message: ");
        sb.append("\r\n");
        sb.append(e.getMessage());
        sb.append("\r\n");
        sb.append("Exception Stack Trace: ");
        sb.append("\r\n");
        sb.append(getStackTrace(e));
        sb.append("\r\n");
        sb.append("\r\n");
        return sb.toString();
    }

}


