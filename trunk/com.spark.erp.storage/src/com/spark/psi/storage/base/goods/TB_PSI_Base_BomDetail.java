package com.spark.psi.storage.base.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_BomDetail extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_BomDetail";

	public final TableFieldDefine f_bomId;
	public final TableFieldDefine f_materialId;
	public final TableFieldDefine f_baseCount;
	public final TableFieldDefine f_lossRate;
	public final TableFieldDefine f_realCount;

	public static final String FN_bomId ="bomId";
	public static final String FN_materialId ="materialId";
	public static final String FN_baseCount ="baseCount";
	public static final String FN_lossRate ="lossRate";
	public static final String FN_realCount ="realCount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_BomDetail() {
		super(TABLE_NAME);
		this.table.setTitle("bom��ϸ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_bomId = field = this.table.newField(FN_bomId, TypeFactory.GUID);
		field.setTitle("BOM��ʶ");
		this.f_materialId = field = this.table.newField(FN_materialId, TypeFactory.GUID);
		field.setTitle("�Ӽ�id");
		this.f_baseCount = field = this.table.newField(FN_baseCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_lossRate = field = this.table.newField(FN_lossRate, TypeFactory.NUMERIC(17,4));
		field.setTitle("�Ӽ������");
		this.f_realCount = field = this.table.newField(FN_realCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("��������");
	}

}
