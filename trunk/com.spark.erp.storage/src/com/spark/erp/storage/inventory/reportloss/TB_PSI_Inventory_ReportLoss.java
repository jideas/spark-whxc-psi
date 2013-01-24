package com.spark.erp.storage.inventory.reportloss;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_ReportLoss extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_ReportLoss";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_applyDate;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_approvalPersonId;
	public final TableFieldDefine f_approvalPersonName;
	public final TableFieldDefine f_approvalDate;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_reportType;
	public final TableFieldDefine f_accountApprover;
	public final TableFieldDefine f_accountApproverId;
	public final TableFieldDefine f_accountApproveDate;

	public static final String FN_id ="id";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_applyDate ="applyDate";
	public static final String FN_remark ="remark";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_approvalPersonId ="approvalPersonId";
	public static final String FN_approvalPersonName ="approvalPersonName";
	public static final String FN_approvalDate ="approvalDate";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_status ="status";
	public static final String FN_reportType ="reportType";
	public static final String FN_accountApprover ="accountApprover";
	public static final String FN_accountApproverId ="accountApproverId";
	public static final String FN_accountApproveDate ="accountApproveDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_ReportLoss() {
		super(TABLE_NAME);
		this.table.setDescription("��汨��");
		TableFieldDeclare field;
		this.f_id = field = this.table.newField(FN_id, TypeFactory.GUID);
		field.setTitle("��¼��ʶ");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ��ʶ");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("�ֿ�����");
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���𵥺�");
		this.f_applyDate = field = this.table.newField(FN_applyDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("�����˱�ʶ");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(50));
		field.setTitle("����������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_approvalPersonId = field = this.table.newField(FN_approvalPersonId, TypeFactory.GUID);
		field.setTitle("�����˱�ʶ");
		this.f_approvalPersonName = field = this.table.newField(FN_approvalPersonName, TypeFactory.NVARCHAR(50));
		field.setTitle("����������");
		this.f_approvalDate = field = this.table.newField(FN_approvalDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(200));
		field.setTitle("����ԭ��");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_reportType = field = this.table.newField(FN_reportType, TypeFactory.CHAR(2));
		field.setTitle("��������");
		this.f_accountApprover = this.table.newField(FN_accountApprover, TypeFactory.NVARCHAR(30));
		this.f_accountApproverId = this.table.newField(FN_accountApproverId, TypeFactory.GUID);
		this.f_accountApproveDate = this.table.newField(FN_accountApproveDate, TypeFactory.DATE);
	}

}
