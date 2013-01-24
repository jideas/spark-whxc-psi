package com.spark.oms.publish.order.key;

import com.spark.oms.publish.OrderSort;
import com.spark.oms.publish.SortType;

/**
 * 获取订单列表：运营订单，历史订单，变更订单
 * GetOrderItemkey &List<orderItem>
 */
public class GetOrderItemkey {
	
	public static enum SortField {
		
		OrderNo("o.OrderNo"),
		OrderDate("o.CreateTime"),
		TenantsName("o.TenantsName"),
		OrderAmount("o.OrderAmount"),
		OrderRunstatus("s.Runstatus"),
		OrderFeestatus("s.Feestatus"),
		Creator("o.Creator"),
		OrderChangeDate("s.ChangeDate"),
		OrderChangeNo("s.BeforeSerialNumber"),
		Changer("s.Changer");
		
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
	
	private SortType sortType;
	
	private SortField sortField;
	
	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	/**
	 * 签订开始日期
	 */
	private long signStartDate;
	
	/**
	 * 签订结束日期
	 */
	private long signEndDate;
	
	/**
	 * 订单编号
	 *
	 */
	private String serialNumber;
	
	/**
	 * 订单金额起
	 */
	
	private double orderMinAmount;
	
	/**
	 * 订单金额至
	 */
	
	private double orderMaxAmount;
	
	/**
	 * 运行状态
	 */
	private String runstatus[];
	
	/**
	 * 费用状态
	 */
	private String feestatus[];
	
	/**
	 * 数据访问
	 */
	private OrderSort tabType;
	
	/**
	 * 创建时间
	 */
	private String creatorName;
	
	/**
	 * 服务名称
	 */
	private String orderServiceName;
	
	/**
	 * 销售名称
	 */
	private String saleManager;
	
	/**
	 * 租户名称
	 * @return
	 */
	private String tenantsName;
	
	/**
	 * 所搜
	 * @return
	 */
	private String key;
	
	
	/**
	 * 签订日期范围　>=
	 */
	private long signDate;
	
	/**
	 * 特殊的签订日期结束范围　<= 这里暂只有查询本月时会用
	 */
	private long signEndDateSpecial;
	
	public long getSignEndDateSpecial() {
		return signEndDateSpecial;
	}

	public void setSignEndDateSpecial(long signEndDateSpecial) {
		this.signEndDateSpecial = signEndDateSpecial;
	}

	/**
	 * 查询类型，0下拉选项查询，1普通查询,2高级查询
	 */
	private int type;
	
	public long getSignDate() {
		return signDate;
	}

	public void setSignDate(long signDate) {
		this.signDate = signDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getSignStartDate() {
		return signStartDate;
	}

	public void setSignStartDate(long signStartDate) {
		this.signStartDate = signStartDate;
	}

	public long getSignEndDate() {
		return signEndDate;
	}

	public void setSignEndDate(long signEndDate) {
		this.signEndDate = signEndDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getOrderMinAmount() {
		return orderMinAmount;
	}

	public void setOrderMinAmount(double orderMinAmount) {
		this.orderMinAmount = orderMinAmount;
	}

	public double getOrderMaxAmount() {
		return orderMaxAmount;
	}

	public void setOrderMaxAmount(double orderMaxAmount) {
		this.orderMaxAmount = orderMaxAmount;
	}

	public String[] getRunstatus() {
		return runstatus;
	}

	public void setRunstatus(String[] runstatus) {
		this.runstatus = runstatus;
	}

	public String[] getFeestatus() {
		return feestatus;
	}

	public void setFeestatus(String[] feestatus) {
		this.feestatus = feestatus;
	}

	public OrderSort getTabType() {
		return tabType;
	}

	public void setTabType(OrderSort tabType) {
		this.tabType = tabType;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getOrderServiceName() {
		return orderServiceName;
	}

	public void setOrderServiceName(String orderServiceName) {
		this.orderServiceName = orderServiceName;
	}

	public String getSaleManager() {
		return saleManager;
	}

	public void setSaleManager(String saleManager) {
		this.saleManager = saleManager;
	}
	
}
