package com.spark.psi.storage.base.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_Base_GoodsCategory extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_GoodsCategory";

	public final TableFieldDefine f_categoryNo;
	public final TableFieldDefine f_categoryName;
	public final TableFieldDefine f_parentId;
	public final TableFieldDefine f_path;
	public final TableFieldDefine f_leafFlag;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_canDelete;
	public final TableFieldDefine f_reflag;
	public final TableFieldDefine f_setPropertyFlag;
	public final TableFieldDefine f_properties;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;

	public static final String FN_categoryNo ="categoryNo";
	public static final String FN_categoryName ="categoryName";
	public static final String FN_parentId ="parentId";
	public static final String FN_path ="path";
	public static final String FN_leafFlag ="leafFlag";
	public static final String FN_scale ="scale";
	public static final String FN_canDelete ="canDelete";
	public static final String FN_reflag ="reflag";
	public static final String FN_setPropertyFlag ="setPropertyFlag";
	public static final String FN_properties ="properties";
	public static final String FN_createDate ="createDate";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_GoodsCategory() {
		super(TABLE_NAME);
		this.table.setTitle("��Ʒ����");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_categoryNo = field = this.table.newField(FN_categoryNo, TypeFactory.NVARCHAR(25));
		field.setTitle("������");
		this.f_categoryName = field = this.table.newField(FN_categoryName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ��������");
		this.f_parentId = field = this.table.newField(FN_parentId, TypeFactory.GUID);
		field.setTitle("������GUID");
		this.f_path = field = this.table.newField(FN_path, TypeFactory.VARBINARY(544));
		field.setTitle("�ڵ��·��");
		this.f_leafFlag = field = this.table.newField(FN_leafFlag, TypeFactory.BOOLEAN);
		field.setTitle("Ҷ�ӽڵ��ʶ");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.INT);
		field.setTitle("��Ʒ��ʾλ��");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_canDelete = field = this.table.newField(FN_canDelete, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����ɾ��");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_reflag = field = this.table.newField(FN_reflag, TypeFactory.BOOLEAN);
		field.setTitle("������ʶ");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_setPropertyFlag = field = this.table.newField(FN_setPropertyFlag, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����������Ա�ʶ");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_properties = field = this.table.newField(FN_properties, TypeFactory.TEXT);
		field.setTitle("��Ʒ����");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("���ݲ���ʱ��");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("��������޸�ʱ��");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("��������޸���");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
	}

}
