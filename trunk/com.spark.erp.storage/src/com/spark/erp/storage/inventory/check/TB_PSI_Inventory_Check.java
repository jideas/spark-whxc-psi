package com.spark.erp.storage.inventory.check;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Check extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Check";

	public final TableFieldDefine f_startDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_checkOrdNo;
	public final TableFieldDefine f_checkOrdState;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_checkObj;
	public final TableFieldDefine f_checkPerson;
	public final TableFieldDefine f_markPerson;
	public final TableFieldDefine f_markName;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_storeStatus;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_storePY;
	public final TableFieldDefine f_checkProfit;

	public static final String FN_startDate ="startDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_checkOrdNo ="checkOrdNo";
	public static final String FN_checkOrdState ="checkOrdState";
	public static final String FN_remark ="remark";
	public static final String FN_checkObj ="checkObj";
	public static final String FN_checkPerson ="checkPerson";
	public static final String FN_markPerson ="markPerson";
	public static final String FN_markName ="markName";
	public static final String FN_deptId ="deptId";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";
	public static final String FN_creator ="creator";
	public static final String FN_storeStatus ="storeStatus";
	public static final String FN_createDate ="createDate";
	public static final String FN_storePY ="storePY";
	public static final String FN_checkProfit ="checkProfit";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_Check() {
		super(TABLE_NAME);
		this.table.setDescription("����̵�");
		TableFieldDeclare field;
		this.f_startDate = field = this.table.newField(FN_startDate, TypeFactory.DATE);
		field.setTitle("�̵㿪ʼʱ��");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("�̵����ʱ��");
		this.f_checkOrdNo = field = this.table.newField(FN_checkOrdNo, TypeFactory.NVARCHAR(30));
		field.setTitle("�̵㵥��ţ����ţ�");
		this.f_checkOrdState = field = this.table.newField(FN_checkOrdState, TypeFactory.CHAR(2));
		field.setTitle("�̵㵥��״̬");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_checkObj = field = this.table.newField(FN_checkObj, TypeFactory.CHAR(2));
		field.setTitle("�̵����");
		this.f_checkPerson = field = this.table.newField(FN_checkPerson, TypeFactory.NVARCHAR(255));
		field.setTitle("�̵���");
		this.f_markPerson = field = this.table.newField(FN_markPerson, TypeFactory.GUID);
		field.setTitle("�Ƶ���");
		this.f_markName = field = this.table.newField(FN_markName, TypeFactory.NVARCHAR(50));
		field.setTitle("�Ƶ�������");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("����");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ�");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("�ֿ�����");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(50));
		field.setTitle("������");
		this.f_storeStatus = field = this.table.newField(FN_storeStatus, TypeFactory.CHAR(2));
		field.setTitle("�ֿ�״̬");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_storePY = field = this.table.newField(FN_storePY, TypeFactory.NVARCHAR(25));
		field.setTitle("�ֿ����ƣ�ƴ����");
		this.f_checkProfit = field = this.table.newField(FN_checkProfit, TypeFactory.NUMERIC(17,5));
		field.setTitle("��ӯ����");
	}

}
