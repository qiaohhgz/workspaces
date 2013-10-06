package com.common;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.SimpleTimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: chokkh01
 * Date: Apr 10, 2009
 * Time: 9:54:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    public static final long MILLI_SECOND_PER_DAY = 24 * 60 * 60 * 1000;

    public static final SimpleTimeZone TZ_LA = new SimpleTimeZone
            (
                    -28800000,
                    "GMT-08:00",
                    Calendar.APRIL, 1, -Calendar.SUNDAY,
                    7200000,
                    Calendar.OCTOBER, -1, Calendar.SUNDAY,
                    7200000,
                    3600000
            );

    public static final SimpleTimeZone TZ_NY = new SimpleTimeZone
            (
                    -18000000,
                    "GMT-05:00",
                    Calendar.APRIL, 1, -Calendar.SUNDAY,
                    7200000,
                    Calendar.OCTOBER, -1, Calendar.SUNDAY,
                    7200000,
                    3600000
            );

    private static final SimpleDateFormat dateFormatter1 = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormatter3 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
    private static final SimpleDateFormat dateFormatter4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    private static final SimpleDateFormat dateFormatter5 = new SimpleDateFormat("yyyy-MM-dd");

    public static String format1(Date dt) {
        if (dt == null) return "null";
        else return dateFormatter1.format(dt);
    }

    public static String format2(Date dt) {
        if (dt == null) return "null";
        else return dateFormatter2.format(dt);
    }

    public static String format3(Date dt) {
        if (dt == null) return "null";
        else return dateFormatter3.format(dt);
    }

    public static String format4(Date dt) {
        if (dt == null) return "null";
        else return dateFormatter4.format(dt);
    }

    public static String format5(Date dt) {
        if (dt == null) return "null";
        else return dateFormatter5.format(dt);
    }

    /**
     * Get Yesterday Date Object
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, (-1));
        return calendar.getTime();
    }

    public static Date getYesterdayWithoutTimePart() {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, (-1));
        calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * Get Yesterday Calendar Object
     *
     * @return
     */
    public static Calendar getYesterdayCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, (-1));
        return calendar;
    }

    /**
     * Get Tomorrow Date Object
     *
     * @return
     */
    public static Date getTomorrow() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * Get Today Date Object
     *
     * @return
     */
    public static Date getToday() {
        return new Date();
    }

    /**
     * Get the date object in a specified string format
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getFormattedDate(Date date, String dateFormat) {
        String strFormattedDate = new SimpleDateFormat(dateFormat).format(date);
        return strFormattedDate;
    }

    /**
     * Get Previous day as a Date Object
     *
     * @return
     */
    public static Date getPreviousDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, (-1));
        return calendar.getTime();
    }

    /**
     * Get Next day as a Date Object
     *
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * get Date object for the year, month and date passed in
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date getDate(int year, int month, int date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    /**
     * get Date object for the year, month and date passed in
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date getDateFromRegularDateValues(int year, int month, int date) {
        month = month - 1;
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    /**
     * Get Previous Months start and end date
     *
     * @return
     */
    public static String[] getCurrentMonthStartAndEndDateStr(String dateFormat) {
        String[] startAndEndDateOfMonth = new String[2];
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        startAndEndDateOfMonth[0] = getFormattedDate(calendar.getTime(), dateFormat);

        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, numberOfDaysInMonth);
        startAndEndDateOfMonth[1] = getFormattedDate(calendar.getTime(), dateFormat);

        return startAndEndDateOfMonth;
    }

    /**
     * Get Previous Months start and end date
     *
     * @return
     */
    public static Date[] getPreviousMonthStartAndEndDate() {
        Date[] startAndEndDateOfMonth = new Date[2];
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, (-1));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        startAndEndDateOfMonth[0] = calendar.getTime();

        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, numberOfDaysInMonth);
        startAndEndDateOfMonth[1] = calendar.getTime();

        return startAndEndDateOfMonth;
    }

    /**
     * Get the number of days in the month
     *
     * @param year
     * @param month
     * @return
     */
    public static int numberOfDaysInMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String createFromAndToDateFilter(Date fromDate, Date toDate, String dbDateField) {
        StringBuilder dateFilter = new StringBuilder();
        if (fromDate != null) {
            Date fromFilterDate = fromDate;
            dateFilter.append(" " + dbDateField + " >= ");
            dateFilter.append("CONVERT(datetime, '" + getFormattedDate(fromFilterDate, "MM/dd/yyyy") + "')");
            dateFilter.append(" ");
        }
        if (toDate != null) {
            Date toFilterDate = getNextDay(toDate);
            if (dateFilter.length() > 0) dateFilter.append(" and ");
            dateFilter.append(" " + dbDateField + " < ");
            dateFilter.append("CONVERT(datetime, '" + getFormattedDate(toFilterDate, "MM/dd/yyyy") + "')");
            dateFilter.append(" ");
        }
        return dateFilter.toString();
    }

    public static String createFromAndToDateFilter(Date fromDate, Date toDate, String fromDateFieldName, String toDateFieldName) {
        StringBuilder dateFilter = new StringBuilder();
        if (fromDate != null) {
            Date fromFilterDate = fromDate;
            dateFilter.append(" " + fromDateFieldName + " >= ");
            dateFilter.append("CONVERT(datetime, '" + getFormattedDate(fromFilterDate, "MM/dd/yyyy") + "')");
            dateFilter.append(" ");
        }
        if (toDate != null) {
            Date toFilterDate = getNextDay(toDate);
            if (dateFilter.length() > 0) dateFilter.append(" and ");
            dateFilter.append(" " + toDateFieldName + " < ");
            dateFilter.append("CONVERT(datetime, '" + getFormattedDate(toFilterDate, "MM/dd/yyyy") + "')");
            dateFilter.append(" ");
        }
        return dateFilter.toString();
    }

    public static Date changeDateToEndOfDay(Date endDate) {
        Date date = new Date(endDate.getTime());
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    public static Date getStartofDay(final Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set((date.getYear() + 1900), date.getMonth(), date.getDate(), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static Date getEndofDay(final Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set((date.getYear() + 1900), date.getMonth(), date.getDate(),
                calendar.getActualMaximum(Calendar.HOUR_OF_DAY), calendar.getActualMaximum(Calendar.MINUTE), calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static Date changeDateToStartOfDay(Date endDate) {
        Date date = new Date(endDate.getTime());
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    /**
     * Asumming the Date object does not have time component
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDaysBetweenDates(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            date1 = removeTimeFromDate(date1);
            date2 = removeTimeFromDate(date2);

            long totalMilliSecDiff = date2.getTime() - date1.getTime();
            return (int) Math.abs(Math.round((double) totalMilliSecDiff) / MILLI_SECOND_PER_DAY);
        } else {
            return -1;
        }
    }

    /**
     * Set the time component of the Date object to zero
     *
     * @param date
     * @return Date with the Hour/Minute/Second to zero
     */
    public static Date removeTimeFromDate(Date date) {
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            Calendar newCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            return newCalendar.getTime();
        }
        return null;
    }

    public static boolean isOnTheSameDate(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            date1 = removeTimeFromDate(date1);
            date2 = removeTimeFromDate(date2);
            return date1.equals(date2);
        } else {
            return false;
        }
    }

    public static boolean isOnTheSameMonthAndYear(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cDate1 = new GregorianCalendar();
            cDate1.setTime(date1);
            Calendar cDate2 = new GregorianCalendar();
            cDate2.setTime(date2);
            return cDate1.get(Calendar.MONTH) == cDate2.get(Calendar.MONTH) && cDate1.get(Calendar.YEAR) == cDate2.get(Calendar.YEAR);
        } else {
            return false;
        }
    }

    public static Date addDays(Date date, int daysToAdd) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int yearsToAdd) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, yearsToAdd);
        return calendar.getTime();
    }

    public static Date getLastDayOfOneYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parseDate(String date, String format) throws Exception {
        try {
            if (date == null || format == null || format.trim().length() == 0) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (ParseException pe) {
            throw new Exception("Error Parsing Date [" + date + "] using the format [" + format + "]", null);
        }
    }

    public static String formatSimpleDate(Date date) {
        if (date != null) {
            return formatDate(date, "MM/dd/yyyy");
        } else {
            return null;
        }
    }

    public static Calendar getYesterdayWithoutTimePart(TimeZone tz) {
        Calendar cal = Calendar.getInstance(tz);
        Calendar yest = getYesterdayCalendar();
        cal.set
                (
                        yest.get(Calendar.YEAR),
                        yest.get(Calendar.MONTH),
                        yest.get(Calendar.DAY_OF_MONTH)
                );
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    public static Calendar getDateWithoutTimePart(TimeZone tz, int daysShiftFromToday) {
        Calendar cal = Calendar.getInstance(tz);

        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, daysShiftFromToday);

        cal.set
                (
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)
                );
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    public static GregorianCalendar getDateWithoutTimePart(int daysShiftFromToday) {
        GregorianCalendar cal = new GregorianCalendar();

        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, daysShiftFromToday);
        c.getTime();

        cal.set
                (
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)
                );
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.getTime();

        return cal;
    }

    public static GregorianCalendar getDateWithoutTimePart(Date dt, int daysShiftFromDate) {
        GregorianCalendar cal = new GregorianCalendar();

        Calendar c = new GregorianCalendar();
        c.setTime(dt);
        c.add(Calendar.DATE, daysShiftFromDate);
        c.getTime();

        cal.set
                (
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)
                );
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.getTime();

        return cal;
    }

    private final static double AVERAGE_MILLIS_PER_MONTH = 365.24 * 24 * 60 * 60 * 1000 / 12;

    /**
     * calculate number of months between two dates **
     */
    public static int numberOfMonthsBetweenTwoDates(Date endDate, Date startDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / AVERAGE_MILLIS_PER_MONTH);
    }

    /**
     * calculate date depending on month count **
     */
    public static Date getOldMonths(Date date, int monthDuration) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, (-monthDuration));
        return calendar.getTime();
    }

    /**
     * calculate start date of specified month**
     */
    public static Date getStartDateofMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * For FrontEnd validation of date filter*
     */
    public static boolean validateDate(String date) {
        return validateDate(date, "MM/dd/yyyy");
    }

    /**
     * For FrontEnd validation of date filter*
     */
    public static boolean validateDate(String date, String format) {
        try {
            if (date != null && !date.equalsIgnoreCase("")) {
                String[] dateValues = date.split("\\/");
                if (dateValues.length != 3) {
                    return false;
                }
                if (dateValues[0].length() == 1) {
                    dateValues[0] = "0" + dateValues[0];
                }
                if (dateValues[1].length() == 1) {
                    dateValues[1] = "0" + dateValues[1];
                }
                date = dateValues[0] + "/" + dateValues[1] + "/" + dateValues[2];
                Date d = new SimpleDateFormat(format).parse(date);
                String d2 = new SimpleDateFormat(format).format(d);
                return d2.equalsIgnoreCase(date);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isWithinSQLDateRange(Date date) {
        try {
            if (date != null) {
                Date startDateRange = parseDate("01/01/1753", "MM/dd/yyyy");
                Date endDateRange = parseDate("12/31/9999", "MM/dd/yyyy");
                if (date.before(startDateRange)) {
                    return false;
                }
                if (date.after(endDateRange)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
