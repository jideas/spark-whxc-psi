/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.finance.receipt.intf.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ������        
 * ============================================================*/

package com.spark.psi.account.intf.entity.receipt;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����տ�ʵ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2011-11-10
 */

public class RetailReceipt{
	private GUID RECID; //RECID
	private GUID tenantsGuid;           //�⻧���
	private GUID saleEmpGuid;           //����Ա�����
	private String saleEmpName;         //����Ա������
	private long receiptDate;           //�����ڼ�
	private double shouldMoney;         //Ӧ���ֽ�
	private int shouldCardCount;        //Ӧ��ˢ���׵���������
	private double shouldCardMoney;     //Ӧ��ˢ���׵�����
	private GUID deptGuid;              //���ű��
	private GUID retailGuid;            //���۵��ݱ��
	
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
     *  �⻧���
     */
    public GUID getTenantsGuid(){
    	return tenantsGuid;
    }
	/**
     *  �⻧���
     */
    public void setTenantsGuid(GUID tenantsGuid){
    	this.tenantsGuid = tenantsGuid;
    }
	/**
     * ����Ա�����
     */
    public GUID getSaleEmpGuid(){
    	return saleEmpGuid;
    }
	/**
     * ����Ա�����
     */
    public void setSaleEmpGuid(GUID saleEmpGuid){
    	this.saleEmpGuid = saleEmpGuid;
    }
	/**
     * ����Ա������
     */
    public String getSaleEmpName(){
    	return saleEmpName;
    }
	/**
     * ����Ա������
     */
    public void setSaleEmpName(String saleEmpName){
    	this.saleEmpName = saleEmpName;
    }
	/**
     * �����ڼ�
     */
    public long getReceiptDate(){
    	return receiptDate;
    }
	/**
     * �����ڼ�
     */
    public void setReceiptDate(long receiptDate){
    	this.receiptDate = receiptDate;
    }
	/**
     * Ӧ���ֽ�
     */
    public double getShouldMoney(){
    	return shouldMoney;
    }
	/**
     * Ӧ���ֽ�
     */
    public void setShouldMoney(double shouldMoney){
    	this.shouldMoney = shouldMoney;
    }
	/**
     *Ӧ��ˢ���׵���������
     */
    public int getShouldCardCount(){
    	return shouldCardCount;
    }
	/**
     *Ӧ��ˢ���׵���������
     */
    public void setShouldCardCount(int shouldCardCount){
    	this.shouldCardCount = shouldCardCount;
    }
	/**
     * Ӧ��ˢ���׵�����
     */
    public double getShouldCardMoney(){
    	return shouldCardMoney;
    }
	/**
     *Ӧ��ˢ���׵�����
     */
    public void setShouldCardMoney(double shouldCardMoney){
    	this.shouldCardMoney = shouldCardMoney;
    }
	/**
     * ���ű��
     */
    public GUID getDeptGuid(){
    	return deptGuid;
    }
	/**
     * ���ű��
     */
    public void setDeptGuid(GUID deptGuid){
    	this.deptGuid = deptGuid;
    }
	/**
     * ���۵��ݱ��
     */
    public GUID getRetailGuid(){
    	return retailGuid;
    }
	/**
     * ���۵��ݱ��
     */
    public void setRetailGuid(GUID retailGuid){
    	this.retailGuid = retailGuid;
    }
	

}
