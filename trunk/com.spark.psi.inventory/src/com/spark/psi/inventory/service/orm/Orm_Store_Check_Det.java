package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Store_Check_Det extends ORMDeclarator<com.spark.psi.inventory.internal.entity.CheckInventoryItem> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_carryCount;
	public final QueryColumnDefine c_checkOrdGuid;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_goodsAttr;
	public final QueryColumnDefine c_goodsGuid;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsItemNo;
	public final QueryColumnDefine c_goodsItemCode;
	public final QueryColumnDefine c_goodsScale;
	public final QueryColumnDefine c_goodsTypeGuid;
	public final QueryColumnDefine c_newGoods;
	public final QueryColumnDefine c_realCount;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_tenantsGuid;
	public final QueryColumnDefine c_unit;

	public Orm_Store_Check_Det() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_carryCount = this.orm.getColumns().get(0);
		this.c_checkOrdGuid = this.orm.getColumns().get(1);
		this.c_createDate = this.orm.getColumns().get(2);
		this.c_createPerson = this.orm.getColumns().get(3);
		this.c_goodsAttr = this.orm.getColumns().get(4);
		this.c_goodsGuid = this.orm.getColumns().get(5);
		this.c_goodsName = this.orm.getColumns().get(6);
		this.c_goodsItemNo = this.orm.getColumns().get(7);
		this.c_goodsItemCode = this.orm.getColumns().get(8);
		this.c_goodsScale = this.orm.getColumns().get(9);
		this.c_goodsTypeGuid = this.orm.getColumns().get(10);
		this.c_newGoods = this.orm.getColumns().get(11);
		this.c_realCount = this.orm.getColumns().get(12);
		this.c_recid = this.orm.getColumns().get(13);
		this.c_remark = this.orm.getColumns().get(14);
		this.c_tenantsGuid = this.orm.getColumns().get(15);
		this.c_unit = this.orm.getColumns().get(16);
	}
}
