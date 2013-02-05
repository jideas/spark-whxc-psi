package com.spark.psi.storage.store.goodssplit;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_GoodsSplit_Materials extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GoodsSplit_Materials";

	public final TableFieldDefine f_materialsId;
	public final TableFieldDefine f_materialsName;
	public final TableFieldDefine f_spec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_standardCount;
	public final TableFieldDefine f_mcount;
	public final TableFieldDefine f_splitSheetId;

	public static final String FN_materialsId ="materialsId";
	public static final String FN_materialsName ="materialsName";
	public static final String FN_spec ="spec";
	public static final String FN_unit ="unit";
	public static final String FN_standardCount ="standardCount";
	public static final String FN_mcount ="mcount";
	public static final String FN_splitSheetId ="splitSheetId";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_GoodsSplit_Materials() {
		super(TABLE_NAME);
		this.table.setTitle("��ֲ�����ϸ");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_materialsId = field = this.table.newField(FN_materialsId, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_materialsName = field = this.table.newField(FN_materialsName, TypeFactory.NVARCHAR(100));
		field.setTitle("��������");
		this.f_spec = field = this.table.newField(FN_spec, TypeFactory.NVARCHAR(100));
		field.setTitle("���");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(30));
		field.setTitle("��λ");
		this.f_standardCount = field = this.table.newField(FN_standardCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("�ο�����");
		this.f_mcount = field = this.table.newField(FN_mcount, TypeFactory.NUMERIC(17,5));
		field.setTitle("����");
		this.f_splitSheetId = field = this.table.newField(FN_splitSheetId, TypeFactory.GUID);
		field.setTitle("����id");
	}

}
