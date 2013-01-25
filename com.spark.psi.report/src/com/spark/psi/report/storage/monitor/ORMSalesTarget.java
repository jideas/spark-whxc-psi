package com.spark.psi.report.storage.monitor;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.report.entity.SalesTargetEntity;

public abstract class ORMSalesTarget extends ORMDeclarator<SalesTargetEntity> {

	protected QuRelationRefDeclare tableReference;

	public ORMSalesTarget(TB_SA_SALE_TARGET tbTarget, String name) {
		super(name);
		tableReference = this.orm.newReference(tbTarget);
		this.orm.newColumn(tbTarget.f_RECID,"id");
		this.orm.newColumn(tbTarget.f_tenantId, "tenantId");
		this.orm.newColumn(tbTarget.f_objectId, "objectId");
		this.orm.newColumn(tbTarget.f_dataType, "dataType");
		this.orm.newColumn(tbTarget.f_year, "year");
		this.orm.newColumn(tbTarget.f_value01, "value01");
		this.orm.newColumn(tbTarget.f_value02, "value02");
		this.orm.newColumn(tbTarget.f_value03, "value03");
		this.orm.newColumn(tbTarget.f_value04, "value04");
		this.orm.newColumn(tbTarget.f_value05, "value05");
		this.orm.newColumn(tbTarget.f_value06, "value06");
		this.orm.newColumn(tbTarget.f_value07, "value07");
		this.orm.newColumn(tbTarget.f_value08, "value08");
		this.orm.newColumn(tbTarget.f_value09, "value09");
		this.orm.newColumn(tbTarget.f_value10, "value10");
		this.orm.newColumn(tbTarget.f_value11, "value11");
		this.orm.newColumn(tbTarget.f_value12, "value12");
	}

}
