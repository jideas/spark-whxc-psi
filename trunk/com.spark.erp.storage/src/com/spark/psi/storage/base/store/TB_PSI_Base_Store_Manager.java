package com.spark.psi.storage.base.store;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Store_Manager extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Store_Manager";

	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_employeeType;
	public final TableFieldDefine f_employeeGuid;

	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_employeeType ="employeeType";
	public static final String FN_employeeGuid ="employeeGuid";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_Store_Manager() {
		super(TABLE_NAME);
		this.table.setTitle("库管员映射");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("仓库GUID");
		this.f_employeeType = field = this.table.newField(FN_employeeType, TypeFactory.CHAR(2));
		field.setTitle("员工类型");
		this.f_employeeGuid = field = this.table.newField(FN_employeeGuid, TypeFactory.GUID);
		field.setTitle("员工GUID");
	}

}
