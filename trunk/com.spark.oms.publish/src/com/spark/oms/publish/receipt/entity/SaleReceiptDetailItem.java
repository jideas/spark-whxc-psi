package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 销售人员交款条目数据：财务收款
 * @author mengyongfeng
 *
 */
public class SaleReceiptDetailItem {

	//销售合计
	private double totalSaleAmount;
	
	
	//销售人员
	private String saleName = "";
	
	//销售缴款记录
	private java.util.List<SaleReceipt> saleReceiptList = new ArrayList<SaleReceipt>();
	public void add(SaleReceipt saleReceipt){
		saleReceiptList.add(saleReceipt);
	}
	public class  SaleReceipt{
		private GUID receiptGuid;
		private double receiptAmount;
		private List<SaleReceiptServiceInfo> list = new ArrayList<SaleReceiptServiceInfo>();
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
		public List<SaleReceiptServiceInfo> getList() {
			return list;
		}
		public void setList(List<SaleReceiptServiceInfo> list) {
			this.list = list;
		}
		public void add(SaleReceiptServiceInfo info){
			this.list.add(info);
		}
	}
	
	public class SaleReceiptServiceInfo{
		
		/**
		 * 订单主键
		 */
		private GUID orderRecid;
		
		/**
		 * 租户名称
		 */
		private String tenantsName;
		/**
		 * 产品类别
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
		 * 暂时处理
		 * @return
		 */
		private double receiptAmount;
		
		private GUID receiptGuid;
		
		private GUID orderServiceGuid;
		
		

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

		public double getReceiptAmount() {
			return receiptAmount;
		}

		public void setReceiptAmount(double receiptAmount) {
			this.receiptAmount = receiptAmount;
		}

		public GUID getReceiptGuid() {
			return receiptGuid;
		}

		public void setReceiptGuid(GUID receiptGuid) {
			this.receiptGuid = receiptGuid;
		}

		public GUID getOrderServiceGuid() {
			return orderServiceGuid;
		}

		public void setOrderServiceGuid(GUID orderServiceGuid) {
			this.orderServiceGuid = orderServiceGuid;
		}
	}

	public double getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(double totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public java.util.List<SaleReceipt> getSaleReceiptList() {
		return saleReceiptList;
	}

	public void setSaleReceiptList(java.util.List<SaleReceipt> saleReceiptList) {
		this.saleReceiptList = saleReceiptList;
	}
	
	
}

