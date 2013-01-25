package com.spark.psi.report.storage.monitor;

public class ORMSalesTargetByYear extends ORMSalesTarget {

	public final static String NAME = "ORM_SALE_TARGET1";

	public ORMSalesTargetByYear(TB_SA_SALE_TARGET tbTarget) {
		super(tbTarget, NAME);
		this.orm.setCondition(tableReference
				.expOf(tbTarget.f_tenantId)
				.xEq(this.orm.newArgument(tbTarget.f_tenantId))
				.and(tableReference.expOf(tbTarget.f_year).xEq(
						this.orm.newArgument(tbTarget.f_year))));
	}

}
