package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_GOODS_TYPE_PROJECT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE_PROJECT";

	public final TableFieldDefine f_propertyGuid;
	public final TableFieldDefine f_projectValue;
	public final TableFieldDefine f_projectKey;
	public final TableFieldDefine f_projectOrder;

	public static final String FN_propertyGuid ="propertyGuid";
	public static final String FN_projectValue ="projectValue";
	public static final String FN_projectKey ="projectKey";
	public static final String FN_projectOrder ="projectOrder";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_GOODS_TYPE_PROJECT() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ʒ�����������Ŀ");
		this.table.setTitle("��Ʒ����������Ŀ�� ");
		TableFieldDeclare field;
		this.f_propertyGuid = field = this.table.newField(FN_propertyGuid, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_projectValue = field = this.table.newField(FN_projectValue, TypeFactory.NVARCHAR(12));
		field.setTitle("��Ŀֵ");
		this.f_projectKey = field = this.table.newField(FN_projectKey, TypeFactory.CHAR(2));
		field.setTitle("��Ŀ��");
		this.f_projectOrder = field = this.table.newField(FN_projectOrder, TypeFactory.INT);
		field.setDescription("����Ϊ��1��ʼ������");
		field.setTitle("������Ŀ��ʾ˳��");
		this.table.newIndex("SA_GOODS_TYPE_PROJECT",f_propertyGuid);
	}

}
