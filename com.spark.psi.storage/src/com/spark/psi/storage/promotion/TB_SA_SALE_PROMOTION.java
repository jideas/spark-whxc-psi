package com.spark.psi.storage.promotion;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SALE_PROMOTION extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALE_PROMOTION";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsNamePY;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_createPersonGuid;
	public final TableFieldDefine f_cpName;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_promotionCount;
	public final TableFieldDefine f_saleCount;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_promotionPrice;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_promotionCause;
	public final TableFieldDefine f_returnCause;
	public final TableFieldDefine f_checkDate;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsNamePY ="goodsNamePY";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_createPersonGuid ="createPersonGuid";
	public static final String FN_cpName ="cpName";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_promotionCount ="promotionCount";
	public static final String FN_saleCount ="saleCount";
	public static final String FN_price ="price";
	public static final String FN_promotionPrice ="promotionPrice";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";
	public static final String FN_promotionCause ="promotionCause";
	public static final String FN_returnCause ="returnCause";
	public static final String FN_checkDate ="checkDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_SALE_PROMOTION() {
		super(TABLE_NAME);
		this.table.setDescription("������Ʒ");
		this.table.setTitle("������Ʒ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_goodsNamePY = field = this.table.newField(FN_goodsNamePY, TypeFactory.VARCHAR(50));
		field.setTitle("����ƴ��");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.VARCHAR(1000));
		field.setTitle("��Ʒ����");
		this.f_createPersonGuid = field = this.table.newField(FN_createPersonGuid, TypeFactory.GUID);
		field.setTitle("������GUID");
		this.f_cpName = field = this.table.newField(FN_cpName, TypeFactory.VARCHAR(50));
		field.setTitle("����������");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_promotionCount = field = this.table.newField(FN_promotionCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_saleCount = field = this.table.newField(FN_saleCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("ԭ��");
		this.f_promotionPrice = field = this.table.newField(FN_promotionPrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("��������");
		this.f_beginDate = field = this.table.newField(FN_beginDate, TypeFactory.DATE);
		field.setTitle("��ʼ����");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_promotionCause = field = this.table.newField(FN_promotionCause, TypeFactory.VARCHAR(500));
		field.setTitle("����ԭ��");
		this.f_returnCause = field = this.table.newField(FN_returnCause, TypeFactory.VARCHAR(500));
		field.setTitle("�˻�ԭ��");
		this.f_checkDate = field = this.table.newField(FN_checkDate, TypeFactory.DATE);
		field.setTitle("���ʱ��");
		this.table.newIndex("index_promotion_1",f_tenantsGuid,f_status,f_deptGuid);
		this.table.newIndex("index_promotion_2",f_tenantsGuid,f_goodsGuid,f_status);
	}

}
