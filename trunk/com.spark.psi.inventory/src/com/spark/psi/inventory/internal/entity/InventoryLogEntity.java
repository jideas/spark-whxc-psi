package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ˮ
 *
 */
public class InventoryLogEntity {
	
	private GUID id;//	��ʶ
	private GUID storeId;//	�ֿ�GUID
	private GUID stockId;//	��ƷGUID
	private String name;//	��Ʒ����
	private String properties;//	��Ʒ����
	private String unit;//	��Ʒ��λ
	private GUID categoryId;//	��Ʒ����GUID
	private String code;//	��Ʒ���
	private String stockNo;//	��Ʒ����
	private GUID orderId;//	����id
	private String orderNo;//	���ݱ��
	private String logType;//	��ˮ����
	private double instoCount;//	�������
	private double instoAmount;//	�����
	private int scale;//	��Ʒ����
	private double outstoCount;//	��������
	private double outstoAmount;//	������
	private String createPerson;//	������
	private long createdDate;//	��������
	private String inventoryType;
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public GUID getStockId() {
		return stockId;
	}
	public void setStockId(GUID stockId) {
		this.stockId = stockId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public GUID getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(GUID categoryId) {
		this.categoryId = categoryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public GUID getOrderId() {
		return orderId;
	}
	public void setOrderId(GUID orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public double getInstoCount() {
		return instoCount;
	}
	public void setInstoCount(double instoCount) {
		this.instoCount = instoCount;
	}
	public double getInstoAmount() {
		return instoAmount;
	}
	public void setInstoAmount(double instoAmount) {
		this.instoAmount = instoAmount;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public double getOutstoCount() {
		return outstoCount;
	}
	public void setOutstoCount(double outstoCount) {
		this.outstoCount = outstoCount;
	}
	public double getOutstoAmount() {
		return outstoAmount;
	}
	public void setOutstoAmount(double outstoAmount) {
		this.outstoAmount = outstoAmount;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	
}
