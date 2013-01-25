package com.spark.psi.order.storage.purchase;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_PurchaseGoodsDirect extends ORMDeclarator<com.spark.order.purchase.intf.PurchaseGoodsDirect2> {

	public final ArgumentDefine arg_tenantsId;

	public final QueryColumnDefine c_sourceSaleItemId;
	public final QueryColumnDefine c_sourceSaleId;
	public final QueryColumnDefine c_contactId;
	public final QueryColumnDefine c_contactName;
	public final QueryColumnDefine c_contactPhone;
	public final QueryColumnDefine c_contactTel;
	public final QueryColumnDefine c_countDecimal;
	public final QueryColumnDefine c_deleteFlag;
	public final QueryColumnDefine c_goodsCode;
	public final QueryColumnDefine c_goodsItemId;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsProperties;
	public final QueryColumnDefine c_goodsUnit;
	public final QueryColumnDefine c_partnerFax;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_partnerNamePY;
	public final QueryColumnDefine c_partnerShortName;
	public final QueryColumnDefine c_price_lastPurchase;
	public final QueryColumnDefine c_price_purchase;
	public final QueryColumnDefine c_purchaseCause;
	public final QueryColumnDefine c_purchaseCount;
	public final QueryColumnDefine c_sourceId;
	public final QueryColumnDefine c_sourceName;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_tenantsId;

	public Orm_PurchaseGoodsDirect() {
		this.arg_tenantsId = this.orm.getArguments().get(0);
		this.c_sourceSaleItemId = this.orm.getColumns().get(0);
		this.c_sourceSaleId = this.orm.getColumns().get(1);
		this.c_contactId = this.orm.getColumns().get(2);
		this.c_contactName = this.orm.getColumns().get(3);
		this.c_contactPhone = this.orm.getColumns().get(4);
		this.c_contactTel = this.orm.getColumns().get(5);
		this.c_countDecimal = this.orm.getColumns().get(6);
		this.c_deleteFlag = this.orm.getColumns().get(7);
		this.c_goodsCode = this.orm.getColumns().get(8);
		this.c_goodsItemId = this.orm.getColumns().get(9);
		this.c_goodsName = this.orm.getColumns().get(10);
		this.c_goodsProperties = this.orm.getColumns().get(11);
		this.c_goodsUnit = this.orm.getColumns().get(12);
		this.c_partnerFax = this.orm.getColumns().get(13);
		this.c_partnerId = this.orm.getColumns().get(14);
		this.c_partnerName = this.orm.getColumns().get(15);
		this.c_partnerNamePY = this.orm.getColumns().get(16);
		this.c_partnerShortName = this.orm.getColumns().get(17);
		this.c_price_lastPurchase = this.orm.getColumns().get(18);
		this.c_price_purchase = this.orm.getColumns().get(19);
		this.c_purchaseCause = this.orm.getColumns().get(20);
		this.c_purchaseCount = this.orm.getColumns().get(21);
		this.c_sourceId = this.orm.getColumns().get(22);
		this.c_sourceName = this.orm.getColumns().get(23);
		this.c_createDate = this.orm.getColumns().get(24);
		this.c_creator = this.orm.getColumns().get(25);
		this.c_creatorId = this.orm.getColumns().get(26);
		this.c_recid = this.orm.getColumns().get(27);
		this.c_tenantsId = this.orm.getColumns().get(28);
	}
}
