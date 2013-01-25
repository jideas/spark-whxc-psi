package com.spark.order.purchase.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.order.intf.entity.EntityFather;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * <p>
 * �ɹ���Ʒ
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-4-1
 */
public class PurchaseGoods2 extends EntityFather {
	protected GUID goodsItemId;// ��Ʒ���GUID guid
	protected String goodsCode;// ��Ʒ���� nvarchar 20
	protected String goodsNo;
	protected String goodsName;// ��Ʒ���� nvarchar 50
	protected String goodsProperties;// ��Ʒ���� nvarchar 100
	protected int countDecimal;// ��ƷС��λ�� int
	protected String goodsUnit;// ��λ nvarchar 12
	protected double purchaseCount;// �ɹ����� numeric 17 5
	protected GUID sourceId;// ��Ӧ�ֿ��� guid
	protected String sourceName;// �ֿ����� nvarchar 50
	protected GUID partnerId;// ��Ӧ��GUID guid
	protected String partnerShortName;// ��Ӧ������ nvarchar 20
	protected String partnerName;// ��Ӧ��ȫ�� nvarchar 100
	protected String partnerNamePY;// ��Ӧ��ȫ��ƴ�� nvarchar 50
	protected String partnerFax;// ��Ӧ�̴��� nvarchar 20
	protected GUID contactId;// ��ϵ��GUID guid
	protected String contactName;// ��ϵ�� nvarchar 40
	protected String contactTel;// ��ϵ�˵绰 nvarchar 20
	protected String contactPhone;// ��ϵ���ֻ� nvarchar 20
	protected double price_purchase = -1;// �ɹ����� numeric 17 2
	protected double price_lastPurchase;// �ϴβɹ����� numeric 17 2
	protected String purchaseCause;// �ɹ�ԭ�� nvarchar 1000

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsProperties() {
		return goodsProperties;
	}

	public void setGoodsProperties(String goodsProperties) {
		this.goodsProperties = goodsProperties;
	}

	public int getScale() {
		return GoodsScale.PSI_SCALE;
	}

	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public double getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(double purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public GUID getSourceId() {
		return sourceId;
	}

	public void setSourceId(GUID sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerShortName() {
		return partnerShortName;
	}

	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
		this.partnerNamePY = PinyinHelper.getLetter(partnerName);
	}

	public String getPartnerNamePY() {
		return partnerNamePY;
	}

	// public void setPartnerNamePY(String partnerNamePY) {
	// this.partnerNamePY = partnerNamePY;
	// }
	public String getPartnerFax() {
		return partnerFax;
	}

	public void setPartnerFax(String partnerFax) {
		this.partnerFax = partnerFax;
	}

	public GUID getContactId() {
		return contactId;
	}

	public void setContactId(GUID contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public double getPrice_purchase() {
		return price_purchase;
	}

	public void setPrice_purchase(double pricePurchase) {
		price_purchase = pricePurchase;
	}

	public double getPrice_lastPurchase() {
		return price_lastPurchase;
	}

	public void setPrice_lastPurchase(double priceLastPurchase) {
		price_lastPurchase = priceLastPurchase;
	}

	public String getPurchaseCause() {
		return purchaseCause;
	}

	public void setPurchaseCause(String purchaseCause) {
		this.purchaseCause = purchaseCause;
	}
}
