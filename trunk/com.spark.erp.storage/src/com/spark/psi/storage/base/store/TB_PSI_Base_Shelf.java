package com.spark.psi.storage.base.store;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Shelf extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Shelf";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_shelfNo;
	public final TableFieldDefine f_tiersCount;

	public static final String FN_storeId ="storeId";
	public static final String FN_shelfNo ="shelfNo";
	public static final String FN_tiersCount ="tiersCount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_Shelf() {
		super(TABLE_NAME);
		this.table.setTitle("��λ����");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ�id");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("��λ���");
		this.f_tiersCount = field = this.table.newField(FN_tiersCount, TypeFactory.INT);
		field.setTitle("����");
	}

}
