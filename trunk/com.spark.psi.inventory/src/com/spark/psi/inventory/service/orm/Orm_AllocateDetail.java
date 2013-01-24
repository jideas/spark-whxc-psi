package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_AllocateDetail extends ORMDeclarator<com.spark.psi.inventory.internal.entity.AllocateInventoryItem> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_ableCount;
	public final QueryColumnDefine c_allocateCount;
	public final QueryColumnDefine c_allocateId;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_stockCode;
	public final QueryColumnDefine c_stockId;
	public final QueryColumnDefine c_stockName;
	public final QueryColumnDefine c_stockNo;
	public final QueryColumnDefine c_stockSpec;
	public final QueryColumnDefine c_stockScale;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_unit;

	public Orm_AllocateDetail() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_ableCount = this.orm.getColumns().get(0);
		this.c_allocateCount = this.orm.getColumns().get(1);
		this.c_allocateId = this.orm.getColumns().get(2);
		this.c_createDate = this.orm.getColumns().get(3);
		this.c_creatorId = this.orm.getColumns().get(4);
		this.c_stockCode = this.orm.getColumns().get(5);
		this.c_stockId = this.orm.getColumns().get(6);
		this.c_stockName = this.orm.getColumns().get(7);
		this.c_stockNo = this.orm.getColumns().get(8);
		this.c_stockSpec = this.orm.getColumns().get(9);
		this.c_stockScale = this.orm.getColumns().get(10);
		this.c_id = this.orm.getColumns().get(11);
		this.c_unit = this.orm.getColumns().get(12);
	}
}
