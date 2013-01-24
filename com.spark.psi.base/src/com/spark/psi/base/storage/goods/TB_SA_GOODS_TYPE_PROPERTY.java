package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_GOODS_TYPE_PROPERTY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE_PROPERTY";

	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_propertyName;
	public final TableFieldDefine f_propertyInputType;
	public final TableFieldDefine f_propertyOrder;

	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_propertyName ="propertyName";
	public static final String FN_propertyInputType ="propertyInputType";
	public static final String FN_propertyOrder ="propertyOrder";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_GOODS_TYPE_PROPERTY() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ʒ���������");
		this.table.setTitle("��Ʒ�������Ա� ");
		TableFieldDeclare field;
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_propertyName = field = this.table.newField(FN_propertyName, TypeFactory.NVARCHAR(12));
		field.setTitle("��Ʒ������������");
		this.f_propertyInputType = field = this.table.newField(FN_propertyInputType, TypeFactory.CHAR(2));
		field.setDescription("ö�٣�01�ֶ�¼�롢02����ѡ��");
		field.setTitle("����¼������");
		this.f_propertyOrder = field = this.table.newField(FN_propertyOrder, TypeFactory.INT);
		field.setDescription("����Ϊ��1��ʼ������");
		field.setTitle("������ʾ˳��");
		this.table.newIndex("SA_GOODS_TYPE_TYPEGUID",f_goodsTypeGuid);
	}

}
