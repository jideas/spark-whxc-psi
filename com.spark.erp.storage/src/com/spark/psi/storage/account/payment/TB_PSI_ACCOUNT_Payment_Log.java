package com.spark.psi.storage.account.payment;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_ACCOUNT_Payment_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Payment_Log";

	public final TableFieldDefine f_paymentId;
	public final TableFieldDefine f_paymentNo;
	public final TableFieldDefine f_checkinSheetId;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_relevantBillId;
	public final TableFieldDefine f_relevantBillNo;
	public final TableFieldDefine f_checkinDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_molingAmount;
	public final TableFieldDefine f_payPersonId;
	public final TableFieldDefine f_payPersonName;
	public final TableFieldDefine f_payDate;

	public static final String FN_paymentId ="paymentId";
	public static final String FN_paymentNo ="paymentNo";
	public static final String FN_checkinSheetId ="checkinSheetId";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_relevantBillId ="relevantBillId";
	public static final String FN_relevantBillNo ="relevantBillNo";
	public static final String FN_checkinDate ="checkinDate";
	public static final String FN_amount ="amount";
	public static final String FN_molingAmount ="molingAmount";
	public static final String FN_payPersonId ="payPersonId";
	public static final String FN_payPersonName ="payPersonName";
	public static final String FN_payDate ="payDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_ACCOUNT_Payment_Log() {
		super(TABLE_NAME);
		this.table.setTitle("�����¼");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_paymentId = field = this.table.newField(FN_paymentId, TypeFactory.GUID);
		field.setTitle("���뵥id");
		this.f_paymentNo = field = this.table.newField(FN_paymentNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���뵥���");
		this.f_checkinSheetId = field = this.table.newField(FN_checkinSheetId, TypeFactory.GUID);
		field.setTitle("��ⵥid");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("�������");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��ⵥ��");
		this.f_relevantBillId = field = this.table.newField(FN_relevantBillId, TypeFactory.GUID);
		field.setTitle("��ص���Id");
		this.f_relevantBillNo = field = this.table.newField(FN_relevantBillNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��ص���");
		this.f_checkinDate = field = this.table.newField(FN_checkinDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("������");
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ĩ����");
		this.f_payPersonId = field = this.table.newField(FN_payPersonId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_payPersonName = field = this.table.newField(FN_payPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("����������");
		this.f_payDate = field = this.table.newField(FN_payDate, TypeFactory.DATE);
		field.setTitle("��������");
	}

}
