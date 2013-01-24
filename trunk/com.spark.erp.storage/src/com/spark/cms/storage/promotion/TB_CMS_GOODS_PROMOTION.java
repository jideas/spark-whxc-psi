package com.spark.cms.storage.promotion;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_GOODS_PROMOTION extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_GOODS_PROMOTION";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_beginTime;
	public final TableFieldDefine f_endTime;
	public final TableFieldDefine f_disRate;
	public final TableFieldDefine f_pcount;
	public final TableFieldDefine f_isActiving;
	public final TableFieldDefine f_saledCount;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_beginTime ="beginTime";
	public static final String FN_endTime ="endTime";
	public static final String FN_disRate ="disRate";
	public static final String FN_pcount ="pcount";
	public static final String FN_isActiving ="isActiving";
	public static final String FN_saledCount ="saledCount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_GOODS_PROMOTION() {
		super(TABLE_NAME);
		this.table.setTitle("��Ʒ����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("goodsId");
		this.f_beginTime = field = this.table.newField(FN_beginTime, TypeFactory.LONG);
		field.setTitle("��ʼʱ��");
		this.f_endTime = field = this.table.newField(FN_endTime, TypeFactory.LONG);
		field.setTitle("����ʱ��");
		this.f_disRate = field = this.table.newField(FN_disRate, TypeFactory.NUMERIC(6,4));
		field.setTitle("�ۿ���");
		this.f_pcount = field = this.table.newField(FN_pcount, TypeFactory.NUMERIC(10,2));
		field.setTitle("��������");
		this.f_isActiving = field = this.table.newField(FN_isActiving, TypeFactory.BOOLEAN);
		field.setTitle("״̬");
		this.f_saledCount = field = this.table.newField(FN_saledCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("����������");
	}

}
