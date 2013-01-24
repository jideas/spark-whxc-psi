package com.spark.oms.publish.order.task;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductCategory;
import com.spark.oms.publish.order.entity.OrderServiceInfo;

/**
 * 新建、修改订单
 */
public class CreateOrderTask extends SimpleTask{
	
	/**
	 * 标识
	 */
	private GUID id;
	
	/**
	 * 订单序列号
	 */
	private String orderNo;
	
	/**
	 * 订单类别
	 */
	private String orderType;
	
	/**
	 * 客户Id
	 */
	private GUID tenantsId;
	
	/**
	 * 客户名称
	 */
	private String tenantsName;
	
	/**
	 * bossName;
	 */
	private String bossName;
	
	/**
	 * 总经理手机号码
	 */
	private String bossMobile;
	
	/**
	 * 销售Id
	 */
	private GUID saleId;
	
	/**
	 * 销售名称
	 */
	private String saleName;
	
	/**
	 * 销售人员编号
	 */
	private String salerNo;
	
	/**
	 * 订单金额
	 */
	private double orderAmount = 0.00;
	
	/**
	 * 应付
	 */
	private double dueAmount = 0.00;
	
	/**
	 * 实收
	 */
	private double doneAmount = 0.00;
	
	/**
	 * 创建者Id
	 */
	private GUID creatorId;
	
	/**
	 * 创建者姓名
	 */
	private String creatorName;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 创建服务类别
	 */
	private  ProductCategory pCategory;
	
	/**
	 * 订单是否进行修改
	 * @return
	 */
	private String isChange;

	private String changer;
	
	private GUID changerRECID;
	
	private long changeTime;
	
	
	/**
	 * 服务列表
	 */
	private List<OrderServiceInfo> orderServiceInfo = new ArrayList<OrderServiceInfo>(); 
	
	/**
	 * 被删除的订单服务列表
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
