package com.spark.cms.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_CATEGORY_PVALUES extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_CATEGORY_PVALUES";

	public final TableFieldDefine f_propertyId;
	public final TableFieldDefine f_categoryId;
	public final TableFieldDefine f_pvalue;
	public final TableFieldDefine f_ordinal;

	public static final String FN_propertyId ="propertyId";
	public static final String FN_categoryId ="categoryId";
	public static final String FN_pvalue ="pvalue";
	public static final String FN_ordinal ="ordinal";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_CATEGORY_PVALUES() {
		super(TABLE_NAME);
		this.table.setTitle("��������ֵ");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_propertyId = field = this.table.newField(FN_propertyId, TypeFactory.GUID);
		field.setTitle("����id");
		this.f_categoryId = field = this.table.newField(FN_categoryId, TypeFactory.GUID);
		field.setTitle("����id");
		this.f_pvalue = field = this.table.newField(FN_pvalue, TypeFactory.VARCHAR(10));
		field.setTitle("ֵ");
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.VARCHAR(10));
		field.setTitle("˳���");
	}

}
