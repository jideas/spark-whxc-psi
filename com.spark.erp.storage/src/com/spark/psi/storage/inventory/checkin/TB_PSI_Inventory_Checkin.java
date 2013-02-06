package com.spark.psi.storage.inventory.checkin;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Checkin extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Checkin";

	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_sheetType;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_partnerCode;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_partnerShortName;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_storeNamePY;
	public final TableFieldDefine f_goodsFrom;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_buyPerson;
	public final TableFieldDefine f_buyDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_askedAmount;
	public final TableFieldDefine f_paidAmount;
	public final TableFieldDefine f_disAmount;
	public final TableFieldDefine f_checkinDate;
	public final TableFieldDefine f_checkinPerson;
	public final TableFieldDefine f_checkinPersonName;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_deptName;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;

	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_sheetType ="sheetType";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_partnerCode ="partnerCode";
	public static final String FN_namePY ="namePY";
	public static final String FN_partnerShortName ="partnerShortName";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";
	public static final String FN_storeNamePY ="storeNamePY";
	public static final String FN_goodsFrom ="goodsFrom";
	public static final String FN_remark ="remark";
	public static final String FN_buyPerson ="buyPerson";
	public static final String FN_buyDate ="buyDate";
	public static final String FN_amount ="amount";
	public static final String FN_askedAmount ="askedAmount";
	public static final String FN_paidAmount ="paidAmount";
	public static final String FN_disAmount ="disAmount";
	public static final String FN_checkinDate ="checkinDate";
	public static final String FN_checkinPerson ="checkinPerson";
	public static final String FN_checkinPersonName ="checkinPersonName";
	public static final String FN_deptId ="deptId";
	public static final String FN_deptName ="deptName";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_Checkin() {
		super(TABLE_NAME);
		this.table.setTitle("��ⵥ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.VARCHAR(20));
		field.setTitle("��ⵥ��");
		this.f_sheetType = field = this.table.newField(FN_sheetType, TypeFactory.CHAR(2));
		field.setTitle("�������");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("��Ӧ��/�ͻ�recid");
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.VARCHAR(100));
		field.setTitle("��Ӧ��/�ͻ�����");
		this.f_partnerCode = field = this.table.newField(FN_partnerCode, TypeFactory.NVARCHAR(30));
		field.setTitle("���");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.VARCHAR(30));
		field.setTitle("ƴ��");
		this.f_partnerShortName = field = this.table.newField(FN_partnerShortName, TypeFactory.VARCHAR(20));
		field.setTitle("���");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("��ص���recid");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("��ص��ݱ��");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ�recid");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(100));
		field.setTitle("�ֿ�����");
		this.f_storeNamePY = field = this.table.newField(FN_storeNamePY, TypeFactory.NVARCHAR(50));
		field.setTitle("ƴ��");
		this.f_goodsFrom = field = this.table.newField(FN_goodsFrom, TypeFactory.NVARCHAR(500));
		field.setTitle("��Ʒ��Դ");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_buyPerson = field = this.table.newField(FN_buyPerson, TypeFactory.NVARCHAR(20));
		field.setTitle("�ɹ���");
		this.f_buyDate = field = this.table.newField(FN_buyDate, TypeFactory.DATE);
		field.setTitle("�ɹ�����");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�����");
		this.f_askedAmount = field = this.table.newField(FN_askedAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��������");
		this.f_paidAmount = field = this.table.newField(FN_paidAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�Ѹ�����");
		this.f_disAmount = field = this.table.newField(FN_disAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ۿ۽��");
		this.f_checkinDate = field = this.table.newField(FN_checkinDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_checkinPerson = field = this.table.newField(FN_checkinPerson, TypeFactory.GUID);
		field.setTitle("ȷ�������");
		this.f_checkinPersonName = field = this.table.newField(FN_checkinPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("ȷ�������");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("����recid");
		this.f_deptName = field = this.table.newField(FN_deptName, TypeFactory.NVARCHAR(50));
		field.setTitle("��������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
	}

}