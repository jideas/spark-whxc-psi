package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER_DEALING extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_DEALING";

	public final TableFieldDefine f_memberID;
	public final TableFieldDefine f_dealType;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_occurDate;

	public static final String FN_memberID ="memberID";
	public static final String FN_dealType ="dealType";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_amount ="amount";
	public static final String FN_occurDate ="occurDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_MEMBER_DEALING() {
		super(TABLE_NAME);
		this.table.setTitle("������¼");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_memberID = field = this.table.newField(FN_memberID, TypeFactory.GUID);
		field.setTitle("��Աid");
		this.f_dealType = field = this.table.newField(FN_dealType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("��ֵ��������id");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��ص���");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(10,2));
		field.setTitle("���");
		this.f_occurDate = field = this.table.newField(FN_occurDate, TypeFactory.DATE);
		field.setTitle("��������");
	}

}
