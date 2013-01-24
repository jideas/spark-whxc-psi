package com.spark.erp.storage.onlinereturn;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_SALES_ONLINERETURN extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_SALES_ONLINERETURN";

	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_memberName;
	public final TableFieldDefine f_memberPhone;
	public final TableFieldDefine f_onlineCreateDate;
	public final TableFieldDefine f_salesAmount;
	public final TableFieldDefine f_onlineBillsId;
	public final TableFieldDefine f_onlineBillsNo;
	public final TableFieldDefine f_stationId;
	public final TableFieldDefine f_stationName;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_returnReason;
	public final TableFieldDefine f_stopReason;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_approvorId;
	public final TableFieldDefine f_approvor;
	public final TableFieldDefine f_approveDate;
	public final TableFieldDefine f_finishPerson;
	public final TableFieldDefine f_finishPersonName;
	public final TableFieldDefine f_finishedDate;
	public final TableFieldDefine f_vantages;

	public static final String FN_billsNo ="billsNo";
	public static final String FN_memberId ="memberId";
	public static final String FN_memberName ="memberName";
	public static final String FN_memberPhone ="memberPhone";
	public static final String FN_onlineCreateDate ="onlineCreateDate";
	public static final String FN_salesAmount ="salesAmount";
	public static final String FN_onlineBillsId ="onlineBillsId";
	public static final String FN_onlineBillsNo ="onlineBillsNo";
	public static final String FN_stationId ="stationId";
	public static final String FN_stationName ="stationName";
	public static final String FN_amount ="amount";
	public static final String FN_status ="status";
	public static final String FN_returnReason ="returnReason";
	public static final String FN_stopReason ="stopReason";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_approvorId ="approvorId";
	public static final String FN_approvor ="approvor";
	public static final String FN_approveDate ="approveDate";
	public static final String FN_finishPerson ="finishPerson";
	public static final String FN_finishPersonName ="finishPersonName";
	public static final String FN_finishedDate ="finishedDate";
	public static final String FN_vantages ="vantages";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_SALES_ONLINERETURN() {
		super(TABLE_NAME);
		this.table.setTitle("���϶����˻�");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���");
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("��Աid");
		this.f_memberName = field = this.table.newField(FN_memberName, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ա����");
		this.f_memberPhone = field = this.table.newField(FN_memberPhone, TypeFactory.NVARCHAR(13));
		field.setTitle("��Ա�ֻ���");
		this.f_onlineCreateDate = field = this.table.newField(FN_onlineCreateDate, TypeFactory.DATE);
		field.setTitle("�µ�����");
		this.f_salesAmount = field = this.table.newField(FN_salesAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���۶����ܽ��");
		this.f_onlineBillsId = field = this.table.newField(FN_onlineBillsId, TypeFactory.GUID);
		field.setTitle("�������϶���id");
		this.f_onlineBillsNo = field = this.table.newField(FN_onlineBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���϶������");
		this.f_stationId = field = this.table.newField(FN_stationId, TypeFactory.GUID);
		field.setTitle("վ��id");
		this.f_stationName = field = this.table.newField(FN_stationName, TypeFactory.NVARCHAR(50));
		field.setTitle("վ������");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ܽ��");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_returnReason = field = this.table.newField(FN_returnReason, TypeFactory.NVARCHAR(500));
		field.setTitle("�˻�ԭ��");
		this.f_stopReason = field = this.table.newField(FN_stopReason, TypeFactory.NVARCHAR(500));
		field.setTitle("��ֹԭ��");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(500));
		field.setTitle("����ԭ��");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("����������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_approvorId = field = this.table.newField(FN_approvorId, TypeFactory.GUID);
		field.setTitle("�����id");
		this.f_approvor = field = this.table.newField(FN_approvor, TypeFactory.NVARCHAR(30));
		field.setTitle("���������");
		this.f_approveDate = field = this.table.newField(FN_approveDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_finishPerson = field = this.table.newField(FN_finishPerson, TypeFactory.GUID);
		field.setTitle("ȷ�������");
		this.f_finishPersonName = this.table.newField(FN_finishPersonName, TypeFactory.NVARCHAR(30));
		this.f_finishedDate = this.table.newField(FN_finishedDate, TypeFactory.DATE);
		this.f_vantages = this.table.newField(FN_vantages, TypeFactory.NUMERIC(17,0));
	}

}
