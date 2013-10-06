package com.util;

import java.util.StringTokenizer;

public class XMLParamUtil {
    public static void appendParamValues(StringBuffer params,
            String tagValues, String tagName) {
        appendParamValues(params, tagValues, tagName, null);
    }

    public static void appendParamValues(StringBuffer params,
            String tagValues, String tagName, String delim) {
        if (tagValues != null) {
            if (delim == null || delim.trim().length() == 0) {
                delim = ",";
            }
            StringTokenizer st = new StringTokenizer(tagValues.trim(), delim);
            while (st.hasMoreTokens()) {
                params.append("<"
                        + tagName
                        + " id=\""
                        + encodeStringForXml(st.nextToken().trim())
                        + "\"></" + tagName + ">");
            }
        }
    }

    /**
     * encode the string because of the xml convertion.
     *
     * @param str
     * @return
     */
    public static String encodeStringForXml(String str) {
        if (str == null)
            return str;
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("&", "&amp;");
        return str;
    }
}