package com.spark.psi.storage.inventory.checkout;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Checkouting extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Checkouting";

	public final TableFieldDefine f_sheetType;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerCode;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_partnerShortName;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_isStoped;
	public final TableFieldDefine f_stopReason;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_storeNamePY;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_billsAmount;
	public final TableFieldDefine f_billsCount;
	public final TableFieldDefine f_checkoutDate;
	public final TableFieldDefine f_checkoutString;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creatorDeptId;
	public final TableFieldDefine f_deptName;
	public final TableFieldDefine f_creator;

	public static final String FN_sheetType ="sheetType";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerCode ="partnerCode";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_namePY ="namePY";
	public static final String FN_partnerShortName ="partnerShortName";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_storeId ="storeId";
	public static final String FN_isStoped ="isStoped";
	public static final String FN_stopReason ="stopReason";
	public static final String FN_storeName ="storeName";
	public static final String FN_storeNamePY ="storeNamePY";
	public static final String FN_remark ="remark";
	public static final String FN_billsAmount ="billsAmount";
	public static final String FN_billsCount ="billsCount";
	public static final String FN_checkoutDate ="checkoutDate";
	public static final String FN_checkoutString ="checkoutString";
	public static final String FN_status ="status";
	public static final String FN_createDate ="createDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creatorDeptId ="creatorDeptId";
	public static final String FN_deptName ="deptName";
	public static final String FN_creator ="creator";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_Checkouting() {
		super(TABLE_NAME);
		this.table.setTitle("���ⵥ�м��");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_sheetType = field = this.table.newField(FN_sheetType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("��Ӧ��/�ͻ�recid");
		this.f_partnerCode = this.table.newField(FN_partnerCode, TypeFactory.NVARCHAR(30));
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ӧ��/�ͻ�����");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(30));
		field.setTitle("ƴ��");
		this.f_partnerShortName = field = this.table.newField(FN_partnerShortName, TypeFactory.NVARCHAR(30));
		field.setTitle("���");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("��ص���recid");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��ص��ݱ��");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ�recid");
		this.f_isStoped = field = this.table.newField(FN_isStoped, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ���ֹ");
		this.f_stopReason = field = this.table.newField(FN_stopReason, TypeFactory.NVARCHAR(500));
		field.setTitle("��ֹԭ��");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(100));
		field.setTitle("�ֿ�����");
		this.f_storeNamePY = field = this.table.newField(FN_storeNamePY, TypeFactory.NVARCHAR(50));
		field.setTitle("ƴ��");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_billsAmount = field = this.table.newField(FN_billsAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������");
		this.f_billsCount = field = this.table.newField(FN_billsCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������Ʒ��");
		this.f_checkoutDate = field = this.table.newField(FN_checkoutDate, TypeFactory.DATE);
		field.setTitle("Ԥ�Ƴ�������");
		this.f_checkoutString = field = this.table.newField(FN_checkoutString, TypeFactory.NVARCHAR(1000));
		field.setTitle("ȷ�ϳ����ַ���");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("�������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_creatorDeptId = this.table.newField(FN_creatorDeptId, TypeFactory.GUID);
		this.f_deptName = this.table.newField(FN_deptName, TypeFactory.NVARCHAR(50));
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
	}

}
