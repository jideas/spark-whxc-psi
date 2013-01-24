package com.spark.cms.storage.promotion;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_ORDER_PROMOTION extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_ORDER_PROMOTION";

	public final TableFieldDefine f_isActiving;
	public final TableFieldDefine f_beginAmount;
	public final TableFieldDefine f_endAmount;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_vantages;
	public final TableFieldDefine f_isFreeDelivery;

	public static final String FN_isActiving ="isActiving";
	public static final String FN_beginAmount ="beginAmount";
	public static final String FN_endAmount ="endAmount";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_vantages ="vantages";
	public static final String FN_isFreeDelivery ="isFreeDelivery";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_ORDER_PROMOTION() {
		super(TABLE_NAME);
		this.table.setTitle("��������");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_isActiving = field = this.table.newField(FN_isActiving, TypeFactory.BOOLEAN);
		field.setTitle("״̬");
		this.f_beginAmount = field = this.table.newField(FN_beginAmount, TypeFactory.NUMERIC(10,2));
		field.setTitle("��ʼ���");
		this.f_endAmount = field = this.table.newField(FN_endAmount, TypeFactory.NUMERIC(10,2));
		field.setTitle("�������");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("��Ʒid");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("����");
		this.f_vantages = field = this.table.newField(FN_vantages, TypeFactory.NUMERIC(10,0));
		field.setTitle("����ֵ");
		this.f_isFreeDelivery = field = this.table.newField(FN_isFreeDelivery, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����˷Ѵ���");
	}

}
