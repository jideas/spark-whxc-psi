package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ReportLossItem extends ORMDeclarator<com.spark.psi.inventory.internal.entity.ReportLossItem> {

	public final ArgumentDefine arg_sheetId;

	public final QueryColumnDefine c_goodsCode;
	public final QueryColumnDefine c_goodsId;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsNumber;
	public final QueryColumnDefine c_goodsSpec;
	public final QueryColumnDefine c_goodsUnit;
	public final QueryColumnDefine c_reportCount;
	public final QueryColumnDefine c_reportReason;
	public final QueryColumnDefine c_reportLossSheetId;
	public final QueryColumnDefine c_reportType;
	public final QueryColumnDefine c_scale;
	public final QueryColumnDefine c_id;

	public Orm_ReportLossItem() {
		this.arg_sheetId = this.orm.getArguments().get(0);
		this.c_goodsCode = this.orm.getColumns().get(0);
		this.c_goodsId = this.orm.getColumns().get(1);
		this.c_goodsName = this.orm.getColumns().get(2);
		this.c_goodsNumber = this.orm.getColumns().get(3);
		this.c_goodsSpec = this.orm.getColumns().get(4);
		this.c_goodsUnit = this.orm.getColumns().get(5);
		this.c_reportCount = this.orm.getColumns().get(6);
		this.c_reportReason = this.orm.getColumns().get(7);
		this.c_reportLossSheetId = this.orm.getColumns().get(8);
		this.c_reportType = this.orm.getColumns().get(9);
		this.c_scale = this.orm.getColumns().get(10);
		this.c_id = this.orm.getColumns().get(11);
	}
}
