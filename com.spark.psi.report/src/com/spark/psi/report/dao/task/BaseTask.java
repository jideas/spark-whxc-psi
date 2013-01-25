/**
 * 
 */
package com.spark.psi.report.dao.task;

import java.util.Date;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.utils.ReportDateUtils;

/**
 *
 */
public abstract class BaseTask extends SimpleTask {

	private int yearNo;
	private int monthNo;
	private int quarter;
	private int dateNo;
	private GUID tenantId;
	private int count;
	public void setDateNo(Date date){
		setDateNo(ReportDateUtils.getDateNo(date));
		setMonthNo(ReportDateUtils.getMonthNo(date));
		setYearNo(ReportDateUtils.getYearNo(date));
		setQuarter(ReportDateUtils.getQuarter(date));
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the yearNo
	 */
	public int getYearNo() {
		return yearNo;
	}

	/**
	 * @param yearNo
	 *            the yearNo to set
	 */
	private void setYearNo(int yearNo) {
		this.yearNo = yearNo;
	}

	/**
	 * @return the monthNo
	 */
	public int getMonthNo() {
		return monthNo;
	}

	/**
	 * @param monthNo
	 *            the monthNo to set
	 */
	private void setMonthNo(int monthNo) {
		this.monthNo = monthNo;
	}

	/**
	 * @return the quarter
	 */
	public int getQuarter() {
		return quarter;
	}

	/**
	 * @param quarter
	 *            the quarter to set
	 */
	private void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	/**
	 * @return the dateNo
	 */
	public int getDateNo() {
		return dateNo;
	}

	/**
	 * @param dateNo
	 *            the dateNo to set
	 */
	private void setDateNo(int dateNo) {
		this.dateNo = dateNo;
	}

	/**
	 * @return the tenantId
	 */
	public GUID getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setTenantId(GUID tenantId) {
		this.tenantId = tenantId;
	}
}
