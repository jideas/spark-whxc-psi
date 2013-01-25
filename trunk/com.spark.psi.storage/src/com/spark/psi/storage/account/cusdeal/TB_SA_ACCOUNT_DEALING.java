package com.spark.psi.storage.account.cusdeal;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_ACCOUNT_DEALING extends TableDeclarator {

	public static final String TABLE_NAME ="SA_ACCOUNT_DEALING";

	public final TableFieldDefine f_tenantsId;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_partnerShortName;
	public final TableFieldDefine f_type;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_initAmount;

	public static final String FN_tenantsId ="tenantsId";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_partnerShortName ="partnerShortName";
	public static final String FN_type ="type";
	public static final String FN_amount ="amount";
	public static final String FN_initAmount ="initAmount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_ACCOUNT_DEALING() {
		super(TABLE_NAME);
		this.table.setTitle("����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_tenantsId = field = this.table.newField(FN_tenantsId, TypeFactory.GUID);
		field.setTitle("�⻧ID");
		this.f_partnerId = this.table.newField(FN_partnerId, TypeFactory.GUID);
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.NVARCHAR(100));
		field.setTitle("�ͻ���Ӧ��ȫ��");
		this.f_partnerShortName = field = this.table.newField(FN_partnerShortName, TypeFactory.VARCHAR(20));
		field.setTitle("�ͻ���Ӧ�̼��");
		this.f_type = field = this.table.newField(FN_type, TypeFactory.CHAR(2));
		field.setTitle("���ͣ��ͻ�/��Ӧ�̣�");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ӧ��/Ӧ��");
		this.f_initAmount = field = this.table.newField(FN_initAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��ʼ��Ӧ��/Ӧ��");
		this.table.newIndex("INDEX_ACCOUNT_DEALING",f_tenantsId,f_RECID,f_amount,f_type);
	}

}
