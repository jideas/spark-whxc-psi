package com.spark.psi.report.storage.monitor;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SALE_MONITOR extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALE_MONITOR";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_monitorYear;
	public final TableFieldDefine f_monitorType;
	public final TableFieldDefine f_monitorItem;
	public final TableFieldDefine f_monitorObjGuid;
	public final TableFieldDefine f_monitorObjType;
	public final TableFieldDefine f_janAmount;
	public final TableFieldDefine f_febAmount;
	public final TableFieldDefine f_marAmount;
	public final TableFieldDefine f_aprilAmount;
	public final TableFieldDefine f_mayAmount;
	public final TableFieldDefine f_junAmount;
	public final TableFieldDefine f_julyAmount;
	public final TableFieldDefine f_augAmount;
	public final TableFieldDefine f_sepAmount;
	public final TableFieldDefine f_octAmount;
	public final TableFieldDefine f_novAmount;
	public final TableFieldDefine f_decAmount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_field_1;
	public final TableFieldDefine f_field_2;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_monitorYear ="monitorYear";
	public static final String FN_monitorType ="monitorType";
	public static final String FN_monitorItem ="monitorItem";
	public static final String FN_monitorObjGuid ="monitorObjGuid";
	public static final String FN_monitorObjType ="monitorObjType";
	public static final String FN_janAmount ="janAmount";
	public static final String FN_febAmount ="febAmount";
	public static final String FN_marAmount ="marAmount";
	public static final String FN_aprilAmount ="aprilAmount";
	public static final String FN_mayAmount ="mayAmount";
	public static final String FN_junAmount ="junAmount";
	public static final String FN_julyAmount ="julyAmount";
	public static final String FN_augAmount ="augAmount";
	public static final String FN_sepAmount ="sepAmount";
	public static final String FN_octAmount ="octAmount";
	public static final String FN_novAmount ="novAmount";
	public static final String FN_decAmount ="decAmount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_field_1 ="field_1";
	public static final String FN_field_2 ="field_2";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_SALE_MONITOR() {
		super(TABLE_NAME);
		this.table.setTitle("销售监控信息表");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("租户编号");
		field.setKeepValid(true);
		this.f_monitorYear = field = this.table.newField(FN_monitorYear, TypeFactory.NVARCHAR(4));
		field.setTitle("监控年度");
		this.f_monitorType = field = this.table.newField(FN_monitorType, TypeFactory.CHAR(2));
		field.setTitle("监控类别");
		this.f_monitorItem = field = this.table.newField(FN_monitorItem, TypeFactory.CHAR(2));
		field.setTitle("监控项");
		this.f_monitorObjGuid = field = this.table.newField(FN_monitorObjGuid, TypeFactory.GUID);
		field.setTitle("监控对象GUID");
		this.f_monitorObjType = field = this.table.newField(FN_monitorObjType, TypeFactory.CHAR(2));
		field.setTitle("监控对象类型");
		this.f_janAmount = field = this.table.newField(FN_janAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("一月");
		this.f_febAmount = field = this.table.newField(FN_febAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("二月");
		this.f_marAmount = field = this.table.newField(FN_marAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("三月");
		this.f_aprilAmount = field = this.table.newField(FN_aprilAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("四月");
		this.f_mayAmount = field = this.table.newField(FN_mayAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("五月");
		this.f_junAmount = field = this.table.newField(FN_junAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("六月");
		this.f_julyAmount = field = this.table.newField(FN_julyAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("七月");
		this.f_augAmount = field = this.table.newField(FN_augAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("八月");
		this.f_sepAmount = field = this.table.newField(FN_sepAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("九月");
		this.f_octAmount = field = this.table.newField(FN_octAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("十月");
		this.f_novAmount = field = this.table.newField(FN_novAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("十一月");
		this.f_decAmount = field = this.table.newField(FN_decAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("十二月");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.GUID);
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_field_1 = this.table.newField(FN_field_1, TypeFactory.VARCHAR(10));
		this.f_field_2 = this.table.newField(FN_field_2, TypeFactory.VARCHAR(10));
		this.table.newIndex("INDEX_SALE_MONITOR",f_tenantId,f_monitorType,f_monitorYear);
	}

}
