package com.spark.psi.storage.account.invoice;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_FINANCE_INVOICE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_FINANCE_INVOICE";

	public final TableFieldDefine f_cusproGuid;
	public final TableFieldDefine f_cusproName;
	public final TableFieldDefine f_cusproNamePY;
	public final TableFieldDefine f_cusproFullName;
	public final TableFieldDefine f_cusproFullNamePY;
	public final TableFieldDefine f_invoType;
	public final TableFieldDefine f_invoCode;
	public final TableFieldDefine f_invoAmount;
	public final TableFieldDefine f_invoPerson;
	public final TableFieldDefine f_invoPersonName;
	public final TableFieldDefine f_invoDate;
	public final TableFieldDefine f_isInvalided;
	public final TableFieldDefine f_invalidReason;
	public final TableFieldDefine f_invalidPerson;
	public final TableFieldDefine f_invalidDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_cusDeptGuid;

	public static final String FN_cusproGuid ="cusproGuid";
	public static final String FN_cusproName ="cusproName";
	public static final String FN_cusproNamePY ="cusproNamePY";
	public static final String FN_cusproFullName ="cusproFullName";
	public static final String FN_cusproFullNamePY ="cusproFullNamePY";
	public static final String FN_invoType ="invoType";
	public static final String FN_invoCode ="invoCode";
	public static final String FN_invoAmount ="invoAmount";
	public static final String FN_invoPerson ="invoPerson";
	public static final String FN_invoPersonName ="invoPersonName";
	public static final String FN_invoDate ="invoDate";
	public static final String FN_isInvalided ="isInvalided";
	public static final String FN_invalidReason ="invalidReason";
	public static final String FN_invalidPerson ="invalidPerson";
	public static final String FN_invalidDate ="invalidDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_cusDeptGuid ="cusDeptGuid";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_FINANCE_INVOICE() {
		super(TABLE_NAME);
		this.table.setTitle("开票登记信息表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_cusproGuid = field = this.table.newField(FN_cusproGuid, TypeFactory.GUID);
		field.setTitle("客户GUID");
		this.f_cusproName = field = this.table.newField(FN_cusproName, TypeFactory.NVARCHAR(20));
		field.setTitle("客户简称");
		this.f_cusproNamePY = field = this.table.newField(FN_cusproNamePY, TypeFactory.NVARCHAR(10));
		field.setTitle("客户简称拼音");
		this.f_cusproFullName = field = this.table.newField(FN_cusproFullName, TypeFactory.NVARCHAR(100));
		field.setTitle("客户全称");
		this.f_cusproFullNamePY = field = this.table.newField(FN_cusproFullNamePY, TypeFactory.NVARCHAR(50));
		field.setTitle("客户全称拼音");
		this.f_invoType = field = this.table.newField(FN_invoType, TypeFactory.CHAR(2));
		field.setTitle("发票类型");
		this.f_invoCode = field = this.table.newField(FN_invoCode, TypeFactory.NVARCHAR(200));
		field.setTitle("发票号");
		this.f_invoAmount = field = this.table.newField(FN_invoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("开票金额");
		this.f_invoPerson = field = this.table.newField(FN_invoPerson, TypeFactory.GUID);
		field.setTitle("开票人GUID");
		this.f_invoPersonName = field = this.table.newField(FN_invoPersonName, TypeFactory.NVARCHAR(40));
		field.setTitle("开票人");
		this.f_invoDate = field = this.table.newField(FN_invoDate, TypeFactory.DATE);
		field.setTitle("开票日期");
		this.f_isInvalided = field = this.table.newField(FN_isInvalided, TypeFactory.BOOLEAN);
		field.setTitle("是否已作废");
		this.f_invalidReason = field = this.table.newField(FN_invalidReason, TypeFactory.NVARCHAR(200));
		field.setTitle("作废原因");
		this.f_invalidPerson = field = this.table.newField(FN_invalidPerson, TypeFactory.NVARCHAR(40));
		field.setTitle("作废人");
		this.f_invalidDate = field = this.table.newField(FN_invalidDate, TypeFactory.DATE);
		field.setTitle("作废日期");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(40));
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("所属部门");
		this.f_cusDeptGuid = field = this.table.newField(FN_cusDeptGuid, TypeFactory.GUID);
		field.setTitle("业务负责人部门");
	}

}
