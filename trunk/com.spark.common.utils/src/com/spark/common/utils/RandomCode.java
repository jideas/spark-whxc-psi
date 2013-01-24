package com.spark.common.utils;

import java.util.Random;

public class RandomCode {

	/**
	 * Éú³ÉËæ»úÃÜÂë
	 * 
	 * @return String
	 */
	public final static String newCode(int length){
		Random random = new Random();
		char[] chars =
		        new char[] {'2', '3', '4', '5', '6', '7', '8', '9',
		                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
		                'K', 'M', 'P', 'Q', 'R', 'S', 'T', 'U',
		                'V', 'W', 'X', 'Y', 'Z'};
		char[] str = new char[length];
		for(int i = 0; i < str.length; i++){
			str[i] = chars[random.nextInt(30)];
		}
		return new String(str);

	}
	
}
