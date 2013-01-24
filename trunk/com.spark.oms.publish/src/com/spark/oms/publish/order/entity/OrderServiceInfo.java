package com.spark.oms.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
/**
 * 订单服务
 */
public class OrderServiceInfo  implements Cloneable{
	/**
	 * 标识
	 */
	private GUID RECID;

	/**
	 * order标识
	 */
	private GUID orderRECID;
	
	/**
	 * 服务名称
	 */
	private String name;
	
	/**
	 * 客户ID
	 */
	private GUID tenantsRECID;
	
	/**
	 * 客户名称
	 */
	private String tenantsName;
	
	/**
	 * 销售人员
	 */
	private String saleManager;
	
	/**
	 * 销售人员RECID
	 */
	private GUID saleManagerRECID;
	
	/**
	 * 租赁账户余额
	 */
	private double leaseAccountBalance;
	
	/**
	 * 产品类别
	 */
	private String productCategory;
	
	/**
	 * 产品系列
	 */
	private String ProductSerial;
	
	/**
	 * 产品RECID
	 */
	private GUID  productRECID;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 产品名称
	 */
	private String productCode;
	
	/**
	 * 产品条目名称
	 */
	private String productItemName;
	
	/**
	 * 产品条目
	 */
	private GUID  productItemRECID;
	
	/**
	 * 所选版本ID
	 */
	private GUID  productVerionRECID;
	
	/**
	 * 计价周期
	 */
	private String BillingCycle;
	
	/**
	 * 计价价格
	 */
	private double billingPrice;
	
	/**
	 * 用户数
	 */
	private int userNum;
	
	/**
	 * 用户上浮数
	 */
	private int userUpOffset = 0;
	
	/**
	 * 单价/人
	 */
	private double perUserPrice = 0.00;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 开始启动运行时间
	 */
	private long openRunDate;
	
	/**
	 * 关闭运行时间
	 */
	private long closeRunDate;
	
	/**
	 * 启用日期
	 */
	private long enableRunDate;
	
	/**
	 * 服务开始日期
	 */
	private long serviceBegin;
	
	/**
	 * 服务结束日期
	 */
	private long serviceEnd;
	
	/**
	 * 宽限期
	 */
	private int prolongDay;
	
	/**
	 * 计费开始日期
	 */
	private long billingBegin;
	
	/**
	 * 计费结束日期
	 */
	private long billingEnd;
	
	/**
	 * 折扣额
	 */
	private double discountRate;
	
	/**
	 * 折扣价
	 */
	private double discountMoney;
	
	/**
	 * 最终价格
	 */
	private double finalPrice;
	
	/**
	 * 应收金额
	 */
	private double dueAmount;
	
	/**
	 * 实收金额
	 */
	private double doneAmount;
	
	/**
	 * 退还金额
	 */
	private double refundAmount;
	
	
	/**
	 * 短信预存金额
	 */
	private double deposit;
	
	/**
	 * 短信单价
	 */
	private double smsPrice;
	
	/**
	 * 免费条数
	 */
	private int smsFreeNum;
	
	/**
	 * 已发送数
	 */
	private int smsUsedNum;
	
	/**
	 * 已用金额
	 */
	private double usedAmount;
	
	/**
	 * 订单来源：
	 */
	private String source;
	
	/**
	 * 结束类型
	 */
	private String endType;
	
	/**
	 * 开票状态
	 */
	private String invoicestatus;
	
	/**
	 * 费用状态
	 */
	private int feestatus;
	
	/**
	 * 确认收费
	 */
	private String receiptstatus;
	
	/**
	 * 运行状态
	 */
	private int runstatus;
	
	/**
	 * 系统名称
	 * @return
	 */
	private String serverName;
	
	/**
	 * 系统登录名称
	 * @return
	 */
	private String serverLoginName;
	
	/**
	 * 服务器登录地址
	 * @return
	 */
	private String hostAddr;
	
	/**
	 * 变更人姓名
	 * @return
	 */
	private String changer;
	
	/**
	 * 变更人RECID
	 * @return
	 */
	private GUID changerRECID;
	
	/**
	 * 变更日期
	 * @return
	 */
	private long changeDate;
	
	/**
	 * 变更原因
	 * @return
	 */
	private String changeReason;
	
	/**
	 * 变更前订单序号
	 * @return
	 */
	private String beforeSerialNumber;
	
	/**
	 * 订单编号
	 */
	private String serialNumber;
	
	/**
	 * 变更前订单RECID
	 * @return
	 */
	private GUID parentOrderRECID;
	
	/**
	 * 中止原因
	 */
	private String suspendReason;
	
	/**
	 * 申请人
	 */
	private String suspendApplayer;
	
	/**
	 * 申请时间
	 */
	private long suspendApplayDate;
	
	/**
	 * 申请中止标志
	 */
	private String suspend;
	
	/**
	 * 收款单编码
	 * @return
	 */
	private GUID receiptRECID;
	
	/**
	 * 收款单
	 */
	private OrderServiceReceiptInfo receiptInfo;
	
	/**
	 * 服务状态
	 * @return
	 */
	private String serverstatus;
	
	/**
	 * 是否进行修改
	 * @return
	 */
	private String isModified;
	
	/**
	 * 是否进行销售收款
	 * @return
	 */
	private String isCharged ;
	
	/**
	 * 总经理手机
	 * @return
	 */
	private String bossMobile;
	
	/**
	 * 总经理姓名
	 * @return
	 */
	private String bossName;
	
	/**
	 * 缴费截止日期
	 * @return
	 */
	private long chargeEndDate;
	
	
	private int preChangeUserNum;
	
	private GUID parentServiceID;
	
	private GUID pParentServiceID;
	
	private String preFinishReason;
	
	/**
	 * 是否生成收支记录
	 */
	private String isPayment;
	
	/**
	 * 是否已有子节点 
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
