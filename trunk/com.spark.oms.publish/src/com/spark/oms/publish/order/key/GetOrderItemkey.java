package com.spark.oms.publish.order.key;

import com.spark.oms.publish.OrderSort;
import com.spark.oms.publish.SortType;

/**
 * ��ȡ�����б���Ӫ��������ʷ�������������
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
	 * ǩ����ʼ����
	 */
	private long signStartDate;
	
	/**
	 * ǩ����������
	 */
	private long signEndDate;
	
	/**
	 * �������
	 *
	 */
	private String serialNumber;
	
	/**
	 * ���������
	 */
	
	private double orderMinAmount;
	
	/**
	 * ���������
	 */
	
	private double orderMaxAmount;
	
	/**
	 * ����״̬
	 */
	private String runstatus[];
	
	/**
	 * ����״̬
	 */
	private String feestatus[];
	
	/**
	 * ���ݷ���
	 */
	private OrderSort tabType;
	
	/**
	 * ����ʱ��
	 */
	private String creatorName;
	
	/**
	 * ��������
	 */
	private String orderServiceName;
	
	/**
	 * ��������
	 */
	private String saleManager;
	
	/**
	 * �⻧����
	 * @return
	 */
	private String tenantsName;
	
	/**
	 * ����
	 * @return
	 */
	private String key;
	
	
	/**
	 * ǩ�����ڷ�Χ��>=
	 */
	private long signDate;
	
	/**
	 * �����ǩ�����ڽ�����Χ��<= ������ֻ�в�ѯ����ʱ����
	 */
	private long signEndDateSpecial;
	
	public long getSignEndDateSpecial() {
		return signEndDateSpecial;
	}

	public void setSignEndDateSpecial(long signEndDateSpecial) {
		this.signEndDateSpecial = signEndDateSpecial;
	}

	/**
	 * ��ѯ���ͣ�0����ѡ���ѯ��1��ͨ��ѯ,2�߼���ѯ
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
