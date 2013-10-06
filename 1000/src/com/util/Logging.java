package com.util;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.ThrowableInformation;

public class Logging {
    private static org.apache.log4j.Logger betLog = Logger.getLogger("BET");

    private static org.apache.log4j.Logger transactionLog = Logger.getLogger("transaction");
    private static org.apache.log4j.Logger infoLog = Logger.getLogger("webreach");
    private static org.apache.log4j.Logger errorLog = Logger.getLogger("error");
    private static org.apache.log4j.Logger performanceLog = Logger.getLogger("performance");
    private static org.apache.log4j.Logger udacActivateLog = Logger.getLogger("udacactivate");
    private static org.apache.log4j.Logger searchVolumeLog = Logger.getLogger("searchVolume");
    private static org.apache.log4j.Logger excelsheetLog = Logger.getLogger("excelsheet");

    private static org.apache.log4j.Logger google_rpt = Logger.getLogger("google_all_rpt");

    private static org.apache.log4j.Logger msn_cmprpt = Logger.getLogger("msn_cmprpt");
    private static org.apache.log4j.Logger google_cmprpt = Logger.getLogger("google_cmprpt");
    private static org.apache.log4j.Logger adhere_cmprpt = Logger.getLogger("adhere_cmprpt");
    private static org.apache.log4j.Logger ipromote_cmprpt = Logger.getLogger("ipromote_cmprpt");
    private static org.apache.log4j.Logger citygrid_cmprpt = Logger.getLogger("citygrid_cmprpt");

    private static org.apache.log4j.Logger msn_kwdrpt = Logger.getLogger("msn_kwdrpt");
    private static org.apache.log4j.Logger google_kwdrpt = Logger.getLogger("google_kwdrpt");
    private static org.apache.log4j.Logger adhere_kwdrpt = Logger.getLogger("adhere_kwdrpt");
    private static org.apache.log4j.Logger ipromote_kwdrpt = Logger.getLogger("ipromote_kwdrpt");
    private static org.apache.log4j.Logger citygrid_usractrpt = Logger.getLogger("citygrid_usractrpt");

    private static org.apache.log4j.Logger adhere_conv_rpt = Logger.getLogger("adhere_conv_rpt");

    private static org.apache.log4j.Logger msn_sqrpt = Logger.getLogger("msn_sqrpt");
    private static org.apache.log4j.Logger google_sqrpt = Logger.getLogger("google_sqrpt");

    private static org.apache.log4j.Logger schrpt = Logger.getLogger("rpt_usr_schedule");
    private static org.apache.log4j.Logger dldrpt = Logger.getLogger("rpt_usr_download");

    private static org.apache.log4j.Logger dbrpt = Logger.getLogger("dbrpt");
    private static org.apache.log4j.Logger unprpt = Logger.getLogger("unprpt");

    private static org.apache.log4j.Logger reportCenterErrorLog = Logger.getLogger("reportCenterError");
    private static org.apache.log4j.Logger reportCenterInfoLog = Logger.getLogger("reportCenterInfo");

    private static org.apache.log4j.Logger estaraLog = Logger.getLogger("estara");
    private static org.apache.log4j.Logger dbSummaryLog = Logger.getLogger("dbsummary");
    private static org.apache.log4j.Logger voicestarLog = Logger.getLogger("voicestar");

    public static final String NEW_LINE = System.getProperty("line.separator");
    
    public static void logBET(String desc) {
    	betLog.info(desc);
    }
    
    /**
     * Log all types of interaction with search engines. Transactions with Database, etc.
     *
     * @param desc
     */
    public static void logTransaction(String desc) {
        transactionLog.info(desc);
    }

    /**
     * Log all types of udac change activation
     *
     * @param desc
     */
    public static void logUdacActivation(String desc) {
        udacActivateLog.info(desc);
    }

    /**
     * Log all severe error that cannot be resolved by server.
     *
     * @param desc
     * @param ex
     */
    public static void logError(String desc, Throwable ex) {
        errorLog.error(desc, ex);
    }


    /**
     * Log important server information.
     *
     * @param desc
     */
    public static void logInfo(String desc) {
        infoLog.info(desc);
    }

    /**
     * Log important error message that can be handled by server or not severe.
     *
     * @param desc
     * @param ex
     */
    public static void logInfo(String desc, Throwable ex) {
        infoLog.info(desc, ex);
    }

    /**
     * Log performance message.
     *
     * @param desc
     */
    public static void logPerformance(String desc) {
        performanceLog.info(desc);
    }

    /**
     * Log important excelsheet message.
     *
     * @param desc
     * @param ex
     */
    public static void logExcelSheetInfo(String desc, Throwable ex) {
        excelsheetLog.info(desc, ex);
    }

    public static void logExcelSheetInfo(String desc) {
        excelsheetLog.info(desc);
    }

    public static String getStringData(Throwable th) {
        ThrowableInformation thInfo = new ThrowableInformation(th);
        String[] arr = thInfo.getThrowableStrRep();
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(String.format("%s%n", s));
        }
        return sb.toString();
    }


    public static void logGoogleRpt(String msg) {
        google_rpt.info(msg);
    }

    public static void logGoogleRpt(String desc, Throwable th) {
        google_rpt.info(desc, th);
    }

    public static void logUsrRptSchedule(String desc) {
        schrpt.info(desc);
    }

    public static void logUsrRptSchedule(String desc, Throwable th) {
        schrpt.info(desc, th);
    }

    public static void logUsrRptDownload(String desc) {
        dldrpt.info(desc);
    }

    public static void logUsrRptDownload(String desc, Throwable th) {
        dldrpt.info(desc, th);
    }

    public static void logSearchVol(String desc, Throwable th) {
        searchVolumeLog.info(desc, th);
    }

    public static void logDbRpt(String desc, Throwable th) {
        dbrpt.info(desc, th);
    }

    public static void logDbRpt(String desc) {
        dbrpt.info(desc);
    }
    
    public static void logUnpRpt(String desc, Throwable th) {
        unprpt.info(desc, th);
    }

    public static void logUnpRpt(String desc) {
    	unprpt.info(desc);
    }

    public static void logReportcenterError(String desc, Throwable ex) {
        reportCenterErrorLog.error(desc, ex);
    }

    public static void logReportcenterInfo(String desc) {
        reportCenterInfoLog.info(desc);
    }

    public static void logEStara(String msg) {
        estaraLog.info(msg);
    }

    public static void logEStara(String desc, Throwable ex) {
        estaraLog.info(desc, ex);
    }

    public static void logVoiceStar(String msg) {
        voicestarLog.info(msg);
    }

    public static void logVoiceStar(String desc, Throwable ex) {
        voicestarLog.info(desc, ex);
    }

    public static void logDBSummary(String msg) {
        dbSummaryLog.info(msg);
    }

    public static void logDBSummary(String desc, Throwable ex) {
        dbSummaryLog.info(desc, ex);
    }

    public static void logTransaction(Priority p, String desc, Throwable t){
        transactionLog.log(p, desc, t);
    }
}


