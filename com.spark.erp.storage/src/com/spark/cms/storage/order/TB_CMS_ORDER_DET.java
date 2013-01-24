package com.spark.cms.storage.order;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_CMS_ORDER_DET extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_ORDER_DET";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_goodsScale;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_planPrice;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_disRate;
	public final TableFieldDefine f_disAmount;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_promotionId;
	public final TableFieldDefine f_vantages;
	public final TableFieldDefine f_vantagesType;
	public final TableFieldDefine f_vantagesCost;
	public final TableFieldDefine f_vantagesGoods;

	public static final String FN_billsId ="billsId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_goodsScale ="goodsScale";
	public static final String FN_price ="price";
	public static final String FN_planPrice ="planPrice";
	public static final String FN_count ="count";
	public static final String FN_disRate ="disRate";
	public static final String FN_disAmount ="disAmount";
	public static final String FN_amount ="amount";
	public static final String FN_promotionId ="promotionId";
	public static final String FN_vantages ="vantages";
	public static final String FN_vantagesType ="vantagesType";
	public static final String FN_vantagesCost ="vantagesCost";
	public static final String FN_vantagesGoods ="vantagesGoods";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_ORDER_DET() {
		super(TABLE_NAME);
		this.table.setTitle("������ϸ");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("���۶���GUID");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("��ƷID");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("��Ʒ����");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ���");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(10));
		field.setTitle("��λ");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("��ƷС��λ��");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("����");
		this.f_planPrice = this.table.newField(FN_planPrice, TypeFactory.NUMERIC(17,2));
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("����");
		this.f_disRate = field = this.table.newField(FN_disRate, TypeFactory.NUMERIC(6,0));
		field.setTitle("�ۿ���");
		this.f_disAmount = field = this.table.newField(FN_disAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ۿ۶�");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���");
		this.f_promotionId = field = this.table.newField(FN_promotionId, TypeFactory.GUID);
		field.setTitle("������ƷGuid");
		this.f_vantages = field = this.table.newField(FN_vantages, TypeFactory.NUMERIC(17,0));
		field.setTitle("�ͻ���");
		this.f_vantagesType = field = this.table.newField(FN_vantagesType, TypeFactory.CHAR(1));
		field.setTitle("�������ͣ�0,1,2�����֣�");
		this.f_vantagesCost = field = this.table.newField(FN_vantagesCost, TypeFactory.NUMERIC(17,0));
		field.setTitle("���Ļ���");
		this.f_vantagesGoods = field = this.table.newField(FN_vantagesGoods, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ�����̳���Ʒ");
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
