/**
 * 
 */
package com.spark.psi.report.entity;

/**
 *
 */
public class SalesMonitorDailyEntity{

	private int day;
	private double salesAmount,checkoutAmount,receiptAmount;
	/**
     * @return the day
     */
    public int getDay(){
    	return day;
    }
	/**
     * @param day the day to set
     */
    public void setDay(int day){
    	this.day = day;
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
     * @return the checkoutAmount
     */
    public double getCheckoutAmount(){
    	return checkoutAmount;
    }
	/**
     * @param checkoutAmount the checkoutAmount to set
     */
    public void setCheckoutAmount(double checkoutAmount){
    	this.checkoutAmount = checkoutAmount;
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
