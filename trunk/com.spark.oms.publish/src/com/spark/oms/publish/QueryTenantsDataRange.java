package com.spark.oms.publish;

import com.spark.oms.publish.common.DateUtils;

/**
 * 列表页签—正式租户/流失租户
 * @author Administrator
 *
 */
public enum QueryTenantsDataRange {
	
	ALL(0,"全部"),
	MONTH(DateUtils.getCurrentMonthFirstDayTime(),"本月新增"),
	QUARTER(DateUtils.getLastQuartFirstDayFirstTime(),"本季新增"),
	YEAR(DateUtils.getYearFirstDayFirstTime(),"本年新增");
	
	private long code;
	private String name;
	
	public long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private QueryTenantsDataRange(long code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static QueryTenantsDataRange getQueryTenantsDataRangeByCode(long code) {
		for (QueryTenantsDataRange dataRange : QueryTenantsDataRange.values()) {
			if (dataRange.getCode()==code) {
				return dataRange;
			}
		}
		throw new IllegalArgumentException(code + "不是一个正确的日期筛选枚举");
	}
}