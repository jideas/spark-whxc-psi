package com.spark.psi.account.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsType;

/**
 * <p>�޸���������</p>
 * �޸� Ӧ�ա�Ӧ����ʵ�ա�ʵ�����
  */

public class CusdealTask extends SimpleTask {

	/**
	 * �ͻ�/��Ӧ��guid
	 */
	private GUID partnerId;
	/**
	 * Ӧ�ս��
	 */
	private double planAmount;
	/**
	 * ʵ�ս��
	 */
	private double realAmount;
	/**
	 * ��������
	 * ��鿴SAAS.CusdealType
	 */
	private DealingsType cusdealType;
	/**
	 * ����guid��û�п��Բ���
	 */
	private GUID billsGuid;
	/**
	 * ���ݱ�ţ�û�п��Բ���
	 */
	private String billsNo;
	private GUID accountBillsId;  //	���񵥾�GUID
	private String accountBillsNo;//	���񵥾ݱ��

	public GUID getAccountBillsId() {
		return accountBillsId;
	}

	public String getAccountBillsNo() {
		return accountBillsNo;
	}

	/**
	 * �տʽ
	 */
	private String receiptType;
	/**
	 * ʱ�䣬�����Ҿ�Ĭ�ϵ�ǰʱ�䣨����������벻Ҫ����
	 */
	private long pubdate;
	
	private String remark;
	
	public CusdealTask() {
		super();
	}
	
	/**
	 * @param partnerId
	 * @param planAmount
	 * @param realAmount
	 * @param cusdealType
	 * @param billsGuid
	 * @param billsNo
	 */
	public CusdealTask(GUID partnerId, DealingsType cusdealType, double planAmount, double realAmount,
			 GUID billsGuid, String billsNo) {
		super();
		this.partnerId = partnerId;
		this.planAmount = planAmount;
		this.realAmount = realAmount;
		this.cusdealType = cusdealType;
		this.billsGuid = billsGuid;
		this.billsNo = billsNo;
	}
	
	public CusdealTask(GUID partnerId, DealingsType cusdealType, double planAmount, double realAmount,
			 GUID billsGuid, String billsNo,String receiptType,GUID accountBillsId,String accountBillsNo) {
		super();                                               
		this.partnerId = partnerId;
		this.planAmount = planAmount;
		this.realAmount = realAmount;
		this.cusdealType = cusdealType;
		this.billsGuid = billsGuid;
		this.billsNo = billsNo;
		this.receiptType = receiptType;
		this.accountBillsId = accountBillsId;
		this.accountBillsNo = accountBillsNo;
	}
	
	public long getPubdate() {
		return pubdate;
	}

	public void setPubdate(long pubdate) {
		this.pubdate = pubdate;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(double planAmount) {
		this.planAmount = planAmount;
	}

	public double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}

	public DealingsType getCusdealType() {
		return cusdealType;
	}

	public void setCusdealType(DealingsType cusdealType) {
		this.cusdealType = cusdealType;
	}

	public GUID getBillsGuid() {
		return billsGuid;
	}

	public void setBillsGuid(GUID billsGuid) {
		this.billsGuid = billsGuid;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
