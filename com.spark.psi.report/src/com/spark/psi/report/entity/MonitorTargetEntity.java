/**
 * 
 */
package com.spark.psi.report.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class MonitorTargetEntity{

	private GUID objId;
	private int dateNo;
	private double salesAmount;
	private double receiptAmount;

	/**
	 * @return the objId
	 */
	public GUID getObjId(){
		return objId;
	}

	/**
	 * @param objId the objId to set
	 */
	public void setObjId(GUID objId){
		this.objId = objId;
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
	 * @return the salesAmount
	 */
	public double getSalesAmount(){
		return salesAmount;
	}

	/**
	 * @param salesAmount the salesAmount to set
	 */
	public void setSalesAmount(double salesAmount){
		this.salesAmount = salesAmount;
	}

	/**
	 * @return the receiptAmount
	 */
	public double getReceiptAmount(){
		return receiptAmount;
	}

	/**
	 * @param receiptAmount the receiptAmount to set
	 */
	public void setReceiptAmount(double receiptAmount){
		this.receiptAmount = receiptAmount;
	}
}
