package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Deliver_Ticket extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Deliver_Ticket";

	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_onlineOrderId;
	public final TableFieldDefine f_onlineOrderNo;
	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_memberRealName;
	public final TableFieldDefine f_mobilePhone;
	public final TableFieldDefine f_stationId;
	public final TableFieldDefine f_stationName;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_disAmount;
	public final TableFieldDefine f_totalAmount;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_deliverPersonId;
	public final TableFieldDefine f_deliverPerson;
	public final TableFieldDefine f_deliverDate;
	public final TableFieldDefine f_consigneeId;
	public final TableFieldDefine f_consignee;
	public final TableFieldDefine f_consigneeDate;
	public final TableFieldDefine f_deliverTicketType;

	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_onlineOrderId ="onlineOrderId";
	public static final String FN_onlineOrderNo ="onlineOrderNo";
	public static final String FN_memberId ="memberId";
	public static final String FN_memberRealName ="memberRealName";
	public static final String FN_mobilePhone ="mobilePhone";
	public static final String FN_stationId ="stationId";
	public static final String FN_stationName ="stationName";
	public static final String FN_remark ="remark";
	public static final String FN_disAmount ="disAmount";
	public static final String FN_totalAmount ="totalAmount";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_address ="address";
	public static final String FN_status ="status";
	public static final String FN_deliverPersonId ="deliverPersonId";
	public static final String FN_deliverPerson ="deliverPerson";
	public static final String FN_deliverDate ="deliverDate";
	public static final String FN_consigneeId ="consigneeId";
	public static final String FN_consignee ="consignee";
	public static final String FN_consigneeDate ="consigneeDate";
	public static final String FN_deliverTicketType ="deliverTicketType";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Deliver_Ticket() {
		super(TABLE_NAME);
		this.table.setTitle("������");
		TableFieldDeclare field;
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���ݱ��");
		this.f_onlineOrderId = field = this.table.newField(FN_onlineOrderId, TypeFactory.GUID);
		field.setTitle("���϶���id");
		this.f_onlineOrderNo = field = this.table.newField(FN_onlineOrderNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���϶������");
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("�ͻ�ID");
		this.f_memberRealName = field = this.table.newField(FN_memberRealName, TypeFactory.NVARCHAR(50));
		field.setTitle("�ͻ�����");
		this.f_mobilePhone = field = this.table.newField(FN_mobilePhone, TypeFactory.NVARCHAR(30));
		field.setTitle("�ֻ�����");
		this.f_stationId = field = this.table.newField(FN_stationId, TypeFactory.GUID);
		field.setTitle("վ��Id");
		this.f_stationName = field = this.table.newField(FN_stationName, TypeFactory.NVARCHAR(50));
		field.setTitle("վ������");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_disAmount = field = this.table.newField(FN_disAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ۿ۽��");
		this.f_totalAmount = field = this.table.newField(FN_totalAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������Id");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(200));
		field.setTitle("��ַ");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_deliverPersonId = field = this.table.newField(FN_deliverPersonId, TypeFactory.GUID);
		field.setTitle("������Id");
		this.f_deliverPerson = field = this.table.newField(FN_deliverPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
		this.f_deliverDate = field = this.table.newField(FN_deliverDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_consigneeId = field = this.table.newField(FN_consigneeId, TypeFactory.GUID);
		field.setTitle("�ջ���Id");
		this.f_consignee = field = this.table.newField(FN_consignee, TypeFactory.NVARCHAR(40));
		field.setTitle("�ջ���");
		this.f_consigneeDate = field = this.table.newField(FN_consigneeDate, TypeFactory.DATE);
		field.setTitle("�ջ�����");
		this.f_deliverTicketType = field = this.table.newField(FN_deliverTicketType, TypeFactory.CHAR(2));
		field.setTitle("��������");
	}

}
