/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.finance.receipt.intf.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       向中秋        
 * ============================================================*/

package com.spark.psi.account.intf.entity.receipt;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>零售收款实体类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 向中秋
 * @version 2011-11-10
 */

public class RetailReceipt{
	private GUID RECID; //RECID
	private GUID tenantsGuid;           //租户编号
	private GUID saleEmpGuid;           //销售员工编号
	private String saleEmpName;         //销售员工名称
	private long receiptDate;           //交款期间
	private double shouldMoney;         //应交现金
	private int shouldCardCount;        //应交刷卡底单（数量）
	private double shouldCardMoney;     //应交刷卡底单（金额）
	private GUID deptGuid;              //部门编号
	private GUID retailGuid;            //零售单据编号
	
	/**
     * @return the rECID
     */
    public GUID getRECID(){
    	return RECID;
    }
	/**
     * @param rECID the rECID to set
     */
    public void setRECID(GUID rECID){
    	RECID = rECID;
    }
	/**
     *  租户编号
     */
    public GUID getTenantsGuid(){
    	return tenantsGuid;
    }
	/**
     *  租户编号
     */
    public void setTenantsGuid(GUID tenantsGuid){
    	this.tenantsGuid = tenantsGuid;
    }
	/**
     * 销售员工编号
     */
    public GUID getSaleEmpGuid(){
    	return saleEmpGuid;
    }
	/**
     * 销售员工编号
     */
    public void setSaleEmpGuid(GUID saleEmpGuid){
    	this.saleEmpGuid = saleEmpGuid;
    }
	/**
     * 销售员工名称
     */
    public String getSaleEmpName(){
    	return saleEmpName;
    }
	/**
     * 销售员工名称
     */
    public void setSaleEmpName(String saleEmpName){
    	this.saleEmpName = saleEmpName;
    }
	/**
     * 交款期间
     */
    public long getReceiptDate(){
    	return receiptDate;
    }
	/**
     * 交款期间
     */
    public void setReceiptDate(long receiptDate){
    	this.receiptDate = receiptDate;
    }
	/**
     * 应交现金
     */
    public double getShouldMoney(){
    	return shouldMoney;
    }
	/**
     * 应交现金
     */
    public void setShouldMoney(double shouldMoney){
    	this.shouldMoney = shouldMoney;
    }
	/**
     *应交刷卡底单（数量）
     */
    public int getShouldCardCount(){
    	return shouldCardCount;
    }
	/**
     *应交刷卡底单（数量）
     */
    public void setShouldCardCount(int shouldCardCount){
    	this.shouldCardCount = shouldCardCount;
    }
	/**
     * 应交刷卡底单（金额）
     */
    public double getShouldCardMoney(){
    	return shouldCardMoney;
    }
	/**
     *应交刷卡底单（金额）
     */
    public void setShouldCardMoney(double shouldCardMoney){
    	this.shouldCardMoney = shouldCardMoney;
    }
	/**
     * 部门编号
     */
    public GUID getDeptGuid(){
    	return deptGuid;
    }
	/**
     * 部门编号
     */
    public void setDeptGuid(GUID deptGuid){
    	this.deptGuid = deptGuid;
    }
	/**
     * 零售单据编号
     */
    public GUID getRetailGuid(){
    	return retailGuid;
    }
	/**
     * 零售单据编号
     */
    public void setRetailGuid(GUID retailGuid){
    	this.retailGuid = retailGuid;
    }
	

}
