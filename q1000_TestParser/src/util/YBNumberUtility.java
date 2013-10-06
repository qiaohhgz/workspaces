package util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class YBNumberUtility {

	public static Double percentToDouble(String value) {
		NumberFormat nf = NumberFormat.getPercentInstance(Locale.US);
		try {
			return nf.parse(value).doubleValue();
		} catch (ParseException e) {
			return 0.00;
		}
	}

	public static Integer parseInteger(String value) {
		NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);

		try {
			return nf.parse(value).intValue();
		} catch (ParseException e) {
			return 0;
		}
	}
}
