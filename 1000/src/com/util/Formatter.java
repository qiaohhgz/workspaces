package com.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Formatter {
   // private static final Logger LOG = Logger.getLogger(Formatter.class);

    /**
     * Enumeration of date and or time format types.
     */
    public enum DateFieldType {
        /**
         * Format type short date.
         */
        DATE_SHORT,
        /**
         * Format type medium date.
         */
        DATE_MEDIUM,
        /**
         * Format type long date.
         */
        DATE_LONG,
        /**
         * Format type short date time.
         */
        DATE_TIME_SHORT,
        /**
         * Format type medium date time.
         */
        DATE_TIME_MEDIUM,
        /**
         * Format type long date time.
         */
        DATE_TIME_LONG,
        /**
         * Format type long time.
         */
        TIME_SHORT,
        /**
         * Format type medium time.
         */
        TIME_MEDIUM,
        /**
         * Format type long time.
         */
        TIME_LONG,
    }

    /**
     * Enumeration of format types, which include the DateFieldTypes.
     */
    public enum FieldType {
        /**
         * Format type text.
         */
        TEXT,
        /**
         * Format type currency.
         */
        CURRENCY,
        /**
         * Enumeration of format types, which include the DateFieldTypes.
         */
        NUMBER,
        /**
         * Format type short date.
         */
        DATE_SHORT,
        /**
         * Format type medium date.
         */
        DATE_MEDIUM,
        /**
         * Format type long date.
         */
        DATE_LONG,
        /**
         * Format type short date time.
         */
        DATE_TIME_SHORT,
        /**
         * Format type medium date time.
         */
        DATE_TIME_MEDIUM,
        /**
         * Format type long date time.
         */
        DATE_TIME_LONG,
    }

    /**
     * Date format pattern as java.util.Date uses when toString() is called.
     */
    public static final String TO_STRING_PATTERN = "EEE MMM dd hh:mm:ss zzz yyyy";
    /**
     * Date format pattern as used by the DB.
     */
    public static final String DB_PATTERN = "MM-dd-yyyy";
    /**
     * Date format pattern as Auditable.
     */
    public static final String AUDIT_PATTERN = "yyyy-MM-dd hh:mm:ss";
    /**
     * Array of supported date format patterns.
     */
    public static final String[] DATE_PATTERNS = {TO_STRING_PATTERN, AUDIT_PATTERN, DB_PATTERN};

    /**
     * Generic method for formatting the value string to according to
     * the field type.
     *
     * @param value     - the string to be formatted
     * @param fieldType - the type of field (One of FieldType)
     * @return value    - the formatted string
     */
    public static String format(
            String value, FieldType fieldType) {
        return format(value, fieldType, 0);
    }

    /**
     * Generic method for formatting a float.
     *
     * @param value    - the float to be formatted
     * @param decimals - the number of decimals
     * @return value    - the string formatted as a float
     */
    public static String format(float value, int decimals) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setMinimumFractionDigits(decimals);
        numberFormat.setMaximumFractionDigits(decimals);
        Double doubleValue = new Double(value);
        String txtValue = numberFormat.format(doubleValue);
        return txtValue;
    }

    /**
     * Generic method for formatting the value string
     *
     * @param value     - the string to be formatted
     * @param fieldType - the type of field.
     * @param decimals  - number of decimals, used for NUMBER and CURRENCY
     * @return value    - the formatted string
     */
    public static String format(
            String value, FieldType fieldType, int decimals) {
        if (fieldType == null || value == null) {
            return value;
        }
        switch (fieldType) {
            case NUMBER: {
                value = formatNumber(value, decimals);
                break;
            }
            case CURRENCY: {
                value = formatCurrency(value, decimals);
                break;
            }
            case DATE_LONG: {
                value = formatDate(value, DateFormat.LONG);
                break;
            }
            case DATE_MEDIUM: {
                value = formatDate(value, DateFormat.MEDIUM);
                break;
            }
            case DATE_SHORT: {
                value = formatDate(value, DateFormat.SHORT);
                break;
            }
            case DATE_TIME_LONG: {
                value = formatDateTime(value, DateFormat.LONG);
                break;
            }
            case DATE_TIME_MEDIUM: {
                value = formatDateTime(value, DateFormat.MEDIUM);
                break;
            }
            case DATE_TIME_SHORT: {
                value = formatDateTime(value, DateFormat.SHORT);
                break;
            }
            default: {
                //LOG.log(Priority.WARN, "fieldType=" + fieldType
                //        + ". FieldType should be one of " + FieldType.values());
                //nothing matched, just return the value
            }
        }
        return value;
    }

    /**
     * Generic method for formatting the date. The return String will be an empty
     * String if the formatting fails.
     *
     * @param date          - the date to be formatted
     * @param dateFieldType - the type of field
     * @return formattedString  - the formatted string
     */
    public static String format(
            Date date, DateFieldType dateFieldType) {
        String formattedString = "";
        if (dateFieldType == null || date == null) {
            return null;
        }
        switch (dateFieldType) {
            case DATE_LONG: {
                formattedString = formatDate(date, DateFormat.LONG);
                break;
            }
            case DATE_MEDIUM: {
                formattedString = formatDate(date, DateFormat.MEDIUM);
                break;
            }
            case DATE_SHORT: {
                formattedString = formatDate(date, DateFormat.SHORT);
                break;
            }
            case DATE_TIME_LONG: {
                formattedString = formatDateTime(date, DateFormat.LONG);
                break;
            }
            case DATE_TIME_MEDIUM: {
                formattedString = formatDateTime(date, DateFormat.MEDIUM);
                break;
            }
            case DATE_TIME_SHORT: {
                formattedString = formatDateTime(date, DateFormat.SHORT);
                break;
            }
            case TIME_LONG: {
                formattedString = formatTime(date, DateFormat.LONG);
                break;
            }
            case TIME_MEDIUM: {
                formattedString = formatTime(date, DateFormat.MEDIUM);
                break;
            }
            case TIME_SHORT: {
                formattedString = formatTime(date, DateFormat.SHORT);
                break;
            }
            default: {
               // LOG.log(Priority.WARN, "dateFieldType=" + dateFieldType
               //         + ". DateFieldType should be one of " + DateFieldType.values());
                formattedString = date.toString();
            }
        }
        return formattedString;
    }

    /**
     * Formats to a number style.
     *
     * @param value    - value of the number
     * @param decimals - The number of decimals
     * @return formattedString - the number formatted
     */
    private static String formatNumber(
            String value, int decimals) {
        if (value != null && !"".equals(value)) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            numberFormat.setMinimumFractionDigits(decimals);
            numberFormat.setMaximumFractionDigits(decimals);
            Double doubleValue = new Double(value);
            value = numberFormat.format(doubleValue);
            return value;
        } else {
            return "";
        }
    }

    /**
     * Formats to a currency style.
     *
     * @param value    - value of the currency
     * @param decimals - The number of decimals
     * @return formattedString - the currency formatted
     */
    private static String formatCurrency(
            String value, int decimals) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setMinimumFractionDigits(decimals);
        numberFormat.setMaximumFractionDigits(decimals);
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        value = "$" + numberFormat.format(bigDecimal);
        return value;
    }

    /**
     * Tries to parse the value String to a Date using the DateFormat class, instantiated
     * with different patterns. The supported patterns are enumerated in the DatePatterns
     * enumeration. After a successful parse it formats the date to a localized date format.
     *
     * @param value - String value containing a date
     * @return formattedString - the date formatted
     */
    private static String formatDate(
            String value, int dateFormatType) {
        Date date = parseDate(value);
        return formatDate(date, dateFormatType);
    }

    /**
     * Tries to parse the value String to a Date using the DateFormat class, instantiated
     * with different patterns. The supported patterns are enumerated in the DatePatterns
     * enumeration. After a successful parse it formats the date to a localized datetime format.
     *
     * @param value - String value containing a date
     * @return formattedString - the date formatted
     */
    private static String formatDateTime(
            String value, int dateFormatType) {
        Date date = parseDate(value);
        return formatDateTime(date, dateFormatType);
    }

    /**
     * Converts the date into a Date String and the
     * formatType as defined in DateFormat.
     *
     * @param date           - date which needs to be displayed
     * @param dateFormatType - dateFormatType, one of DateFormat types.
     * @return formattedString - the date formatted
     */
    private static String formatDate(
            Date date, int dateFormatType) {
        SimpleDateFormat dateFormatTo;
        dateFormatTo = (SimpleDateFormat) SimpleDateFormat.getDateInstance(
                dateFormatType, Locale.US);
        String datePattern = dateFormatTo.toPattern();
        if (datePattern.indexOf("yyyy") < 0) {
            datePattern = datePattern.replaceAll("y", "yy");
            dateFormatTo.applyPattern(datePattern);
        }

        return dateFormatTo.format(date);
    }

    /**
     * Converts the date into a Date String and the
     * formatType as defined in DateFormat.
     *
     * @param date           - date which needs to be displayed
     * @param dateFormatType - dateFormatType, one of DateFormat types.
     * @return formattedString - the date formatted
     */
    private static String formatDateTime(
            Date date, int dateFormatType) {
        SimpleDateFormat dateFormatTo;
        dateFormatTo = (SimpleDateFormat) DateFormat.getDateTimeInstance(
                dateFormatType, dateFormatType, Locale.US);
        String datePattern = dateFormatTo.toPattern();
        if (datePattern.indexOf("yyyy") < 0) {
            datePattern = datePattern.replaceAll("y", "yy");
            dateFormatTo.applyPattern(datePattern);
        }
        return dateFormatTo.format(date);
    }

    private static String formatTime(Date date, int timeFormatType) {
        SimpleDateFormat dateFormatTo = (SimpleDateFormat) DateFormat.getTimeInstance(timeFormatType, Locale.US);
        return dateFormatTo.format(date);
    }

    /**
     * Tries to parse the value String to a Date using the DateFormat class, instantiated
     * with different patterns. The supported patterns are enumerated in the DatePatterns
     * enumeration.
     *
     * @param value - String value containing a date
     * @return date  - the date, if successfully parsed
     */
    private static Date parseDate(String value) {
        Date date = null;
        for (int i = 0; i < DATE_PATTERNS.length; i++) {
            try {
                String pattern = DATE_PATTERNS[i].toString();
                DateFormat dateFormatFrom = new SimpleDateFormat(pattern);
                date = dateFormatFrom.parse(value);
                break;
            } catch (ParseException e) {
                //only log an error if there are no other supported date patterns to try
                if (i == DateFieldType.values().length - 1) {
                   // LOG.log(Priority.ERROR, "Could not parse to Date. "
                    //        + "The incoming string is:" + value, e);
                }
            }
        }
        return date;
    }

    /**
     * Parse float from string.
     *
     * @param value    string value
     * @param decimals - Number of decimals
     * @return fVal - floating point value
     * @throws ParseException ParseException
     */
    public static float parseFloat(String value, int decimals)
            throws ParseException {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(decimals, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.floatValue();
    }

    /**
     * Formats to a currency style.
     *
     * @param value    - value of the currency
     * @param decimals - The number of decimals
     * @return formattedString - the currency formatted
     */
    public static float roundFloat(float value, int decimals) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(decimals, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.floatValue();
    }

    /**
     * Formats to a currency style.
     *
     * @param value    - value of the currency
     * @param decimals - The number of decimals
     * @return formattedString - the currency formatted
     */
    public static String formatCurrency(float value, int decimals) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setMinimumFractionDigits(decimals);
        numberFormat.setMaximumFractionDigits(decimals);
        return "$" + numberFormat.format(value);
    }

    /**
     * Formats to a percent style.
     *
     * @param value    - value of the currency
     * @param decimals - The number of decimals
     * @return formattedString - the currency formatted
     */
    public static String formatPercent(float value, int decimals) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setMinimumFractionDigits(decimals);
        numberFormat.setMaximumFractionDigits(decimals);
        return numberFormat.format(value) + "%";
    }

    /**
     * Parse int from string.
     *
     * @param value string value
     * @return iVal - integer value
     * @throws ParseException ParseException
     */
    public static float parseInt(String value)
            throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) numberFormat;
        return numberFormat.parse(value).intValue();
    }

    /**
     * Private default constructor
     */
    private Formatter() {
    }

    public static String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int leftSeconds = seconds - minutes * 60;
        StringBuffer sb = new StringBuffer();
        sb.append(minutes);
        sb.append("min ");
        sb.append(leftSeconds);
        sb.append("sec");
        return sb.toString();

/*        int hours = seconds / 3600;
        int minutes = (seconds - hours / 3600) / 60;
        int leftSeconds = seconds - hours / 3600 - minutes * 60;
        StringBuffer sb = new StringBuffer();
        if (hours >= 10) {
            sb.append(hours);
        } else {
            sb.append("0" + hours);
        }
        sb.append(":");
        if (minutes >= 10) {
            sb.append(minutes);
        } else {
            sb.append("0" + minutes);
        }
        sb.append(":");
        if (leftSeconds >= 10) {
            sb.append(leftSeconds);
        } else {
            sb.append("0" + leftSeconds);
        }
        return sb.toString();
  */
    }

    public static String formatPhoneString(String phone) {
        StringBuilder sb = new StringBuilder();
        if (phone == null || phone.trim().isEmpty()) return sb.toString();

        //Remove all Empty Spaces
        phone = phone.replaceAll("\\s", "");

        if (phone.indexOf("-") > 0) return phone;     //already formatted.
        StringBuffer newPhoneSb = new StringBuffer(phone);

        if (newPhoneSb.length() > 10) {
            sb.append(newPhoneSb.substring(0, newPhoneSb.length() - 10) + "-");
            newPhoneSb = new StringBuffer(newPhoneSb.substring(newPhoneSb.length() - 10, newPhoneSb.length()));
        }
        if (newPhoneSb.length() == 10) {
            sb.append(newPhoneSb.substring(0, 3));
            newPhoneSb = new StringBuffer(newPhoneSb.substring(3));
        }
        if (newPhoneSb.length() == 7) {
            sb.append("-" + newPhoneSb.substring(0, 3));
            newPhoneSb = new StringBuffer(newPhoneSb.substring(3));
        }
        sb.append("-" + newPhoneSb);
        return sb.toString();
    }

    public static boolean isNumber(String num) {
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String removeChar(String s, char c) {
        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                ret += s.charAt(i);
            }
        }
        return ret;
    }

    public static String trimStringForNumber(String s) {
        /*if (s.contains("-")) {
            s = removeChar(s, '-');
        }
        if (s.contains(".")) {
            s = removeChar(s, '.');
        }
        if (s.contains(" ")) {
            s = removeChar(s, ' ');
        }*/
        if (s == null || s.trim().isEmpty()) {
            return "";
        }
        s = s.replaceAll("[^\\p{N}]","");
        s = s.trim();
        return s;
    }

    public static String numberFormatChange(String s) {
        String ret = "";
        if (s == null || s.trim().isEmpty()) {
            return ret;
        }
        if (s.length() == 10) {
            ret = "1-";
            ret += s.substring(0, 3) + "-";
            ret += s.substring(3, 6) + "-";
            ret += s.substring(6);
        } else if (s.length() == 11 && s.charAt(0) == '1') {
            ret += s.substring(0, 1) + "-";
            ret += s.substring(1, 4) + "-";
            ret += s.substring(4, 7) + "-";
            ret += s.substring(7);
        }
        return ret;
    }
}