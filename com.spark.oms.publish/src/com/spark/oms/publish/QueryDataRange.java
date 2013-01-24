package com.spark.oms.publish;

import com.spark.oms.publish.common.DateUtils;

//����ɸѡ
public enum QueryDataRange {

	//���£����£����������꣬ȫ��
	MONTH(DateUtils.getCurrentMonthFirstDayTime(),"����"),
	LASTMONTH(DateUtils.getLastMonthFirstDayFirstTime(),"����"),
	QUARTER(DateUtils.getLastQuartFirstDayFirstTime(),"����"),
	YEAR(DateUtils.getYearFirstDayFirstTime(),"����"),
	ALL(0,"ȫ��");
	
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
		throw new IllegalArgumentException(code + "����һ����ȷ������ɸѡö��");
	}
}