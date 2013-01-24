package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_DELIVERY_PRICE extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_DELIVERY_PRICE";

	public final TableFieldDefine f_dcount;
	public final TableFieldDefine f_dprice;
	public final TableFieldDefine f_single;
	public final TableFieldDefine f_lastModify;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_activing;

	public static final String FN_dcount ="dcount";
	public static final String FN_dprice ="dprice";
	public static final String FN_single ="single";
	public static final String FN_lastModify ="lastModify";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_remark ="remark";
	public static final String FN_activing ="activing";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_DELIVERY_PRICE() {
		super(TABLE_NAME);
		this.table.setTitle("�ͻ����ż۸�");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_dcount = field = this.table.newField(FN_dcount, TypeFactory.INT);
		field.setTitle("����");
		this.f_dprice = field = this.table.newField(FN_dprice, TypeFactory.NUMERIC(10,2));
		field.setTitle("�۸�");
		this.f_single = field = this.table.newField(FN_single, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����ۼ�");
		this.f_lastModify = this.table.newField(FN_lastModify, TypeFactory.NVARCHAR(30));
		this.f_lastModifyDate = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		this.f_remark = this.table.newField(FN_remark, TypeFactory.NVARCHAR(100));
		this.f_activing = this.table.newField(FN_activing, TypeFactory.BOOLEAN);
	}

}
