package demo9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
	
	public static void main(String[] args) {
		Pattern p = Pattern.compile(":");
		Matcher m = p.matcher("");
		for (String string : p.split("boo:and:foo",2)) {
			System.out.println(string);
		}
	}
}
