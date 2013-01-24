
package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>������Ʒ���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class OthersInventory{
	private GUID recid;
	private GUID tenantsGuid;
	private GUID storeGuid;
	private GUID goodsGuid;
	private String storeName;
	
	//��ʼ����
	private double initCount;
	//�������
	private double goodsCount;
	
	//��Ʒ����
	private String goodsName;
	//��Ʒ����
	private String goodsProperty;
	//��Ʒ��λ
	private String goodsUnit;
	
	//�Ƿ��ʼ��
	private boolean isInit;

	public GUID getRecid() {
		return recid;
	}

	public void setRecid(GUID recid) {
		this.recid = recid;
	}

	public GUID getTenantsGuid() {
		return tenantsGuid;
	}

	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}

	public GUID getStoreGuid() {
		return storeGuid;
	}

	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}

	public GUID getGoodsGuid() {
		return goodsGuid;
	}

	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}

	public double getInitCount() {
		return initCount;
	}

	public void setInitCount(double initCount) {
		this.initCount = initCount;
	}
	
	public double getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(double goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getGoodsProperty() {
		return goodsProperty;
	}

	public void setGoodsProperty(String goodsProperty) {
		this.goodsProperty = goodsProperty;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	
	
}
