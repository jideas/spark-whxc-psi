package com.spark.b2c.storage.joint;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Materials_Joint_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Materials_Joint_Log";

	public final TableFieldDefine f_supplierId;
	public final TableFieldDefine f_materialId;
	public final TableFieldDefine f_materialName;
	public final TableFieldDefine f_materialCode;
	public final TableFieldDefine f_materialNo;
	public final TableFieldDefine f_materialUnit;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_percentage;

	public static final String FN_supplierId ="supplierId";
	public static final String FN_materialId ="materialId";
	public static final String FN_materialName ="materialName";
	public static final String FN_materialCode ="materialCode";
	public static final String FN_materialNo ="materialNo";
	public static final String FN_materialUnit ="materialUnit";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_percentage ="percentage";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Materials_Joint_Log() {
		super(TABLE_NAME);
		this.table.setTitle("联营商品信息记录");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_supplierId = field = this.table.newField(FN_supplierId, TypeFactory.GUID);
		field.setTitle("供应商Id");
		this.f_materialId = this.table.newField(FN_materialId, TypeFactory.GUID);
		this.f_materialName = this.table.newField(FN_materialName, TypeFactory.NVARCHAR(50));
		this.f_materialCode = this.table.newField(FN_materialCode, TypeFactory.NVARCHAR(20));
		this.f_materialNo = this.table.newField(FN_materialNo, TypeFactory.NVARCHAR(20));
		this.f_materialUnit = this.table.newField(FN_materialUnit, TypeFactory.NVARCHAR(10));
		this.f_beginDate = this.table.newField(FN_beginDate, TypeFactory.DATE);
		this.f_endDate = this.table.newField(FN_endDate, TypeFactory.DATE);
		this.f_percentage = this.table.newField(FN_percentage, TypeFactory.NUMERIC(17,4));
	}

}
