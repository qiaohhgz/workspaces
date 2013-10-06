package com.common;

import java.text.DecimalFormat;

/**
 * Utility class providing various static methods
 * for working with numbers and also with micros
 * (the SEs currency unit).
 */
public class NumberUtil {

    public static final double EPS = 0.001;

    public static double roundTo2Decimal(double input) {
        return Math.round(input * 100) / 100.0;
    }

    public static double roundTo6Decimal(double input) {
        return Math.round(input * 1000000) / 1000000.0;
    }

    public static float roundTo2Decimal(float input) {
        return Math.round(input * 100) / 100.0f;
    }

    /**
     * Rounds the micro to the cent level
     * ex. 4,050,000 ($4.05) making
     * sure that last 4 digit are zero
     */
    public static long roundMicroToCents(long micro) {
        micro = Math.round(micro / 10000d) * 10000;
        return micro;
    }

    public static boolean smaller(double a, double b){
        return a-b <= -EPS;
    }

    public static boolean bigger(double a, double b){
        return a-b >= EPS;
    }

    public static boolean equal(double a, double b){
        return Math.abs(a-b) < EPS;
    }

    public static boolean different(double a, double b){
        return Math.abs(a-b) >= EPS;
    }

    public static String format(double x){
        DecimalFormat d = new DecimalFormat("#.##");
        return d.format(x);
    }

}


