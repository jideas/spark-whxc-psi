package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Inventory extends ORMDeclarator<com.spark.psi.inventory.service.resource.InventoryEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_stockId;
	public final QueryColumnDefine c_initCount;
	public final QueryColumnDefine c_initAmount;
	public final QueryColumnDefine c_initCost;
	public final QueryColumnDefine c_name;
	public final QueryColumnDefine c_code;
	public final QueryColumnDefine c_stockNo;
	public final QueryColumnDefine c_count;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_unit;
	public final QueryColumnDefine c_spec;
	public final QueryColumnDefine c_onWayCount;
	public final QueryColumnDefine c_toDeliverCount;
	public final QueryColumnDefine c_upperLimitCount;
	public final QueryColumnDefine c_lowerLimitCount;
	public final QueryColumnDefine c_upperLimitAmount;
	public final QueryColumnDefine c_inventoryType;
	public final QueryColumnDefine c_lockedCount;
	public final QueryColumnDefine c_properties;

	public Orm_Inventory() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_storeId = this.orm.getColumns().get(1);
		this.c_stockId = this.orm.getColumns().get(2);
		this.c_initCount = this.orm.getColumns().get(3);
		this.c_initAmount = this.orm.getColumns().get(4);
		this.c_initCost = this.orm.getColumns().get(5);
		this.c_name = this.orm.getColumns().get(6);
		this.c_code = this.orm.getColumns().get(7);
		this.c_stockNo = this.orm.getColumns().get(8);
		this.c_count = this.orm.getColumns().get(9);
		this.c_amount = this.orm.getColumns().get(10);
		this.c_unit = this.orm.getColumns().get(11);
		this.c_spec = this.orm.getColumns().get(12);
		this.c_onWayCount = this.orm.getColumns().get(13);
		this.c_toDeliverCount = this.orm.getColumns().get(14);
		this.c_upperLimitCount = this.orm.getColumns().get(15);
		this.c_lowerLimitCount = this.orm.getColumns().get(16);
		this.c_upperLimitAmount = this.orm.getColumns().get(17);
		this.c_inventoryType = this.orm.getColumns().get(18);
		this.c_lockedCount = this.orm.getColumns().get(19);
		this.c_properties = this.orm.getColumns().get(20);
	}
}
