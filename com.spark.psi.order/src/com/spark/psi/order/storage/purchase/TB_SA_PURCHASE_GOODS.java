package com.spark.psi.order.storage.purchase;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_PURCHASE_GOODS extends TableDeclarator {

	public static final String TABLE_NAME ="SA_PURCHASE_GOODS";

	public final TableFieldDefine f_tenantsId;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_goodsItemId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsProperties;
	public final TableFieldDefine f_countDecimal;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_purchaseCount;
	public final TableFieldDefine f_sourceId;
	public final TableFieldDefine f_sourceName;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerShortName;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_partnerNamePY;
	public final TableFieldDefine f_partnerFax;
	public final TableFieldDefine f_contactId;
	public final TableFieldDefine f_contactName;
	public final TableFieldDefine f_contactTel;
	public final TableFieldDefine f_contactPhone;
	public final TableFieldDefine f_price_purchase;
	public final TableFieldDefine f_price_lastPurchase;
	public final TableFieldDefine f_purchaseCause;

	public static final String FN_tenantsId ="tenantsId";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_goodsItemId ="goodsItemId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsProperties ="goodsProperties";
	public static final String FN_countDecimal ="countDecimal";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_purchaseCount ="purchaseCount";
	public static final String FN_sourceId ="sourceId";
	public static final String FN_sourceName ="sourceName";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerShortName ="partnerShortName";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_partnerNamePY ="partnerNamePY";
	public static final String FN_partnerFax ="partnerFax";
	public static final String FN_contactId ="contactId";
	public static final String FN_contactName ="contactName";
	public static final String FN_contactTel ="contactTel";
	public static final String FN_contactPhone ="contactPhone";
	public static final String FN_price_purchase ="price_purchase";
	public static final String FN_price_lastPurchase ="price_lastPurchase";
	public static final String FN_purchaseCause ="purchaseCause";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_PURCHASE_GOODS() {
		super(TABLE_NAME);
		this.table.setTitle("存放采购订单的商品信息");
		TableFieldDeclare field;
		this.f_tenantsId = field = this.table.newField(FN_tenantsId, TypeFactory.GUID);
		field.setTitle("租户GUID");
		field.setKeepValid(true);
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人GUID");
		field.setKeepValid(true);
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.VARCHAR(50));
		field.setTitle("创建人姓名");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_goodsItemId = field = this.table.newField(FN_goodsItemId, TypeFactory.GUID);
		field.setTitle("商品规格GUID");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(20));
		field.setTitle("商品条码");
		this.f_goodsNo = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsProperties = field = this.table.newField(FN_goodsProperties, TypeFactory.NVARCHAR(100));
		field.setTitle("商品属性");
		this.f_countDecimal = field = this.table.newField(FN_countDecimal, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(12));
		field.setTitle("单位");
		this.f_purchaseCount = field = this.table.newField(FN_purchaseCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("采购数量");
		this.f_sourceId = field = this.table.newField(FN_sourceId, TypeFactory.GUID);
		field.setTitle("对应仓库编号");
		this.f_sourceName = field = this.table.newField(FN_sourceName, TypeFactory.NVARCHAR(50));
		field.setTitle("仓库名称");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("供应商GUID");
		this.f_partnerShortName = field = this.table.newField(FN_partnerShortName, TypeFactory.NVARCHAR(20));
		field.setTitle("供应商名称");
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.NVARCHAR(100));
		field.setTitle("供应商全称");
		this.f_partnerNamePY = field = this.table.newField(FN_partnerNamePY, TypeFactory.NVARCHAR(50));
		field.setTitle("供应商全称拼音");
		this.f_partnerFax = field = this.table.newField(FN_partnerFax, TypeFactory.NVARCHAR(20));
		field.setTitle("供应商传真");
		this.f_contactId = field = this.table.newField(FN_contactId, TypeFactory.GUID);
		field.setTitle("联系人GUID");
		this.f_contactName = field = this.table.newField(FN_contactName, TypeFactory.NVARCHAR(40));
		field.setTitle("联系人");
		this.f_contactTel = field = this.table.newField(FN_contactTel, TypeFactory.NVARCHAR(20));
		field.setTitle("联系人电话");
		this.f_contactPhone = field = this.table.newField(FN_contactPhone, TypeFactory.NVARCHAR(20));
		field.setTitle("联系人手机");
		this.f_price_purchase = field = this.table.newField(FN_price_purchase, TypeFactory.NUMERIC(17,2));
		field.setTitle("采购单价");
		this.f_price_lastPurchase = field = this.table.newField(FN_price_lastPurchase, TypeFactory.NUMERIC(17,2));
		field.setTitle("上次采购单价");
		this.f_purchaseCause = field = this.table.newField(FN_purchaseCause, TypeFactory.NVARCHAR(1000));
		field.setTitle("采购原因");
		this.table.newIndex("SA_PURCHASE_GOODS_1",f_tenantsId,f_creatorId);
	}

}
