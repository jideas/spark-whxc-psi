package com.spark.psi.query.browser;

import java.text.SimpleDateFormat;

public class QueryDataUtil {
	/**
	 * ����ձ��
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String s = sdf.format(System.currentTimeMillis());
		return Integer.valueOf(s);
	}
}
