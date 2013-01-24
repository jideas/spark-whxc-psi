package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.PaymentType;

/**
 * 租户交款条目列表:确认收款
 */
public class TenantsReceiptDetailItem {
	/**
	 * 租户名称
	 */
	private String tenantsName;
	
	/**
	 * 租户标识
	 */
	private GUID tenantsId;
	
	/**
	 * 销售人员姓名
	 */
	private String saleName;
	
	/**
	 * 待确认金额
	 */
	private double unPayAmount=0;
	

	/**
	 * 租赁账户金额
	 */
	private double leaseAccountBalance=0;
	
	/**
	 * 计量账户金额
	 */
	private double pieceAccountBalance=0;
	
	
	/**
	 * 应交款记录列表
	 */
	private java.util.List<TenantReceipt> tenantsReceiptList = new ArrayList<TenantReceipt>();
	
	public void add(TenantReceipt tenantReceipt){
		tenantsReceiptList.add(tenantReceipt);
	}
	
	
	public class  TenantReceipt{
		private GUID receiptGuid;
		private double receiptAmount;
		private List<TenantsReceiptServiceInfo> list = new ArrayList<TenantsReceiptServiceInfo>();
		public GUID getReceiptGuid() {
			return receiptGuid;
		}
		public void setReceiptGuid(GUID receiptGuid) {
			this.receiptGuid = receiptGuid;
		}
		public double getReceiptAmount() {
			return receiptAmount;
		}
		public void setReceiptAmount(double receiptAmount) {
			this.receiptAmount = receiptAmount;
		}
		public List<TenantsReceiptServiceInfo> getList() {
			return list;
		}
		public void setList(List<TenantsReceiptServiceInfo> list) {
			this.list = list;
		}
		public void add(TenantsReceiptServiceInfo info){
			this.list.add(info);
		}
	}
	
	public class TenantsReceiptServiceInfo{
		
		/**
		 * 订单主键
		 */
		private GUID orderRecid;
		
		/**
		 * 租户名称
		 */
		private String tenantsName;
		
		/**
		 * 产品规格
		 */
		private String productCategory;
		
		/**
		 * 产品系列
		 */
		private String productSerial;
		
		/**
		 * 产品名称
		 */
		private String productName;
		
		/**
		 * 租用规格
		 */
		private String productItemName;
		
		/**
		 * 计费周期
		 */
		private String billingCycle;
		
		/**
		 * 发生日期
		 */
		private long createTime;
		
		/**
		 * 收费账户
		 * @return
		 */
		private String account;
		
		/**
		 * 账户名称
		 * @return
		 */
		private String accountName;
		
		/**
		 * 账户持有者
		 * @return
		 */
		private String accountHolder;
		
		/**
		 * 银行
		 * @return
		 */
		private String bankName;
		
		/**
		 * 缴款方式
		 */
		private PaymentType paymentType;
		
		
		
		/**
		 * 冗余字段
		 */
		private GUID receiptGuid;
		private double receiptAmount;
		
		
		

		public String getProductCategory() {
			return productCategory;
		}

		public void setProductCategory(String productCategory) {
			this.productCategory = productCategory;
		}

		public String getProductSerial() {
			return productSerial;
		}

		public void setProductSerial(String productSerial) {
			this.productSerial = productSerial;
		}

		public GUID getReceiptGuid() {
			return receiptGuid;
		}

		public void setReceiptGuid(GUID receiptGuid) {
			this.receiptGuid = receiptGuid;
		}

		public double getReceiptAmount() {
			return receiptAmount;
		}

		public void setReceiptAmount(double receiptAmount) {
			this.receiptAmount = receiptAmount;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getAccountHolder() {
			return accountHolder;
		}

		public void setAccountHolder(String accountHolder) {
			this.accountHolder = accountHolder;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public GUID getOrderRecid() {
			return orderRecid;
		}

		public void setOrderRecid(GUID orderRecid) {
			this.orderRecid = orderRecid;
		}

		public String getTenantsName() {
			return tenantsName;
		}

		public void setTenantsName(String tenantsName) {
			this.tenantsName = tenantsName;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductItemName() {
			return productItemName;
		}

		public void setProductItemName(String productItemName) {
			this.productItemName = productItemName;
		}

		public String getBillingCycle() {
			return billingCycle;
		}

		public void setBillingCycle(String billingCycle) {
			this.billingCycle = billingCycle;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public PaymentType getPaymentType() {
			return paymentType;
		}

		public void setPaymentType(PaymentType paymentType) {
			this.paymentType = paymentType;
		}
		
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public double getUnPayAmount() {
		return unPayAmount;
	}

	public void setUnPayAmount(double unPayAmount) {
		this.unPayAmount = unPayAmount;
	}

	public double getLeaseAccountBalance() {
		return leaseAccountBalance;
	}

	public void setLeaseAccountBalance(double leaseAccountBalance) {
		this.leaseAccountBalance = leaseAccountBalance;
	}

	public double getPieceAccountBalance() {
		return pieceAccountBalance;
	}

	public void setPieceAccountBalance(double pieceAccountBalance) {
		this.pieceAccountBalance = pieceAccountBalance;
	}

	public java.util.List<TenantReceipt> getTenantsReceiptList() {
		return tenantsReceiptList;
	}

	public void setTenantsReceiptList(
			java.util.List<TenantReceipt> tenantsReceiptList) {
		this.tenantsReceiptList = tenantsReceiptList;
	}
}
