package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_InventoryLog extends ORMDeclarator<com.spark.psi.inventory.internal.entity.InventoryLogEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_stockId;
	public final QueryColumnDefine c_name;
	public final QueryColumnDefine c_properties;
	public final QueryColumnDefine c_unit;
	public final QueryColumnDefine c_categoryId;
	public final QueryColumnDefine c_code;
	public final QueryColumnDefine c_stockNo;
	public final QueryColumnDefine c_orderId;
	public final QueryColumnDefine c_orderNo;
	public final QueryColumnDefine c_logType;
	public final QueryColumnDefine c_instoCount;
	public final QueryColumnDefine c_instoAmount;
	public final QueryColumnDefine c_scale;
	public final QueryColumnDefine c_outstoCount;
	public final QueryColumnDefine c_outstoAmount;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_createdDate;
	public final QueryColumnDefine c_inventoryType;

	public Orm_InventoryLog() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_storeId = this.orm.getColumns().get(1);
		this.c_stockId = this.orm.getColumns().get(2);
		this.c_name = this.orm.getColumns().get(3);
		this.c_properties = this.orm.getColumns().get(4);
		this.c_unit = this.orm.getColumns().get(5);
		this.c_categoryId = this.orm.getColumns().get(6);
		this.c_code = this.orm.getColumns().get(7);
		this.c_stockNo = this.orm.getColumns().get(8);
		this.c_orderId = this.orm.getColumns().get(9);
		this.c_orderNo = this.orm.getColumns().get(10);
		this.c_logType = this.orm.getColumns().get(11);
		this.c_instoCount = this.orm.getColumns().get(12);
		this.c_instoAmount = this.orm.getColumns().get(13);
		this.c_scale = this.orm.getColumns().get(14);
		this.c_outstoCount = this.orm.getColumns().get(15);
		this.c_outstoAmount = this.orm.getColumns().get(16);
		this.c_createPerson = this.orm.getColumns().get(17);
		this.c_createdDate = this.orm.getColumns().get(18);
		this.c_inventoryType = this.orm.getColumns().get(19);
	}
}
