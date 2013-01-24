package com.spark.oms.publish.order.task;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductCategory;
import com.spark.oms.publish.order.entity.OrderServiceInfo;

/**
 * �½����޸Ķ���
 */
public class CreateOrderTask extends SimpleTask{
	
	/**
	 * ��ʶ
	 */
	private GUID id;
	
	/**
	 * �������к�
	 */
	private String orderNo;
	
	/**
	 * �������
	 */
	private String orderType;
	
	/**
	 * �ͻ�Id
	 */
	private GUID tenantsId;
	
	/**
	 * �ͻ�����
	 */
	private String tenantsName;
	
	/**
	 * bossName;
	 */
	private String bossName;
	
	/**
	 * �ܾ����ֻ�����
	 */
	private String bossMobile;
	
	/**
	 * ����Id
	 */
	private GUID saleId;
	
	/**
	 * ��������
	 */
	private String saleName;
	
	/**
	 * ������Ա���
	 */
	private String salerNo;
	
	/**
	 * �������
	 */
	private double orderAmount = 0.00;
	
	/**
	 * Ӧ��
	 */
	private double dueAmount = 0.00;
	
	/**
	 * ʵ��
	 */
	private double doneAmount = 0.00;
	
	/**
	 * ������Id
	 */
	private GUID creatorId;
	
	/**
	 * ����������
	 */
	private String creatorName;
	
	/**
	 * ����ʱ��
	 */
	private long createTime;
	
	/**
	 * �����������
	 */
	private  ProductCategory pCategory;
	
	/**
	 * �����Ƿ�����޸�
	 * @return
	 */
	private String isChange;

	private String changer;
	
	private GUID changerRECID;
	
	private long changeTime;
	
	
	/**
	 * �����б�
	 */
	private List<OrderServiceInfo> orderServiceInfo = new ArrayList<OrderServiceInfo>(); 
	
	/**
	 * ��ɾ���Ķ��������б�
	 */
	private List<OrderServiceInfo> deleteOrderServiceInfo = new ArrayList<OrderServiceInfo>();

	public GUID getId() {
		return id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public GUID getTenantsId() {
		return tenantsId;
	}

	public String getTenantsName() {
		return tenantsName;
	}

	public GUID getSaleId() {
		return saleId;
	}

	public String getSaleName() {
		return saleName;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public double getDoneAmount() {
		return doneAmount;
	}

	public GUID getCreatorId() {
		return creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public ProductCategory getpCategory() {
		return pCategory;
	}

	public List<OrderServiceInfo> getOrderServiceInfo() {
		return orderServiceInfo;
	}

	public List<OrderServiceInfo> getDeleteOrderServiceInfo() {
		return deleteOrderServiceInfo;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}

	public void setSaleId(GUID saleId) {
		this.saleId = saleId;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public void setDoneAmount(double doneAmount) {
		this.doneAmount = doneAmount;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setpCategory(ProductCategory pCategory) {
		this.pCategory = pCategory;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public String getBossMobile() {
		return bossMobile;
	}

	public void setBossMobile(String bossMobile) {
		this.bossMobile = bossMobile;
	}

	public void setOrderServiceInfo(List<OrderServiceInfo> orderServiceInfo) {
		this.orderServiceInfo = orderServiceInfo;
	}

	public void setDeleteOrderServiceInfo(
			List<OrderServiceInfo> deleteOrderServiceInfo) {
		this.deleteOrderServiceInfo = deleteOrderServiceInfo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}

	public String getChanger() {
		return changer;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public GUID getChangerRECID() {
		return changerRECID;
	}

	public void setChangerRECID(GUID changerRECID) {
		this.changerRECID = changerRECID;
	}

	public long getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(long changeTime) {
		this.changeTime = changeTime;
	}

	public String getSalerNo() {
		return salerNo;
	}

	public void setSalerNo(String salerNo) {
		this.salerNo = salerNo;
	}
	
	public void addOrderService(OrderServiceInfo osi){
		this.orderServiceInfo.add(osi);
	}
	
}
