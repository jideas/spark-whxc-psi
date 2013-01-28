package com.spark.psi.storage.inventory.checkout;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Checkout_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Checkout_Det";

	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_avgCost;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_realCount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;

	public static final String FN_sheetId ="sheetId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_scale ="scale";
	public static final String FN_price ="price";
	public static final String FN_avgCost ="avgCost";
	public static final String FN_amount ="amount";
	public static final String FN_realCount ="realCount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_Checkout_Det() {
		super(TABLE_NAME);
		this.table.setTitle("���ⵥ��ϸ");
		TableFieldDeclare field;
		this.f_sheetId = field = this.table.newField(FN_sheetId, TypeFactory.GUID);
		field.setTitle("���ⵥrecid");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("��Ʒrecid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(1000));
		field.setTitle("��Ʒ���");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(12));
		field.setTitle("��Ʒ��λ");
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.INT);
		field.setTitle("��Ʒ��������");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ʒ����");
		this.f_avgCost = field = this.table.newField(FN_avgCost, TypeFactory.NUMERIC(17,2));
		field.setTitle("���ɱ�");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ʒ���");
		this.f_realCount = field = this.table.newField(FN_realCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("����������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
	}

}