package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_EXAM_LIST extends TableDeclarator {

	public static final String TABLE_NAME ="SA_EXAM_LIST";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_userGuid;
	public final TableFieldDefine f_examDate;
	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_busType;
	public final TableFieldDefine f_busTypeName;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_userGuid ="userGuid";
	public static final String FN_examDate ="examDate";
	public static final String FN_billsNo ="billsNo";
	public static final String FN_busType ="busType";
	public static final String FN_busTypeName ="busTypeName";
	public static final String FN_amount ="amount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_EXAM_LIST() {
		super(TABLE_NAME);
		this.table.setTitle("审批记录  ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_userGuid = field = this.table.newField(FN_userGuid, TypeFactory.GUID);
		field.setTitle("用户编号");
		this.f_examDate = field = this.table.newField(FN_examDate, TypeFactory.DATE);
		field.setTitle("审批日期");
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("单据编号");
		this.f_busType = field = this.table.newField(FN_busType, TypeFactory.NVARCHAR(20));
		field.setTitle("单据类型");
		this.f_busTypeName = field = this.table.newField(FN_busTypeName, TypeFactory.NVARCHAR(20));
		field.setTitle("单据类型名称");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.DOUBLE);
		field.setTitle("金额");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.NVARCHAR(15));
		field.setTitle("单据状态");
		this.table.newIndex("INDEX_EXAM_LIST_",f_tenantsGuid,f_userGuid,f_examDate);
	}

}
