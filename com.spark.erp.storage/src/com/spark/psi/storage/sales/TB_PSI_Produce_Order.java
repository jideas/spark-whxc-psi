package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Produce_Order extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Produce_Order";

	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_planDate;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_finishDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_approvePerson;
	public final TableFieldDefine f_approveDate;

	public static final String FN_billsNo ="billsNo";
	public static final String FN_planDate ="planDate";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_remark ="remark";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_finishDate ="finishDate";
	public static final String FN_status ="status";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_approvePerson ="approvePerson";
	public static final String FN_approveDate ="approveDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Produce_Order() {
		super(TABLE_NAME);
		this.table.setTitle("��������");
		TableFieldDeclare field;
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("�������");
		this.f_planDate = field = this.table.newField(FN_planDate, TypeFactory.DATE);
		field.setTitle("�ƻ��������");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.INT);
		field.setTitle("��Ʒ����");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("�Ƶ���");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("�Ƶ�����");
		this.f_finishDate = field = this.table.newField(FN_finishDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(1000));
		field.setTitle("�˻�ԭ��");
		this.f_approvePerson = field = this.table.newField(FN_approvePerson, TypeFactory.NVARCHAR(30));
		field.setTitle("�����");
		this.f_approveDate = field = this.table.newField(FN_approveDate, TypeFactory.DATE);
		field.setTitle("���ʱ��");
	}

}
