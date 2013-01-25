package com.spark.psi.storage.index;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_COMM_INDEX_TOOL extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_INDEX_TOOL";

	public final TableFieldDefine f_name;
	public final TableFieldDefine f_x;
	public final TableFieldDefine f_y;
	public final TableFieldDefine f_userid;

	public static final String FN_name ="name";
	public static final String FN_x ="x";
	public static final String FN_y ="y";
	public static final String FN_userid ="userid";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_COMM_INDEX_TOOL() {
		super(TABLE_NAME);
		this.table.setDescription("����ͼ��");
		this.table.setTitle("����ͼ��");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_name = field = this.table.newField(FN_name, TypeFactory.VARCHAR(255));
		field.setTitle("С��������");
		this.f_x = field = this.table.newField(FN_x, TypeFactory.INT);
		field.setTitle("X����");
		this.f_y = field = this.table.newField(FN_y, TypeFactory.INT);
		field.setTitle("Y����");
		this.f_userid = field = this.table.newField(FN_userid, TypeFactory.GUID);
		field.setTitle("�û�GUID");
	}

}
