package com.spark.psi.storage.account.receipts;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_ACCOUNT_Receipts_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Receipts_Log";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_receiptsId;
	public final TableFieldDefine f_receiptNo;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_checkoutSheetId;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_relevantBillId;
	public final TableFieldDefine f_relevantBillNo;
	public final TableFieldDefine f_checkinDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_molingAmount;
	public final TableFieldDefine f_receiptPersonId;
	public final TableFieldDefine f_receiptPersonName;
	public final TableFieldDefine f_receiptDate;

	public static final String FN_id ="id";
	public static final String FN_receiptsId ="receiptsId";
	public static final String FN_receiptNo ="receiptNo";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_checkoutSheetId ="checkoutSheetId";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_relevantBillId ="relevantBillId";
	public static final String FN_relevantBillNo ="relevantBillNo";
	public static final String FN_checkinDate ="checkinDate";
	public static final String FN_amount ="amount";
	public static final String FN_molingAmount ="molingAmount";
	public static final String FN_receiptPersonId ="receiptPersonId";
	public static final String FN_receiptPersonName ="receiptPersonName";
	public static final String FN_receiptDate ="receiptDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_ACCOUNT_Receipts_Log() {
		super(TABLE_NAME);
		this.table.setTitle("�տ��¼");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_id = field = this.table.newField(FN_id, TypeFactory.GUID);
		field.setTitle("��ʶ");
		this.f_receiptsId = field = this.table.newField(FN_receiptsId, TypeFactory.GUID);
		field.setTitle("֪ͨ��id");
		this.f_receiptNo = field = this.table.newField(FN_receiptNo, TypeFactory.NVARCHAR(30));
		field.setTitle("֪ͨ�����");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("�������");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_checkoutSheetId = field = this.table.newField(FN_checkoutSheetId, TypeFactory.GUID);
		field.setTitle("��ⵥid");
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��ⵥ��");
		this.f_relevantBillId = field = this.table.newField(FN_relevantBillId, TypeFactory.GUID);
		field.setTitle("��ص���Id");
		this.f_relevantBillNo = field = this.table.newField(FN_relevantBillNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��ص���");
		this.f_checkinDate = field = this.table.newField(FN_checkinDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�տ���");
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ĩ����");
		this.f_receiptPersonId = field = this.table.newField(FN_receiptPersonId, TypeFactory.GUID);
		field.setTitle("�տ���");
		this.f_receiptPersonName = field = this.table.newField(FN_receiptPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("����");
		this.f_receiptDate = field = this.table.newField(FN_receiptDate, TypeFactory.DATE);
		field.setTitle("�տ�ʱ��");
	}

}
