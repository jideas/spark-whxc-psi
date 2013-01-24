package com.spark.psi.account.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsType;

/**
 * <p>修改往来款金额</p>
 * 修改 应收、应付、实收、实付金额
  */

public class CusdealTask extends SimpleTask {

	/**
	 * 客户/供应商guid
	 */
	private GUID partnerId;
	/**
	 * 应收金额
	 */
	private double planAmount;
	/**
	 * 实收金额
	 */
	private double realAmount;
	/**
	 * 往来类型
	 * 请查看SAAS.CusdealType
	 */
	private DealingsType cusdealType;
	/**
	 * 单据guid，没有可以不设
	 */
	private GUID billsGuid;
	/**
	 * 单据编号，没有可以不设
	 */
	private String billsNo;
	private GUID accountBillsId;  //	财务单据GUID
	private String accountBillsNo;//	财务单据编号

	public GUID getAccountBillsId() {
		return accountBillsId;
	}

	public String getAccountBillsNo() {
		return accountBillsNo;
	}

	/**
	 * 收款方式
	 */
	private String receiptType;
	/**
	 * 时间，不传我就默认当前时间（非特殊情况请不要传）
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
