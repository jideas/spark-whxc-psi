package com.spark.oms.publish;

import com.spark.oms.publish.common.DateUtils;

/**
 * �б�ҳǩ����ʽ�⻧/��ʧ�⻧
 * @author Administrator
 *
 */
public enum QueryTenantsDataRange {
	
	ALL(0,"ȫ��"),
	MONTH(DateUtils.getCurrentMonthFirstDayTime(),"��������"),
	QUARTER(DateUtils.getLastQuartFirstDayFirstTime(),"��������"),
	YEAR(DateUtils.getYearFirstDayFirstTime(),"��������");
	
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
		throw new IllegalArgumentException(code + "����һ����ȷ������ɸѡö��");
	}
}