package com.spark.psi.order.storage.sales;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_SalesReturnItem2 extends ORMDeclarator<com.spark.order.sales2.SalesReturnItem2> {

	public final ArgumentDefine arg_tenantsId;
	public final ArgumentDefine arg_orderId;

	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_storeName;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_countDecimal;
	public final QueryColumnDefine c_goodsCode;
	public final QueryColumnDefine c_goodsItemId;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsProperties;
	public final QueryColumnDefine c_goodsUnit;
	public final QueryColumnDefine c_num;
	public final QueryColumnDefine c_orderId;
	public final QueryColumnDefine c_price;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_tenantsId;

	public Orm_SalesReturnItem2() {
		this.arg_tenantsId = this.orm.getArguments().get(0);
		this.arg_orderId = this.orm.getArguments().get(1);
		this.c_storeId = this.orm.getColumns().get(0);
		this.c_storeName = this.orm.getColumns().get(1);
		this.c_amount = this.orm.getColumns().get(2);
		this.c_countDecimal = this.orm.getColumns().get(3);
		this.c_goodsCode = this.orm.getColumns().get(4);
		this.c_goodsItemId = this.orm.getColumns().get(5);
		this.c_goodsName = this.orm.getColumns().get(6);
		this.c_goodsProperties = this.orm.getColumns().get(7);
		this.c_goodsUnit = this.orm.getColumns().get(8);
		this.c_num = this.orm.getColumns().get(9);
		this.c_orderId = this.orm.getColumns().get(10);
		this.c_price = this.orm.getColumns().get(11);
		this.c_createDate = this.orm.getColumns().get(12);
		this.c_creator = this.orm.getColumns().get(13);
		this.c_creatorId = this.orm.getColumns().get(14);
		this.c_recid = this.orm.getColumns().get(15);
		this.c_tenantsId = this.orm.getColumns().get(16);
	}
}
