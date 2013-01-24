package com.spark.oms.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * ��������
 */
public class OrderServiceInfo  implements Cloneable{
	/**
	 * ��ʶ
	 */
	private GUID RECID;

	/**
	 * order��ʶ
	 */
	private GUID orderRECID;
	
	/**
	 * ��������
	 */
	private String name;
	
	/**
	 * �ͻ�ID
	 */
	private GUID tenantsRECID;
	
	/**
	 * �ͻ�����
	 */
	private String tenantsName;
	
	/**
	 * ������Ա
	 */
	private String saleManager;
	
	/**
	 * ������ԱRECID
	 */
	private GUID saleManagerRECID;
	
	/**
	 * �����˻����
	 */
	private double leaseAccountBalance;
	
	/**
	 * ��Ʒ���
	 */
	private String productCategory;
	
	/**
	 * ��Ʒϵ��
	 */
	private String ProductSerial;
	
	/**
	 * ��ƷRECID
	 */
	private GUID  productRECID;
	
	/**
	 * ��Ʒ����
	 */
	private String productName;
	
	/**
	 * ��Ʒ����
	 */
	private String productCode;
	
	/**
	 * ��Ʒ��Ŀ����
	 */
	private String productItemName;
	
	/**
	 * ��Ʒ��Ŀ
	 */
	private GUID  productItemRECID;
	
	/**
	 * ��ѡ�汾ID
	 */
	private GUID  productVerionRECID;
	
	/**
	 * �Ƽ�����
	 */
	private String BillingCycle;
	
	/**
	 * �Ƽۼ۸�
	 */
	private double billingPrice;
	
	/**
	 * �û���
	 */
	private int userNum;
	
	/**
	 * �û��ϸ���
	 */
	private int userUpOffset = 0;
	
	/**
	 * ����/��
	 */
	private double perUserPrice = 0.00;
	
	/**
	 * ����ʱ��
	 */
	private long createTime;
	
	/**
	 * ��ʼ��������ʱ��
	 */
	private long openRunDate;
	
	/**
	 * �ر�����ʱ��
	 */
	private long closeRunDate;
	
	/**
	 * ��������
	 */
	private long enableRunDate;
	
	/**
	 * ����ʼ����
	 */
	private long serviceBegin;
	
	/**
	 * �����������
	 */
	private long serviceEnd;
	
	/**
	 * ������
	 */
	private int prolongDay;
	
	/**
	 * �Ʒѿ�ʼ����
	 */
	private long billingBegin;
	
	/**
	 * �Ʒѽ�������
	 */
	private long billingEnd;
	
	/**
	 * �ۿ۶�
	 */
	private double discountRate;
	
	/**
	 * �ۿۼ�
	 */
	private double discountMoney;
	
	/**
	 * ���ռ۸�
	 */
	private double finalPrice;
	
	/**
	 * Ӧ�ս��
	 */
	private double dueAmount;
	
	/**
	 * ʵ�ս��
	 */
	private double doneAmount;
	
	/**
	 * �˻����
	 */
	private double refundAmount;
	
	
	/**
	 * ����Ԥ����
	 */
	private double deposit;
	
	/**
	 * ���ŵ���
	 */
	private double smsPrice;
	
	/**
	 * �������
	 */
	private int smsFreeNum;
	
	/**
	 * �ѷ�����
	 */
	private int smsUsedNum;
	
	/**
	 * ���ý��
	 */
	private double usedAmount;
	
	/**
	 * ������Դ��
	 */
	private String source;
	
	/**
	 * ��������
	 */
	private String endType;
	
	/**
	 * ��Ʊ״̬
	 */
	private String invoicestatus;
	
	/**
	 * ����״̬
	 */
	private int feestatus;
	
	/**
	 * ȷ���շ�
	 */
	private String receiptstatus;
	
	/**
	 * ����״̬
	 */
	private int runstatus;
	
	/**
	 * ϵͳ����
	 * @return
	 */
	private String serverName;
	
	/**
	 * ϵͳ��¼����
	 * @return
	 */
	private String serverLoginName;
	
	/**
	 * ��������¼��ַ
	 * @return
	 */
	private String hostAddr;
	
	/**
	 * ���������
	 * @return
	 */
	private String changer;
	
	/**
	 * �����RECID
	 * @return
	 */
	private GUID changerRECID;
	
	/**
	 * �������
	 * @return
	 */
	private long changeDate;
	
	/**
	 * ���ԭ��
	 * @return
	 */
	private String changeReason;
	
	/**
	 * ���ǰ�������
	 * @return
	 */
	private String beforeSerialNumber;
	
	/**
	 * �������
	 */
	private String serialNumber;
	
	/**
	 * ���ǰ����RECID
	 * @return
	 */
	private GUID parentOrderRECID;
	
	/**
	 * ��ֹԭ��
	 */
	private String suspendReason;
	
	/**
	 * ������
	 */
	private String suspendApplayer;
	
	/**
	 * ����ʱ��
	 */
	private long suspendApplayDate;
	
	/**
	 * ������ֹ��־
	 */
	private String suspend;
	
	/**
	 * �տ����
	 * @return
	 */
	private GUID receiptRECID;
	
	/**
	 * �տ
	 */
	private OrderServiceReceiptInfo receiptInfo;
	
	/**
	 * ����״̬
	 * @return
	 */
	private String serverstatus;
	
	/**
	 * �Ƿ�����޸�
	 * @return
	 */
	private String isModified;
	
	/**
	 * �Ƿ���������տ�
	 * @return
	 */
	private String isCharged ;
	
	/**
	 * �ܾ����ֻ�
	 * @return
	 */
	private String bossMobile;
	
	/**
	 * �ܾ�������
	 * @return
	 */
	private String bossName;
	
	/**
	 * �ɷѽ�ֹ����
	 * @return
	 */
	private long chargeEndDate;
	
	
	private int preChangeUserNum;
	
	private GUID parentServiceID;
	
	private GUID pParentServiceID;
	
	private String preFinishReason;
	
	/**
	 * �Ƿ�������֧��¼
	 */
	private String isPayment;
	
	/**
	 * �Ƿ������ӽڵ� 
	 * @return
	 */
	private String hasChildren;
	
	public String getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}

	public int getPreChangeUserNum() {
		return preChangeUserNum;
	}

	public void setPreChangeUserNum(int preChangeUserNum) {
		this.preChangeUserNum = preChangeUserNum;
	}

	public GUID getParentServiceID() {
		return parentServiceID;
	}

	public void setParentServiceID(GUID parentServiceID) {
		this.parentServiceID = parentServiceID;
	}

	public String getPreFinishReason() {
		return preFinishReason;
	}

	public void setPreFinishReason(String preFinshReason) {
		this.preFinishReason = preFinshReason;
	}

	public String getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(String isPayment) {
		this.isPayment = isPayment;
	}

	public long getChargeEndDate() {
		return chargeEndDate;
	}

	public void setChargeEndDate(long chargeEndDate) {
		this.chargeEndDate = chargeEndDate;
	}
	
	

	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public GUID getRECID() {
		return RECID;
	}

	public GUID getOrderRECID() {
		return orderRECID;
	}

	public String getName() {
		return name;
	}

	public GUID getTenantsRECID() {
		return tenantsRECID;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public String getSaleManager() {
		return saleManager;
	}

	public GUID getSaleManagerRECID() {
		return saleManagerRECID;
	}

	public double getLeaseAccountBalance() {
		return leaseAccountBalance;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public String getProductSerial() {
		return ProductSerial;
	}

	public GUID getProductRECID() {
		return productRECID;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductItemName() {
		return productItemName;
	}

	public GUID getProductItemRECID() {
		return productItemRECID;
	}

	public GUID getProductVerionRECID() {
		return productVerionRECID;
	}

	public String getBillingCycle() {
		return BillingCycle;
	}

	public double getBillingPrice() {
		return billingPrice;
	}

	public int getUserNum() {
		return userNum;
	}

	public int getUserUpOffset() {
		return userUpOffset;
	}

	public double getPerUserPrice() {
		return perUserPrice;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getOpenRunDate() {
		return openRunDate;
	}

	public long getCloseRunDate() {
		return closeRunDate;
	}

	public long getEnableRunDate() {
		return enableRunDate;
	}

	public long getServiceBegin() {
		return serviceBegin;
	}

	public long getServiceEnd() {
		return serviceEnd;
	}

	public int getProlongDay() {
		return prolongDay;
	}

	public long getBillingBegin() {
		return billingBegin;
	}

	public long getBillingEnd() {
		return billingEnd;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public double getDiscountMoney() {
		return discountMoney;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public double getDoneAmount() {
		return doneAmount;
	}

	public double getDeposit() {
		return deposit;
	}

	public double getSmsPrice() {
		return smsPrice;
	}

	public int getSmsFreeNum() {
		return smsFreeNum;
	}

	public int getSmsUsedNum() {
		return smsUsedNum;
	}

	public double getUsedAmount() {
		return usedAmount;
	}

	public String getSource() {
		return source;
	}

	public String getEndType() {
		return endType;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public int getFeestatus() {
		return feestatus;
	}

	public String getReceiptStatus() {
		return receiptstatus;
	}

	public int getRunstatus() {
		return runstatus;
	}

	public String getServerName() {
		return serverName;
	}

	public String getServerLoginName() {
		return serverLoginName;
	}

	public String getHostAddr() {
		return hostAddr;
	}

	public String getChanger() {
		return changer;
	}

	public GUID getChangerRECID() {
		return changerRECID;
	}

	public long getChangeDate() {
		return changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public String getBeforeSerialNumber() {
		return beforeSerialNumber;
	}

	public GUID getParentOrderRECID() {
		return parentOrderRECID;
	}

	public String getSuspendReason() {
		return suspendReason;
	}

	public String getSuspendApplayer() {
		return suspendApplayer;
	}

	public long getSuspendApplayDate() {
		return suspendApplayDate;
	}

	public String getSuspend() {
		return suspend;
	}

	public GUID getReceiptRECID() {
		return receiptRECID;
	}

	public String getServerstatus() {
		return serverstatus;
	}

	public String getIsModified() {
		return isModified;
	}

	public String getIsCharged() {
		return isCharged;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public void setOrderRECID(GUID orderRECID) {
		this.orderRECID = orderRECID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTenantsRECID(GUID tenantsRECID) {
		this.tenantsRECID = tenantsRECID;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public void setSaleManager(String saleManager) {
		this.saleManager = saleManager;
	}

	public void setSaleManagerRECID(GUID saleManagerRECID) {
		this.saleManagerRECID = saleManagerRECID;
	}

	public void setLeaseAccountBalance(double leaseAccountBalance) {
		this.leaseAccountBalance = leaseAccountBalance;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductSerial(String productSerial) {
		ProductSerial = productSerial;
	}

	public void setProductRECID(GUID productRECID) {
		this.productRECID = productRECID;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setProductItemName(String productItemName) {
		this.productItemName = productItemName;
	}

	public void setProductItemRECID(GUID productItemRECID) {
		this.productItemRECID = productItemRECID;
	}

	public void setProductVerionRECID(GUID productVerionRECID) {
		this.productVerionRECID = productVerionRECID;
	}

	public void setBillingCycle(String billingCycle) {
		BillingCycle = billingCycle;
	}

	public void setBillingPrice(double billingPrice) {
		this.billingPrice = billingPrice;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public void setUserUpOffset(int userUpOffset) {
		this.userUpOffset = userUpOffset;
	}

	public void setPerUserPrice(double perUserPrice) {
		this.perUserPrice = perUserPrice;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setOpenRunDate(long openRunDate) {
		this.openRunDate = openRunDate;
	}

	public void setCloseRunDate(long closeRunDate) {
		this.closeRunDate = closeRunDate;
	}

	public void setEnableRunDate(long enableRunDate) {
		this.enableRunDate = enableRunDate;
	}

	public void setServiceBegin(long serviceBegin) {
		this.serviceBegin = serviceBegin;
	}

	public void setServiceEnd(long serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	public void setProlongDay(int prolongDay) {
		this.prolongDay = prolongDay;
	}

	public void setBillingBegin(long billingBegin) {
		this.billingBegin = billingBegin;
	}

	public void setBillingEnd(long billingEnd) {
		this.billingEnd = billingEnd;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public void setDiscountMoney(double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public void setDoneAmount(double doneAmount) {
		this.doneAmount = doneAmount;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public void setSmsPrice(double smsPrice) {
		this.smsPrice = smsPrice;
	}

	public void setSmsFreeNum(int smsFreeNum) {
		this.smsFreeNum = smsFreeNum;
	}

	public void setSmsUsedNum(int smsUsedNum) {
		this.smsUsedNum = smsUsedNum;
	}

	public void setUsedAmount(double usedAmount) {
		this.usedAmount = usedAmount;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public void setFeestatus(int feestatus) {
		this.feestatus = feestatus;
	}

	public void setReceiptStatus(String receiptstatus) {
		this.receiptstatus = receiptstatus;
	}

	public void setRunstatus(int runstatus) {
		this.runstatus = runstatus;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setServerLoginName(String serverLoginName) {
		this.serverLoginName = serverLoginName;
	}

	public void setHostAddr(String hostAddr) {
		this.hostAddr = hostAddr;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public void setChangerRECID(GUID changerRECID) {
		this.changerRECID = changerRECID;
	}

	public void setChangeDate(long changeDate) {
		this.changeDate = changeDate;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public void setBeforeSerialNumber(String beforeSerialNumber) {
		this.beforeSerialNumber = beforeSerialNumber;
	}

	public void setParentOrderRECID(GUID parentOrderRECID) {
		this.parentOrderRECID = parentOrderRECID;
	}

	public void setSuspendReason(String suspendReason) {
		this.suspendReason = suspendReason;
	}

	public void setSuspendApplayer(String suspendApplayer) {
		this.suspendApplayer = suspendApplayer;
	}

	public void setSuspendApplayDate(long suspendApplayDate) {
		this.suspendApplayDate = suspendApplayDate;
	}

	public void setSuspend(String suspend) {
		this.suspend = suspend;
	}

	public void setReceiptRECID(GUID receiptRECID) {
		this.receiptRECID = receiptRECID;
	}

	public void setServerstatus(String serverstatus) {
		this.serverstatus = serverstatus;
	}

	public void setIsModified(String isModified) {
		this.isModified = isModified;
	}

	public void setIsCharged(String isCharged) {
		this.isCharged = isCharged;
	}

	public OrderServiceReceiptInfo getReceiptInfo() {
		return receiptInfo;
	}

	public void setReceiptInfo(OrderServiceReceiptInfo receiptInfo) {
		this.receiptInfo = receiptInfo;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	public GUID getpParentServiceID() {
		return pParentServiceID;
	}

	public void setpParentServiceID(GUID pParentServiceID) {
		this.pParentServiceID = pParentServiceID;
	}

	@Override
	public OrderServiceInfo clone() throws CloneNotSupportedException {
		return (OrderServiceInfo)super.clone();
	}
	
}
