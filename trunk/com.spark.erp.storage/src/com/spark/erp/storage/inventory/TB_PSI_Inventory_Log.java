package com.spark.erp.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Log";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_properties;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_categoryId;
	public final TableFieldDefine f_code;
	public final TableFieldDefine f_stockNo;
	public final TableFieldDefine f_orderId;
	public final TableFieldDefine f_orderNo;
	public final TableFieldDefine f_logType;
	public final TableFieldDefine f_instoCount;
	public final TableFieldDefine f_instoAmount;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_outstoCount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createdDate;
	public final TableFieldDefine f_inventoryType;

	public static final String FN_storeId ="storeId";
	public static final String FN_stockId ="stockId";
	public static final String FN_name ="name";
	public static final String FN_properties ="properties";
	public static final String FN_unit ="unit";
	public static final String FN_categoryId ="categoryId";
	public static final String FN_code ="code";
	public static final String FN_stockNo ="stockNo";
	public static final String FN_orderId ="orderId";
	public static final String FN_orderNo ="orderNo";
	public static final String FN_logType ="logType";
	public static final String FN_instoCount ="instoCount";
	public static final String FN_instoAmount ="instoAmount";
	public static final String FN_scale ="scale";
	public static final String FN_outstoCount ="outstoCount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createdDate ="createdDate";
	public static final String FN_inventoryType ="inventoryType";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_Log() {
		super(TABLE_NAME);
		this.table.setDescription("�����ˮ");
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ�GUID");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(50));
		field.setTitle("��Ʒ����");
		this.f_properties = field = this.table.newField(FN_properties, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(20));
		field.setTitle("��Ʒ��λ");
		this.f_categoryId = field = this.table.newField(FN_categoryId, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ���");
		this.f_stockNo = field = this.table.newField(FN_stockNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_orderId = field = this.table.newField(FN_orderId, TypeFactory.GUID);
		field.setTitle("����id");
		this.f_orderNo = field = this.table.newField(FN_orderNo, TypeFactory.NVARCHAR(30));
		field.setTitle("���ݱ��");
		this.f_logType = field = this.table.newField(FN_logType, TypeFactory.CHAR(2));
		field.setTitle("��ˮ����");
		this.f_instoCount = field = this.table.newField(FN_instoCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("�������");
		this.f_instoAmount = field = this.table.newField(FN_instoAmount, TypeFactory.NUMERIC(17,5));
		field.setTitle("�����");
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.INT);
		field.setTitle("��Ʒ����");
		this.f_outstoCount = field = this.table.newField(FN_outstoCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,5));
		field.setTitle("������");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(20));
		field.setTitle("������");
		this.f_createdDate = field = this.table.newField(FN_createdDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_inventoryType = field = this.table.newField(FN_inventoryType, TypeFactory.CHAR(2));
		field.setTitle("�������");
	}

}
