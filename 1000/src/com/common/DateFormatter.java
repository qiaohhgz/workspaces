package com.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a thin wrapper
 * around the SimpleDateFormat class aiming
 * simply to support formatting of null dates.
 */
public class DateFormatter {
    private String fs;
    private SimpleDateFormat dateFormatter;

    public DateFormatter(String fs){
        this.fs = fs;
        this.dateFormatter = new SimpleDateFormat(fs);
    }

    public String format(Date dt){
        if (dt==null) return "null";
        else return this.dateFormatter.format(dt);
    }
}


