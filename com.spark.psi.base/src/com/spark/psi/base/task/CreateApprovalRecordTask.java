/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-27    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.base.ApprovalConfig.Mode;

/**
 * <p>����������¼</p>
 *


 *
 
 * @version 2012-4-27
 */

public class CreateApprovalRecordTask extends SimpleTask{
	
	private double amount;
	
	private String createPerson;
	
	private long createDate;
	
	private String billNumber;
	
	private Mode busMode;
	
	private String billstatus;

	public String getBillstatus(){
    	return billstatus;
    }

	public void setBillstatus(String billstatus){
    	this.billstatus = billstatus;
    }

	public double getAmount(){
    	return amount;
    }

	public void setAmount(double amount){
    	this.amount = amount;
    }

	public String getCreatePerson(){
    	return createPerson;
    }

	public void setCreatePerson(String createPerson){
    	this.createPerson = createPerson;
    }

	public long getCreateDate(){
    	return createDate;
    }

	public void setCreateDate(long createDate){
    	this.createDate = createDate;
    }

	public String getBillNumber(){
    	return billNumber;
    }

	public void setBillNumber(String billNumber){
    	this.billNumber = billNumber;
    }

	public Mode getBusMode(){
    	return busMode;
    }

	public void setBusMode(Mode busMode){
    	this.busMode = busMode;
    }
	

	
}
