package backend.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.entity.Category;
import backend.entity.Users;
import backend.model._Category;
import backend.model._Users;

//https://stackoverflow.com/questions/8204680/java-regex-email
public class Validation {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_FOR_SQL_INJECTION = Pattern.compile("\b(ALTER|CREATE|DELETE|WHERE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE|UNION( +ALL){0,1})\b",Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_ONLY_FOR_A_Z_WITH_WHITE_SPACE = Pattern.compile("^[a-zA-Z\\s]*$",Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_CARD_NUMBER = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$",Pattern.CASE_INSENSITIVE);
 
	public static boolean IsEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static boolean IsEmailTaken(String emailStr) {
		try {
			_Users _user = new _Users();
			_Users[] user = (_Users[]) _user.where("email", "=", emailStr).first();

			return !(user.length > 0);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean IsEmaiTaken(String emailStr, int userId) {
		try {
			_Users _user = new _Users();
			Users[] user = (Users[]) _user.where("email", "=", emailStr).and()
					.where("id", "!=", Integer.toString(userId)).first();

			return !(user.length > 0);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean IsSafe(String str) {
		Matcher matcher = VALID_FOR_SQL_INJECTION.matcher(str);
		return matcher.find();
	}
	
	public static boolean IsA_Z(String str) {
		Matcher matcher = VALID_ONLY_FOR_A_Z_WITH_WHITE_SPACE.matcher(str);
		return matcher.find();
	}

	public static boolean IsValidCardNumber(String str) {
		//https://stackoverflow.com/questions/9315647/regex-credit-card-number-tests
		Matcher matcher = VALID_CARD_NUMBER.matcher(str);
		return matcher.find();
	}

}
