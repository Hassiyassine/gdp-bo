package com.auth.detailes.utilities;

import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author 	: Yassine Hassi | yassine.hassi.ismail@gmail.com
 */

public class StringGeneratorCode {


	public static String convertToCode(String code) {
		String strTemp = Normalizer.normalize(code.replaceAll(" ", "").
												   replaceAll("'", "").
												   replaceAll("-", "").
												   replaceAll("_", "").
												   toUpperCase(),
												   Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(strTemp).replaceAll("");
	}
	
	
	public static Date getDateToTimestamp() {
		Date date = new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		return timestamp;
	}

	public static Long getDateLong(){
		return  getDateToTimestamp().getTime();
	}


	public static String  generateRandomString(int maxChar){
		return String.format("%s%s",new Date().getTime(),getRandomNum(maxChar,999999));
	}

	private static int getRandomNum(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

}
