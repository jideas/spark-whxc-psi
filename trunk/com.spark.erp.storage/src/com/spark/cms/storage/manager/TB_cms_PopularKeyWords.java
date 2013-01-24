package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_cms_PopularKeyWords extends TableDeclarator {

	public static final String TABLE_NAME ="cms_PopularKeyWords";

	public final TableFieldDefine f_popularKeyWords;

	public static final String FN_popularKeyWords ="popularKeyWords";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_cms_PopularKeyWords() {
		super(TABLE_NAME);
		this.table.setTitle("��������");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_popularKeyWords = field = this.table.newField(FN_popularKeyWords, TypeFactory.NVARCHAR(200));
		field.setTitle("�ؼ���(�Զ��ŷָ�)");
	}

}
