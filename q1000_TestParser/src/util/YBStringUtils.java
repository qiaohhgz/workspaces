
package util;


import org.apache.commons.lang.StringEscapeUtils;
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.util.ParserException;


/**
 *
 * @author sh
 */

public class YBStringUtils {


	
    public static String truncateWithSpace(final String orig, final int maxLen) {

        return truncateWithSpace (orig, " ", maxLen);

    }


    public static String truncateWithSpace(final String orig, final String demi, final int maxLen){

        String retVal = orig;

        if (orig != null && orig.length () > maxLen) {

            retVal = retVal.substring (0, maxLen);

            int index = retVal.lastIndexOf (demi);

            if (index != -1) {

                retVal = retVal.substring (0, index);

            }

        }


        return retVal;

    }

    public static String removeAllNonNumChars(final String phone) {

        String result = "";

        if (phone != null) {

             result = phone.replaceAll("[^0-9]*", "");

        }

        return result;

    }


    public static String convertPhoneNumberToStandard(final String phone){

        if(phone == null) {

            throw new IllegalArgumentException("Expected non null phone number");

        }

        String nakedPhone = removeAllNonNumChars(phone);

        if(nakedPhone.length() != 10) {

            throw new IllegalArgumentException("Expected 10 number phone string.  Instead got = " + phone);

        }

        String result = "(" + nakedPhone.substring(0,3) + ")" +

                nakedPhone.substring(3,6) + "-" +

                nakedPhone.substring(6,10);

        return result;

    }


    public static String cleanUpContent(String text) {

		text = text.trim().replaceAll("[ ]+", " ");

		text = text.replaceAll("(\\s)+", "$1");

		return unescapeHtml(text);

	}
    
    public static void main(String[] args) throws ParserException {
    	System.out.println(convertPhoneNumberToStandard("1324567891"));
		System.out.println(toPlainText("<html>Hello World</html>"));
		System.out.println(StringEscapeUtils.escapeHtml("<html>Hello World</html>"));
		System.out.println(unescapeHtml("&lt;html&gt;&lt;/html&gt;"));
		System.out.println(truncateWithSpace("123456789",5));
	}


	public static String unescapeHtml(String value) {

		return StringEscapeUtils.unescapeHtml(value);

	}


	public static String toPlainTextExcludeTag(String source, String tagName) throws ParserException {

		if(source != null) {

			String temp = source.replaceAll("<" + tagName + " .*>.*</" + tagName + ">", "");

			String text = toPlainText(temp);

			return cleanUpContent(text);

		}
		return "";

	}


	public static String toPlainText(String text) {

		if(text == null) {

			return text;

		}

		return text.replaceAll("<[^<>]*>", "").replaceAll("</[^<>]*>", "");

	}


	public static String getAttrFromNode(Node node, String attr) {

		if(node != null && node instanceof Tag){

			return ((Tag)node).getAttribute(attr);

		}

		return null;

	}

}