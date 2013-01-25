/**
 * 
 */
package com.spark.psi.report.entity;

/**
 *
 */
public class MonitorTargetEntityTemp{
	private double salesTarget = 0;
	private double receiptTarget = 0;
	private double totalSalesTarget = 0;
	private double totalReceiptTarget = 0;
	/**
     * @return the salesTarget
     */
    public double getSalesTarget(){
    	return salesTarget;
    }
	/**
     * @param salesTarget the salesTarget to set
     */
    public void setSalesTarget(double salesTarget){
    	this.salesTarget = salesTarget;
    }
	/**
     * @return the receiptTarget
     */
    public double getReceiptTarget(){
    	return receiptTarget;
    }
	/**
     * @param receiptTarget the receiptTarget to set
     */
    public void setReceiptTarget(double receiptTarget){
    	this.receiptTarget = receiptTarget;
    }
	/**
     * @return the totalSalesTarget
     */
    public double getTotalSalesTarget(){
    	return totalSalesTarget;
    }
	/**
     * @param totalSalesTarget the totalSalesTarget to set
     */
    public void setTotalSalesTarget(double totalSalesTarget){
    	this.totalSalesTarget = totalSalesTarget;
    }
	/**
     * @return the totalReceiptTarget
     */
    public double getTotalReceiptTarget(){
    	return totalReceiptTarget;
    }
	/**
     * @param totalReceiptTarget the totalReceiptTarget to set
     */
    public void setTotalReceiptTarget(double totalReceiptTarget){
    	this.totalReceiptTarget = totalReceiptTarget;
    }
}
