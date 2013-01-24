package com.spark.cms.storage.card;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_CARD_CONFIG extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_CARD_CONFIG";

	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_promotionType;
	public final TableFieldDefine f_giftId;
	public final TableFieldDefine f_realValue;
	public final TableFieldDefine f_giftLastDate;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;

	public static final String FN_amount ="amount";
	public static final String FN_promotionType ="promotionType";
	public static final String FN_giftId ="giftId";
	public static final String FN_realValue ="realValue";
	public static final String FN_giftLastDate ="giftLastDate";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_CARD_CONFIG() {
		super(TABLE_NAME);
		this.table.setTitle("��ֵ����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(10,2));
		field.setTitle("��ֵ");
		this.f_promotionType = field = this.table.newField(FN_promotionType, TypeFactory.CHAR(2));
		field.setTitle("������ʽ");
		this.f_giftId = field = this.table.newField(FN_giftId, TypeFactory.GUID);
		field.setTitle("��Ʒid");
		this.f_realValue = field = this.table.newField(FN_realValue, TypeFactory.NUMERIC(10,0));
		field.setTitle("ֵ������������Ʒ�����ͽ�");
		this.f_giftLastDate = field = this.table.newField(FN_giftLastDate, TypeFactory.DATE);
		field.setTitle("��Ʒ��ֹ����");
		this.f_beginDate = field = this.table.newField(FN_beginDate, TypeFactory.DATE);
		field.setTitle("��ʼ����");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("��������");
	}

}
