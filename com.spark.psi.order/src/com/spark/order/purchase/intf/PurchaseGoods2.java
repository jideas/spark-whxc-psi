package com.spark.order.purchase.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.order.intf.entity.EntityFather;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * <p>
 * 采购商品
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-4-1
 */
public class PurchaseGoods2 extends EntityFather {
	protected GUID goodsItemId;// 商品规格GUID guid
	protected String goodsCode;// 商品条码 nvarchar 20
	protected String goodsNo;
	protected String goodsName;// 商品名称 nvarchar 50
	protected String goodsProperties;// 商品属性 nvarchar 100
	protected int countDecimal;// 商品小数位数 int
	protected String goodsUnit;// 单位 nvarchar 12
	protected double purchaseCount;// 采购数量 numeric 17 5
	protected GUID sourceId;// 对应仓库编号 guid
	protected String sourceName;// 仓库名称 nvarchar 50
	protected GUID partnerId;// 供应商GUID guid
	protected String partnerShortName;// 供应商名称 nvarchar 20
	protected String partnerName;// 供应商全称 nvarchar 100
	protected String partnerNamePY;// 供应商全称拼音 nvarchar 50
	protected String partnerFax;// 供应商传真 nvarchar 20
	protected GUID contactId;// 联系人GUID guid
	protected String contactName;// 联系人 nvarchar 40
	protected String contactTel;// 联系人电话 nvarchar 20
	protected String contactPhone;// 联系人手机 nvarchar 20
	protected double price_purchase = -1;// 采购单价 numeric 17 2
	protected double price_lastPurchase;// 上次采购单价 numeric 17 2
	protected String purchaseCause;// 采购原因 nvarchar 1000

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
