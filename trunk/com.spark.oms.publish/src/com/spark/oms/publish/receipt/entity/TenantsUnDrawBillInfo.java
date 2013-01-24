package com.spark.oms.publish.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 租户未开票基本信息
 */
public class TenantsUnDrawBillInfo {
	

	/**
	 * 客户标识
	 */
	private GUID tenantsId;
	
	/**
	 * 客户名称
	 */
	private String tenantsName;
	
	/**
	 * 金额
	 */
	private double unDrawBillAmount;
	
	/**
	 * 销售
	 */
	private String saleName;
	
	private List<TenantUnDraw> unDrawlist = new ArrayList<TenantUnDraw>();
	
	public void add(TenantUnDraw tenantUnDraw){
		unDrawlist.add(tenantUnDraw);
	}
	
	public class  TenantUnDraw{
		private GUID receiptGuid;
		private double receiptAmount;
		private List<TenantService> list = new ArrayList<TenantService>();
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
		public List<TenantService> getList() {
			return list;
		}
		public void setList(List<TenantService> list) {
			this.list = list;
		}
		public void add(TenantService info){
			this.list.add(info);
		}
	}
	
	public class TenantService{
		
		/**
		 * 订单主键
		 */
		private GUID orderRecid;
		
		/**
		 * 租户名称
		 */
		private String tenantsName;
		
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
		 * 付款日期
		 */
		private long paydate;
		
		/**
		 * 冗余
		 * @return
		 */
		private GUID receiptGuid;
		private double receiptAmount;
		
		

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

		public long getPaydate() {
			return paydate;
		}

		public void setPaydate(long paydate) {
			this.paydate = paydate;
		}
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public double getUnDrawBillAmount() {
		return unDrawBillAmount;
	}

	public void setUnDrawBillAmount(double unDrawBillAmount) {
		this.unDrawBillAmount = unDrawBillAmount;
	}

	public List<TenantUnDraw> getUnDrawlist() {
		return unDrawlist;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public void setUnDrawlist(List<TenantUnDraw> unDrawlist) {
		this.unDrawlist = unDrawlist;
	}
}
