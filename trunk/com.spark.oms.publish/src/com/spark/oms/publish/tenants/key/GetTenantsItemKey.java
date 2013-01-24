package com.spark.oms.publish.tenants.key;

import com.spark.oms.publish.CycleType;
import com.spark.oms.publish.SortType;
import com.spark.oms.publish.TenantsType;


/**
 * 获得正式客户、流失客户列表
 * GetTenantsItemKey & TenantsListIntf
 */
public class GetTenantsItemKey {
	
	public static enum SortField {
		
		TenantsNameColumn("Name"),//租户名称
		firstSignDate("FirstSignOrderDate"),//首次签约时间
		serverTime("ServeMonth"),//服务时长(月)
		come("ReceiptAmount"),//累计收款金额
		receipt("InvoiceAmount"),//累计开票金额
		RelatorColumn("BossName"),//联系人
		SaleManager("SalerManager"),//客户经理
		leaseAmount("LeaseAccountBalance"),//租金账户金额
		pieceAmount("PieceAccountBalance"),//"计量账户金额"
		allTotal("LeaseAccountBalance");//"可退金额"

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}

	private SortField sortField;

	private SortType sortType;
	
	/**
	 * 时间段
	 */
	private CycleType cycleType;
	
	/**
	 * 日期范围*
	 */
	private long date;
	
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * 客户名称:销售人员姓名:联系人姓名*
	 */
	private String key;
	
	/**
	 * 客户类别*
	 */
	private TenantsType tenantsType;

	public CycleType getCycleType() {
		return cycleType;
	}

	public void setCycleType(CycleType cycleType) {
		this.cycleType = cycleType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TenantsType getTenantsType() {
		return tenantsType;
	}

	public void setTenantsType(TenantsType tenantsType) {
		this.tenantsType = tenantsType;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
}