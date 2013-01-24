package com.spark.psi.base.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_sa_dictionary_list extends TableDeclarator {

	public static final String TABLE_NAME ="sa_dictionary_list";

	public final TableFieldDefine f_typeName;
	public final TableFieldDefine f_code;
	public final TableFieldDefine f_codeName;
	public final TableFieldDefine f_parentcode;
	public final TableFieldDefine f_installDate;
	public final TableFieldDefine f_installPerson;

	public static final String FN_typeName ="typeName";
	public static final String FN_code ="code";
	public static final String FN_codeName ="codeName";
	public static final String FN_parentcode ="parentcode";
	public static final String FN_installDate ="installDate";
	public static final String FN_installPerson ="installPerson";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_sa_dictionary_list() {
		super(TABLE_NAME);
		this.table.setTitle("�����ֵ����ݴ�ű�  ");
		TableFieldDeclare field;
		this.f_typeName = field = this.table.newField(FN_typeName, TypeFactory.NVARCHAR(20));
		field.setTitle("�����ֵ�������");
		field.setKeepValid(true);
		this.f_code = field = this.table.newField(FN_code, TypeFactory.CHAR(20));
		field.setTitle("����ֵ");
		field.setKeepValid(true);
		this.f_codeName = field = this.table.newField(FN_codeName, TypeFactory.NVARCHAR(50));
		field.setTitle("��������");
		this.f_parentcode = field = this.table.newField(FN_parentcode, TypeFactory.CHAR(20));
		field.setTitle("��������");
		this.f_installDate = field = this.table.newField(FN_installDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_installPerson = field = this.table.newField(FN_installPerson, TypeFactory.GUID);
		field.setTitle("������");
		this.table.newIndex("INDEX_SA_DICTIONARY_LIST",f_typeName,f_code);
	}

}
