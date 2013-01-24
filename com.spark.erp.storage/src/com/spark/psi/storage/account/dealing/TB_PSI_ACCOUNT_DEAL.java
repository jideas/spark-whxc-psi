package com.spark.psi.storage.account.dealing;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_ACCOUNT_DEAL extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_DEAL";

	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_billsType;
	public final TableFieldDefine f_receiptType;
	public final TableFieldDefine f_planAmount;
	public final TableFieldDefine f_realAmount;
	public final TableFieldDefine f_balance;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_accountBillsId;
	public final TableFieldDefine f_accountBillsNo;

	public static final String FN_partnerId ="partnerId";
	public static final String FN_billsId ="billsId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_billsNo ="billsNo";
	public static final String FN_billsType ="billsType";
	public static final String FN_receiptType ="receiptType";
	public static final String FN_planAmount ="planAmount";
	public static final String FN_realAmount ="realAmount";
	public static final String FN_balance ="balance";
	public static final String FN_createDate ="createDate";
	public static final String FN_remark ="remark";
	public static final String FN_accountBillsId ="accountBillsId";
	public static final String FN_accountBillsNo ="accountBillsNo";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_ACCOUNT_DEAL() {
		super(TABLE_NAME);
		this.table.setTitle("������ϸ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("�ͻ���Ӧ��GUID");
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.VARCHAR(20));
		field.setTitle("���ݱ��");
		this.f_billsType = field = this.table.newField(FN_billsType, TypeFactory.VARCHAR(10));
		field.setTitle("��������");
		this.f_receiptType = field = this.table.newField(FN_receiptType, TypeFactory.CHAR(10));
		field.setTitle("�տʽ");
		this.f_planAmount = field = this.table.newField(FN_planAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ӧ��/Ӧ�����");
		this.f_realAmount = field = this.table.newField(FN_realAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("ʵ��/ʵ�����");
		this.f_balance = field = this.table.newField(FN_balance, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ӧ��/Ӧ�����");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.VARCHAR(1000));
		field.setTitle("��ע");
		this.f_accountBillsId = field = this.table.newField(FN_accountBillsId, TypeFactory.GUID);
		field.setTitle("���񵥾�GUID");
		this.f_accountBillsNo = field = this.table.newField(FN_accountBillsNo, TypeFactory.VARCHAR(20));
		field.setTitle("���񵥾ݱ��");
	}

}
