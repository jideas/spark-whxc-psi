package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_CFG_EXAM extends TableDeclarator {

	public static final String TABLE_NAME ="SA_CFG_EXAM";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_modeid;
	public final TableFieldDefine f_isOpenExam;
	public final TableFieldDefine f_maxAmount;
	public final TableFieldDefine f_updatePerson;
	public final TableFieldDefine f_updateDate;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_modeid ="modeid";
	public static final String FN_isOpenExam ="isOpenExam";
	public static final String FN_maxAmount ="maxAmount";
	public static final String FN_updatePerson ="updatePerson";
	public static final String FN_updateDate ="updateDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_CFG_EXAM() {
		super(TABLE_NAME);
		this.table.setTitle("审核流程配置表");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newPrimaryField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号1");
		field.setKeepValid(true);
		this.f_modeid = field = this.table.newPrimaryField(FN_modeid, TypeFactory.CHAR(2));
		field.setTitle("模块id");
		field.setKeepValid(true);
		this.f_isOpenExam = field = this.table.newField(FN_isOpenExam, TypeFactory.BOOLEAN);
		field.setTitle("是否开启审核");
		this.f_maxAmount = field = this.table.newField(FN_maxAmount, TypeFactory.DOUBLE);
		field.setTitle("金额上限");
		this.f_updatePerson = field = this.table.newField(FN_updatePerson, TypeFactory.NVARCHAR(10));
		field.setTitle("最后修改人");
		this.f_updateDate = field = this.table.newField(FN_updateDate, TypeFactory.DATE);
		field.setTitle("修改时间");
	}

}
