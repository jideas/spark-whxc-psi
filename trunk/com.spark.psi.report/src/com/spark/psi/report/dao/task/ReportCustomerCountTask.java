/**
 * 
 */
package com.spark.psi.report.dao.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class ReportCustomerCountTask extends SimpleTask{

	private GUID tenantId;//�⻧���
	private String logType;//��¼���ͣ�����,ת����
	private GUID deptId;//����GUID
	private GUID employeeId;//Ա�����
	private GUID customerId;//�ͻ�id
	private int dateNo;//�պ�
	private int monthNo;//�ں�
	private int quarter;//����
	private int yearNo;//���
	private long currDate;//����ʱ��
	private int beforeCount;

	/**
     * @return the beforeCount
     */
    public int getBeforeCount(){
    	return beforeCount;
    }

	/**
     * @param beforeCount the beforeCount to set
     */
    public void setBeforeCount(int beforeCount){
    	this.beforeCount = beforeCount;
    }

	private int count;
	
	/**
     * @return the count
     */
    public int getCount(){
    	return count;
    }

	/**
     * @param count the count to set
     */
    public void setCount(int count){
    	this.count = count;
    }

	/**
	 * @return the tenantId
	 */
	public GUID getTenantId(){
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(GUID tenantId){
		this.tenantId = tenantId;
	}

	/**
	 * @return the logType
	 */
	public String getLogType(){
		return logType;
	}

	/**
	 * @param logType the logType to set
	 */
	public void setLogType(String logType){
		this.logType = logType;
	}

	/**
	 * @return the deptId
	 */
	public GUID getDeptId(){
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(GUID deptId){
		this.deptId = deptId;
	}

	/**
	 * @return the employeeId
	 */
	public GUID getEmployeeId(){
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(GUID employeeId){
		this.employeeId = employeeId;
	}

	/**
	 * @return the customerId
	 */
	public GUID getCustomerId(){
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(GUID customerId){
		this.customerId = customerId;
	}

	/**
	 * @return the dateNo
	 */
	public int getDateNo(){
		return dateNo;
	}

	/**
	 * @param dateNo the dateNo to set
	 */
	public void setDateNo(int dateNo){
		this.dateNo = dateNo;
	}

	/**
	 * @return the monthNo
	 */
	public int getMonthNo(){
		return monthNo;
	}

	/**
	 * @param monthNo the monthNo to set
	 */
	public void setMonthNo(int monthNo){
		this.monthNo = monthNo;
	}

	/**
	 * @return the quarter
	 */
	public int getQuarter(){
		return quarter;
	}

	/**
	 * @param quarter the quarter to set
	 */
	public void setQuarter(int quarter){
		this.quarter = quarter;
	}

	/**
	 * @return the yearNo
	 */
	public int getYearNo(){
		return yearNo;
	}

	/**
	 * @param yearNo the yearNo to set
	 */
	public void setYearNo(int yearNo){
		this.yearNo = yearNo;
	}

	/**
	 * @return the currDate
	 */
	public long getCurrDate(){
		return currDate;
	}

	/**
	 * @param currDate the currDate to set
	 */
	public void setCurrDate(long currDate){
		this.currDate = currDate;
	}

}
