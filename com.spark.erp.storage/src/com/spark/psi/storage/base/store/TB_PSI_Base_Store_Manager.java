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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_Store_Manager() {
		super(TABLE_NAME);
		this.table.setTitle("���Աӳ��");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("�ֿ�GUID");
		this.f_employeeType = field = this.table.newField(FN_employeeType, TypeFactory.CHAR(2));
		field.setTitle("Ա������");
		this.f_employeeGuid = field = this.table.newField(FN_employeeGuid, TypeFactory.GUID);
		field.setTitle("Ա��GUID");
	}

}
