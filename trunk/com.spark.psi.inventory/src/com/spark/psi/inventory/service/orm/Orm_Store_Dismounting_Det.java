package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Store_Dismounting_Det extends ORMDeclarator<com.spark.psi.inventory.internal.entity.DismountingItem> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_dismFlag;
	public final QueryColumnDefine c_dismGuid;
	public final QueryColumnDefine c_dismCount;
	public final QueryColumnDefine c_storeCost;
	public final QueryColumnDefine c_goodsAttr;
	public final QueryColumnDefine c_goodsGuid;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsNo;
	public final QueryColumnDefine c_goodsScale;
	public final QueryColumnDefine c_tenantsGuid;
	public final QueryColumnDefine c_money;
	public final QueryColumnDefine c_unit;

	public Orm_Store_Dismounting_Det() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_createDate = this.orm.getColumns().get(1);
		this.c_createPerson = this.orm.getColumns().get(2);
		this.c_dismFlag = this.orm.getColumns().get(3);
		this.c_dismGuid = this.orm.getColumns().get(4);
		this.c_dismCount = this.orm.getColumns().get(5);
		this.c_storeCost = this.orm.getColumns().get(6);
		this.c_goodsAttr = this.orm.getColumns().get(7);
		this.c_goodsGuid = this.orm.getColumns().get(8);
		this.c_goodsName = this.orm.getColumns().get(9);
		this.c_goodsNo = this.orm.getColumns().get(10);
		this.c_goodsScale = this.orm.getColumns().get(11);
		this.c_tenantsGuid = this.orm.getColumns().get(12);
		this.c_money = this.orm.getColumns().get(13);
		this.c_unit = this.orm.getColumns().get(14);
	}
}
