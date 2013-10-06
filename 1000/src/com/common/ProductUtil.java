package com.common;

/**
 * Utility class providing various public static
 * methods and fields for dealing with products
 * and product checks.
 */
public class ProductUtil {
    /*
      The constants defined here must represent
      UDAC DESCs not UDAC NAMEs. The methods
      testing the UDACs also expect UDAC DESCs,
      not UDAC NAMEs.
    */
    public static final String WRB_UDAC_DESC = "WRB";
    public static final String SEOA_UDAC_DESC = "SEOA";
    public static final String SEOW_UDAC_DESC = "SEOW";
    public static final String WRD_UDAC_DESC = "WRD";
    public static final String MSS_UDAC_DESC = "MSS-R";
    public static final String MSSD_UDAC_DESC = "MSSD-R";

    public static boolean isSeoEliteUdac(String udacDesc){
        if (udacDesc == null) return false;
        return
            SEOA_UDAC_DESC.equalsIgnoreCase(udacDesc) ||
            SEOW_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isSEOA(String udacDesc){
        if (udacDesc == null) return false;
        return SEOA_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isSEOW(String udacDesc){
        if (udacDesc == null) return false;
        return SEOW_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isMSSTypeUdac(String udacDesc){
        if (udacDesc == null) return false;
        return
            MSS_UDAC_DESC.equalsIgnoreCase(udacDesc) ||
            MSSD_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isMSSUdac(String udacDesc) {
        if (udacDesc == null) return false;
        return MSS_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isMSSDUdac(String udacDesc){
        if (udacDesc == null) return false;
        return MSSD_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isWRBUdac(String udacDesc){
        if (udacDesc == null) return false;
        return WRB_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

    public static boolean isWRDUdac(String udacDesc){
        if (udacDesc == null) return false;
        return WRD_UDAC_DESC.equalsIgnoreCase(udacDesc);
    }

}


