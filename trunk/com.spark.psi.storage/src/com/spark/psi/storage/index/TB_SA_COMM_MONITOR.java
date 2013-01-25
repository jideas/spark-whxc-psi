package com.spark.psi.storage.index;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_COMM_MONITOR extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_MONITOR";

	public final TableFieldDefine f_monitors;

	public static final String FN_monitors ="monitors";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_COMM_MONITOR() {
		super(TABLE_NAME);
		this.table.setDescription("�������");
		this.table.setTitle("�������");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_monitors = field = this.table.newField(FN_monitors, TypeFactory.VARCHAR(255));
		field.setTitle("�����");
	}

}
