package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_AllocateItemDet extends ORMDeclarator<com.spark.psi.inventory.internal.entity.AllocateItemDet> {

	public final ArgumentDefine arg_allocateItemId;

	public final QueryColumnDefine c_allocateItemId;
	public final QueryColumnDefine c_count;
	public final QueryColumnDefine c_produceDate;
	public final QueryColumnDefine c_sheetId;
	public final QueryColumnDefine c_shelfId;
	public final QueryColumnDefine c_shelfNo;
	public final QueryColumnDefine c_stockId;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_stockName;
	public final QueryColumnDefine c_tiersNo;
	public final QueryColumnDefine c_id;

	public Orm_AllocateItemDet() {
		this.arg_allocateItemId = this.orm.getArguments().get(0);
		this.c_allocateItemId = this.orm.getColumns().get(0);
		this.c_count = this.orm.getColumns().get(1);
		this.c_produceDate = this.orm.getColumns().get(2);
		this.c_sheetId = this.orm.getColumns().get(3);
		this.c_shelfId = this.orm.getColumns().get(4);
		this.c_shelfNo = this.orm.getColumns().get(5);
		this.c_stockId = this.orm.getColumns().get(6);
		this.c_storeId = this.orm.getColumns().get(7);
		this.c_stockName = this.orm.getColumns().get(8);
		this.c_tiersNo = this.orm.getColumns().get(9);
		this.c_id = this.orm.getColumns().get(10);
	}
}
