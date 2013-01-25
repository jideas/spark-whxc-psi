package com.spark.psi.report.storage.monitor;

public class ORMSalesTargetByPK extends ORMSalesTarget {

	public final static String NAME = "ORM_SALE_TARGET2";

	public ORMSalesTargetByPK(TB_SA_SALE_TARGET tbTarget) {
		super(tbTarget, NAME);
		this.orm.setCondition(tableReference
				.expOf(tbTarget.f_tenantId)
				.xEq(this.orm.newArgument(tbTarget.f_tenantId))
				.and(tableReference.expOf(tbTarget.f_objectId).xEq(
						this.orm.newArgument(tbTarget.f_objectId)))
				.and(tableReference.expOf(tbTarget.f_dataType).xEq(
						this.orm.newArgument(tbTarget.f_dataType)))
				.and(tableReference.expOf(tbTarget.f_year).xEq(
						this.orm.newArgument(tbTarget.f_year))));
	}

}
