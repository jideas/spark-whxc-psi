package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ReportLossItemDet extends ORMDeclarator<com.spark.psi.inventory.internal.entity.ReportLossItemDet> {

	public final ArgumentDefine arg_reportLossItemId;

	public final QueryColumnDefine c_count;
	public final QueryColumnDefine c_produceDate;
	public final QueryColumnDefine c_reportLossItemId;
	public final QueryColumnDefine c_sheetId;
	public final QueryColumnDefine c_shelfId;
	public final QueryColumnDefine c_shelfNo;
	public final QueryColumnDefine c_stockId;
	public final QueryColumnDefine c_tiersNo;
	public final QueryColumnDefine c_id;

	public Orm_ReportLossItemDet() {
		this.arg_reportLossItemId = this.orm.getArguments().get(0);
		this.c_count = this.orm.getColumns().get(0);
		this.c_produceDate = this.orm.getColumns().get(1);
		this.c_reportLossItemId = this.orm.getColumns().get(2);
		this.c_sheetId = this.orm.getColumns().get(3);
		this.c_shelfId = this.orm.getColumns().get(4);
		this.c_shelfNo = this.orm.getColumns().get(5);
		this.c_stockId = this.orm.getColumns().get(6);
		this.c_tiersNo = this.orm.getColumns().get(7);
		this.c_id = this.orm.getColumns().get(8);
	}
}
