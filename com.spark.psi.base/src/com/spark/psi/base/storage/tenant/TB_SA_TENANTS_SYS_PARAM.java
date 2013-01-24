package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_TENANTS_SYS_PARAM extends TableDeclarator {

	public static final String TABLE_NAME ="SA_TENANTS_SYS_PARAM";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_key;
	public final TableFieldDefine f_value;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_key ="key";
	public static final String FN_value ="value";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_TENANTS_SYS_PARAM() {
		super(TABLE_NAME);
		this.table.setDescription(" ");
		this.table.setTitle("�⻧ϵͳ����");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newPrimaryField(FN_tenantsGuid, TypeFactory.GUID);
		field.setKeepValid(true);
		this.f_key = field = this.table.newPrimaryField(FN_key, TypeFactory.NVARCHAR(25));
		field.setTitle("��");
		field.setKeepValid(true);
		this.f_value = field = this.table.newField(FN_value, TypeFactory.BOOLEAN);
		field.setTitle("ֵ");
	}

}
