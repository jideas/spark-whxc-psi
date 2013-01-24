package com.spark.psi.storage.account.payment;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_ACCOUNT_Payment extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Payment";

	public final TableFieldDefine f_paymentNo;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_paymentType;
	public final TableFieldDefine f_denyReason;
	public final TableFieldDefine f_payDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_paidAmount;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_approvePerson;
	public final TableFieldDefine f_approvePersonName;
	public final TableFieldDefine f_approveDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_dealingsWay;

	public static final String FN_paymentNo ="paymentNo";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_paymentType ="paymentType";
	public static final String FN_denyReason ="denyReason";
	public static final String FN_payDate ="payDate";
	public static final String FN_status ="status";
	public static final String FN_amount ="amount";
	public static final String FN_paidAmount ="paidAmount";
	public static final String FN_remark ="remark";
	public static final String FN_approvePerson ="approvePerson";
	public static final String FN_approvePersonName ="approvePersonName";
	public static final String FN_approveDate ="approveDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_dealingsWay ="dealingsWay";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_ACCOUNT_Payment() {
		super(TABLE_NAME);
		this.table.setTitle("�������뵥");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_paymentNo = field = this.table.newField(FN_paymentNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("�������");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_paymentType = field = this.table.newField(FN_paymentType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_denyReason = field = this.table.newField(FN_denyReason, TypeFactory.NVARCHAR(200));
		field.setTitle("�˻�ԭ��");
		this.f_payDate = field = this.table.newField(FN_payDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ܽ��");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_paidAmount = field = this.table.newField(FN_paidAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�Ѹ����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_approvePerson = field = this.table.newField(FN_approvePerson, TypeFactory.GUID);
		field.setTitle("�����");
		this.f_approvePersonName = field = this.table.newField(FN_approvePersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("���������");
		this.f_approveDate = field = this.table.newField(FN_approveDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("����������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.NVARCHAR(100));
		field.setTitle("�������");
		this.f_dealingsWay = field = this.table.newField(FN_dealingsWay, TypeFactory.CHAR(2));
		field.setTitle("���ʽ");
	}

}
