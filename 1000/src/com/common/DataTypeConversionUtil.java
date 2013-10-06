package com.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.util.Formatter;

public class DataTypeConversionUtil {
	public static Integer convertToInt(String s) {

		if (null != s && !"".equalsIgnoreCase(s.trim())) {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	public static int[] convertToIntArray(String s) {
		if (s == null)
			s = "";
		if (!"".equalsIgnoreCase(s)) {
			try {
				int[] int_arr = null;
				StringTokenizer stok = new StringTokenizer(s, ", ");
				List<Integer> intValues = new ArrayList<Integer>();
				String st = "";
				while (stok.hasMoreElements()) {
					st = stok.nextToken();
					try {
						intValues.add(Integer.parseInt(st));
					} catch (NumberFormatException ne) {
					}
				}
				if (intValues.size() > 0) {
					int i = 0;
					int_arr = new int[intValues.size()];
					for (int intValue : intValues) {
						int_arr[i] = intValue;
						i++;
					}
				}
				return int_arr;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static Integer[] convertToIntegerArray(String s) {
		if (s == null)
			s = "";
		if (!"".equalsIgnoreCase(s)) {
			try {
				Integer[] int_arr = null;
				StringTokenizer stok = new StringTokenizer(s, ", ");
				List<Integer> intValues = new ArrayList<Integer>();
				String st = "";
				while (stok.hasMoreElements()) {
					st = stok.nextToken();
					try {
						intValues.add(Integer.parseInt(st));
					} catch (NumberFormatException ne) {
					}
				}
				if (intValues.size() > 0) {
					int i = 0;
					int_arr = new Integer[intValues.size()];
					for (int intValue : intValues) {
						int_arr[i] = intValue;
						i++;
					}
				}
				return int_arr;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static Integer[] convertToIntegerArray(String s, String delim) {
		if (s == null)
			s = "";
		if (!"".equalsIgnoreCase(s)) {
			try {
				Integer[] int_arr = null;
				if (delim == null) {
					delim = ", ";
				}
				StringTokenizer stok = new StringTokenizer(s, delim);
				List<Integer> intValues = new ArrayList<Integer>();
				String st = "";
				while (stok.hasMoreElements()) {
					st = stok.nextToken();
					try {
						intValues.add(Integer.parseInt(st));
					} catch (NumberFormatException ne) {
					}
				}
				if (intValues.size() > 0) {
					int i = 0;
					int_arr = new Integer[intValues.size()];
					for (int intValue : intValues) {
						int_arr[i] = intValue;
						i++;
					}
				}
				return int_arr;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static Float convertToFloat(String s) {

		if (null != s && !"".equalsIgnoreCase(s.trim())) {
			try {
				return Float.parseFloat(s);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	public static Date convertToDate(String s, String format) {

		if (null != s && !"".equalsIgnoreCase(s.trim())) {
			try {
				return new SimpleDateFormat(format).parse(s);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	public static Date convertLongToDate(String s) {
		try {
			long mils = Long.parseLong(s);
			return new Date(mils);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static String formatFloatToMoney(float money) {
		return Formatter.format(Float.toString(money),
				Formatter.FieldType.CURRENCY, 2);
	}

	public static float formatFloatToMoneyNoSymbol(float money) {
		return Formatter.roundFloat(money, 2);
	}

	public static String formatFloatToMoneyNoRnd(float money) {
		return Formatter.formatCurrency(money, 2);
	}

	public static Float roundAndFormatFloat(String input, Integer numDecimal) {
		try {
			return Formatter.parseFloat(input, numDecimal);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDoubleToMoney(double money) {
		return Formatter.format(Double.toString(money),
				Formatter.FieldType.CURRENCY, 2);
	}

	public static String formatFloat(float input, Integer numDecimal) {
		return Formatter.format(input, numDecimal);
	}

	public static float roundFloat(float money, int decimals) {
		return Formatter.roundFloat(money, decimals);
	}

	public static String formatFloatToPercentNoRnd(float value, int decimals) {
		return Formatter.formatPercent(value, decimals);
	}

	public static String formatPercentage(float input, Integer numDecimal) {
		return formatFloat(input, numDecimal) + "%";
	}
}
