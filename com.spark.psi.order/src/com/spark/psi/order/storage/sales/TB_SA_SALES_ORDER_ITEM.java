package com.spark.psi.order.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SALES_ORDER_ITEM extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALES_ORDER_ITEM";

	public final TableFieldDefine f_tenantsId;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_orderId;
	public final TableFieldDefine f_goodsItemId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsProperties;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_countDecimal;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_num;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_discount;
	public final TableFieldDefine f_disAmount;
	public final TableFieldDefine f_promotionId;

	public static final String FN_tenantsId ="tenantsId";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_orderId ="orderId";
	public static final String FN_goodsItemId ="goodsItemId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsProperties ="goodsProperties";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_countDecimal ="countDecimal";
	public static final String FN_price ="price";
	public static final String FN_num ="num";
	public static final String FN_amount ="amount";
	public static final String FN_discount ="discount";
	public static final String FN_disAmount ="disAmount";
	public static final String FN_promotionId ="promotionId";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_SALES_ORDER_ITEM() {
		super(TABLE_NAME);
		this.table.setTitle("������۶�����ϸ��Ϣ");
		TableFieldDeclare field;
		this.f_tenantsId = field = this.table.newField(FN_tenantsId, TypeFactory.GUID);
		field.setTitle("�⻧GUID");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������GUID");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.VARCHAR(50));
		field.setTitle("����������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_orderId = field = this.table.newField(FN_orderId, TypeFactory.GUID);
		field.setTitle("���۶���GUID");
		this.f_goodsItemId = field = this.table.newField(FN_goodsItemId, TypeFactory.GUID);
		field.setTitle("��Ʒ���GUID");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(20));
		field.setTitle("��Ʒ����");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("��Ʒ����");
		this.f_goodsProperties = field = this.table.newField(FN_goodsProperties, TypeFactory.NVARCHAR(1000));
		field.setTitle("��Ʒ����");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(10));
		field.setTitle("��λ");
		this.f_countDecimal = field = this.table.newField(FN_countDecimal, TypeFactory.INT);
		field.setTitle("��ƷС��λ��");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("����");
		this.f_num = field = this.table.newField(FN_num, TypeFactory.NUMERIC(17,5));
		field.setTitle("����");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���");
		this.f_discount = field = this.table.newField(FN_discount, TypeFactory.NUMERIC(6,5));
		field.setTitle("�ۿ���");
		this.f_disAmount = field = this.table.newField(FN_disAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ۿ۶�");
		this.f_promotionId = field = this.table.newField(FN_promotionId, TypeFactory.GUID);
		field.setTitle("������ƷGuid");
		this.table.newIndex("SA_SALES_ORDER_ITEM_1",f_tenantsId,f_orderId);
	}

}
