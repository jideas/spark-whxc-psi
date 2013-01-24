package com.spark.oms.publish;

import com.spark.oms.publish.common.DateUtils;

//日期筛选
public enum QueryDataRange {

	//本月，上月，本季，本年，全部
	MONTH(DateUtils.getCurrentMonthFirstDayTime(),"本月"),
	LASTMONTH(DateUtils.getLastMonthFirstDayFirstTime(),"上月"),
	QUARTER(DateUtils.getLastQuartFirstDayFirstTime(),"本季"),
	YEAR(DateUtils.getYearFirstDayFirstTime(),"本年"),
	ALL(0,"全部");
	
	private long code;
	private String name;
	
	public long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private QueryDataRange(long code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static QueryDataRange getQueryDataRangeByCode(long code) {
		for (QueryDataRange dataRange : QueryDataRange.values()) {
			if (dataRange.getCode()==code) {
				return dataRange;
			}
		}
		throw new IllegalArgumentException(code + "不是一个正确的日期筛选枚举");
	}
}