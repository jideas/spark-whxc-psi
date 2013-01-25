package com.spark.psi.report.storage.monitor;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public final class TB_SA_SALE_TARGET extends TableDeclarator {

	public static final String TABLE_NAME = "SA_SALE_TARGET";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_objectId;
	public final TableFieldDefine f_dataType;
	public final TableFieldDefine f_year;
	public final TableFieldDefine f_value01;
	public final TableFieldDefine f_value02;
	public final TableFieldDefine f_value03;
	public final TableFieldDefine f_value04;
	public final TableFieldDefine f_value05;
	public final TableFieldDefine f_value06;
	public final TableFieldDefine f_value07;
	public final TableFieldDefine f_value08;
	public final TableFieldDefine f_value09;
	public final TableFieldDefine f_value10;
	public final TableFieldDefine f_value11;
	public final TableFieldDefine f_value12;

	private TB_SA_SALE_TARGET() {
		super(TABLE_NAME);
		this.table.setTitle("销售目标设置表");
		this.f_tenantId = this.table.newPrimaryField("tenantId", TypeFactory.GUID);
		this.f_objectId = this.table.newPrimaryField("objectId", TypeFactory.GUID);
		this.f_dataType = this.table.newPrimaryField("dataType", TypeFactory.NVARCHAR(2));
		this.f_year = this.table.newPrimaryField("year", TypeFactory.NVARCHAR(4));
		this.f_value01 = this.table.newField("value01", TypeFactory.NUMERIC(17, 2));
		this.f_value02 = this.table.newField("value02", TypeFactory.NUMERIC(17, 2));
		this.f_value03 = this.table.newField("value03", TypeFactory.NUMERIC(17, 2));
		this.f_value04 = this.table.newField("value04", TypeFactory.NUMERIC(17, 2));
		this.f_value05 = this.table.newField("value05", TypeFactory.NUMERIC(17, 2));
		this.f_value06 = this.table.newField("value06", TypeFactory.NUMERIC(17, 2));
		this.f_value07 = this.table.newField("value07", TypeFactory.NUMERIC(17, 2));
		this.f_value08 = this.table.newField("value08", TypeFactory.NUMERIC(17, 2));
		this.f_value09 = this.table.newField("value09", TypeFactory.NUMERIC(17, 2));
		this.f_value10 = this.table.newField("value10", TypeFactory.NUMERIC(17, 2));
		this.f_value11 = this.table.newField("value11", TypeFactory.NUMERIC(17, 2));
		this.f_value12 = this.table.newField("value12", TypeFactory.NUMERIC(17, 2));
	}

}
