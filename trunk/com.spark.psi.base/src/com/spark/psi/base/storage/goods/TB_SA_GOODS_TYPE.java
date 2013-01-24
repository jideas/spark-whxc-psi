package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_TYPE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsTypeName;
	public final TableFieldDefine f_parentGuid;
	public final TableFieldDefine f_path;
	public final TableFieldDefine f_yzFlag;
	public final TableFieldDefine f_goodsCountDigit;
	public final TableFieldDefine f_deleteFlag;
	public final TableFieldDefine f_reflag;
	public final TableFieldDefine f_setPropertyFlag;
	public final TableFieldDefine f_properties;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsTypeName ="goodsTypeName";
	public static final String FN_parentGuid ="parentGuid";
	public static final String FN_path ="path";
	public static final String FN_yzFlag ="yzFlag";
	public static final String FN_goodsCountDigit ="goodsCountDigit";
	public static final String FN_deleteFlag ="deleteFlag";
	public static final String FN_reflag ="reflag";
	public static final String FN_setPropertyFlag ="setPropertyFlag";
	public static final String FN_properties ="properties";
	public static final String FN_createDate ="createDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_GOODS_TYPE() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ʒ��������,ע�⣺�ñ��´���ʱ��Ҫ����һ��guidΪ��10000000000000000000000000000001��������Ϊ��ȫ���������ϼ�����Ķ����ڵ�");
		this.table.setTitle("��Ʒ����� ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setDescription("�����⻧");
		field.setTitle("�⻧GUID");
		this.f_goodsTypeName = field = this.table.newField(FN_goodsTypeName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ��������");
		this.f_parentGuid = field = this.table.newField(FN_parentGuid, TypeFactory.GUID);
		field.setTitle("������GUID");
		this.f_path = field = this.table.newField(FN_path, TypeFactory.VARBINARY(544));
		field.setDescription("�ڵ���������ڽڵ㵽�����ڵ��·�������·���Ϊ544λ��Ҳ�������Ϊ17��");
		field.setTitle("�ڵ��·��");
		this.f_yzFlag = field = this.table.newField(FN_yzFlag, TypeFactory.BOOLEAN);
		field.setDescription("1��Ҷ�ӽڵ㣬0��Ҷ�ӽڵ�");
		field.setTitle("Ҷ�ӽڵ��ʶ");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_goodsCountDigit = field = this.table.newField(FN_goodsCountDigit, TypeFactory.INT);
		field.setTitle("��Ʒ��ʾλ��");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_deleteFlag = field = this.table.newField(FN_deleteFlag, TypeFactory.BOOLEAN);
		field.setDescription("1��ɾ����0����ɾ����Ĭ��Ϊ1");
		field.setTitle("ɾ����־");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_reflag = field = this.table.newField(FN_reflag, TypeFactory.BOOLEAN);
		field.setDescription("1�ѹ�����0δ������Ĭ��Ϊ0");
		field.setTitle("������ʶ");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_setPropertyFlag = field = this.table.newField(FN_setPropertyFlag, TypeFactory.BOOLEAN);
		field.setDescription("1�����á�0δ���ã���Ĭ��Ϊ0");
		field.setTitle("�Ƿ����������Ա�ʶ");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_properties = field = this.table.newField(FN_properties, TypeFactory.TEXT);
		field.setTitle("��Ʒ����");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("���ݲ���ʱ��");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("���ݴ�����");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("��������޸�ʱ��");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("��������޸���");
		this.table.newIndex("SA_GOODS_TYPE_1",f_parentGuid);
		this.table.newIndex("INDEX_GOODS_TYPE_TENANT",f_tenantsGuid,f_parentGuid);
	}

}
