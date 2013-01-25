package com.spark.psi.report.queue.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ReportQueue extends ORMDeclarator<com.spark.psi.report.entity.ReportQueue> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_attributeXml;
	public final QueryColumnDefine c_currTime;
	public final QueryColumnDefine c_eventClass;
	public final QueryColumnDefine c_tenantId;
	public final QueryColumnDefine c_userId;

	public Orm_ReportQueue() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_attributeXml = this.orm.getColumns().get(1);
		this.c_currTime = this.orm.getColumns().get(2);
		this.c_eventClass = this.orm.getColumns().get(3);
		this.c_tenantId = this.orm.getColumns().get(4);
		this.c_userId = this.orm.getColumns().get(5);
	}
}
