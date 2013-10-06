package com.common;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by IntelliJ IDEA.
 * User: chokkh01
 * Date: Dec 23, 2008
 * Time: 1:09:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {

    public static final String NEWLINE = System.getProperty("line.separator");

    /**
     * utility function to construct a list of ids
     *
     * @param ids
     * @return
     */
    public static String idsToInString(int[] ids) {
        if (ids.length == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        sb.append(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            sb.append(',');
            sb.append(ids[i]);
        }
        return sb.toString();
    }

    /**
     * utility function to construct a list of ids
     *
     * @param ids
     * @return
     */
    public static String idsToInString(Integer[] ids) {
        if (ids.length == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        sb.append(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            sb.append(',');
            sb.append(ids[i]);
        }
        return sb.toString();
    }

    /**
     * utility function to construct a comma seperated String from a String Array
     *
     * @param strArr
     * @return
     */
    public static String arrayToCommaSeperatedString(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(',');
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    /**
     * utility function to construct a comma seperated String from a String Array
     *
     * @param intArr
     * @return
     */
    public static String arrayToCommaSeperatedString(Integer[] intArr) {
        if (intArr == null || intArr.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(intArr[0]);
        for (int i = 1; i < intArr.length; i++) {
            sb.append(',');
            sb.append(intArr[i].intValue());
        }
        return sb.toString();
    }

    /**
     * To validate the passed in string to see if it is a valid set of integer values.
     * If so, we generate the in string or else we throw an error.
     *
     * @param strIds
     * @return
     */
    public static String toValidatedInString(String strIds) {
        if (strIds.length() == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        StringTokenizer commaSepStrTokens = new StringTokenizer(strIds, ", ");
        while (commaSepStrTokens.hasMoreTokens()) {
            String strValue = commaSepStrTokens.nextToken().trim();
            if (strValue.equals("")) {
                continue;
            } else {
                int intValue = Integer.parseInt(strValue);
                sb.append(intValue + ",");
            }
        }
        String retVal = "";
        if (sb.length() > 0) {
            retVal = sb.substring(0, (sb.length() - 1));
        }
        return retVal;
    }


    public static String getNewDelimitedEmailStr(String emailStr, String regExStr, String newDelim) {
        String newEmailStr = null;
        if (emailStr != null && regExStr != null && newDelim != null) {
            String[] emailAddArr = emailStr.split(regExStr);
            for (String emailAddress : emailAddArr) {
                emailAddress = emailAddress.trim();
                if (emailAddress.length() > 0) {
                    if (newEmailStr == null) {
                        newEmailStr = emailAddress;
                    } else {
                        newEmailStr += newDelim + emailAddress;
                    }
                }
            }
        }
        return newEmailStr;
    }

    public static String toPrettyXml(String xml) {
        try {
            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "2");
            StreamResult result = new StreamResult(new StringWriter());
            StreamSource source = new StreamSource(new StringReader(xml));
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception e) {
            //Logging.logError("Failed to parse the XML.", e);
            return xml;
        }
    }

    public static boolean equal(String a, String b) {
        if ((a == null) != (b == null)) return false;
        if (a == null) return true; // a is null, b is also null
        return a.equals(b); // a and b are non-null
    }

    public static String getNewLines(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    /**
     * Add leading zeros to return a String number with exact totalDigits digit
     *
     * @param number
     * @param totalDigits
     * @return
     */
    public static String addLeadZeros(int number, int totalDigits) {
        return String.format("%1$#"+totalDigits+"s", number).replaceAll(" ", "0");
    }

    /**
     * Return true of the Str is null or contain empty string (or spaces)
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmptyOrBlank(String str) {
        return str == null || str.trim().equals("");
    }
}


