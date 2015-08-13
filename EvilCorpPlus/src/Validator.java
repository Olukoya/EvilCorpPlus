import java.util.*;
import java.util.regex.*;

public class Validator {


	public static boolean checkName(String s) {
		Pattern p = Pattern.compile("[\\w ]+");
		Matcher m = p.matcher(s);

		if (m.matches())
			return true;
		return false;
	}

	public static boolean checkTransactionType(String s) {
		switch(s){
		case("debit"):return true;
		case("check"):return true;
		case("deposit"):return true;
		case("withdrawal"):return true;
		case("-1"): return true;
		default:return false;
		}
	}
	
	public static boolean checkAccount(String s){
		Pattern p = Pattern.compile("\\d{6}");
		Matcher m = p.matcher(s);
		if(m.matches())
			return true;
		return false;
	}
	
	public static boolean checkTransactionAmount(String s) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(s);

		if (m.matches())
			return true;
		return false;
	}

	public static boolean checkDate(String s) {
		Pattern p = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
		Matcher m = p.matcher(s);

		if (m.matches())
			return true;
		return false;
	}
}