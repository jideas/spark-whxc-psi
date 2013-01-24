package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_TYPE_TONGJI extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE_TONGJI";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_parentGuid;
	public final TableFieldDefine f_storeAmountUpper;
	public final TableFieldDefine f_recentStoreAmount;
	public final TableFieldDefine f_saleGoodsCount;
	public final TableFieldDefine f_stopGoodsCount;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_parentGuid ="parentGuid";
	public static final String FN_storeAmountUpper ="storeAmountUpper";
	public static final String FN_recentStoreAmount ="recentStoreAmount";
	public static final String FN_saleGoodsCount ="saleGoodsCount";
	public static final String FN_stopGoodsCount ="stopGoodsCount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_GOODS_TYPE_TONGJI() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ʒ�����ͳ����Ϣ");
		this.table.setTitle("��Ʒ����ͳ�Ʊ�(�м��) ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧GUID");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_parentGuid = field = this.table.newField(FN_parentGuid, TypeFactory.GUID);
		field.setTitle("������GUID");
		this.f_storeAmountUpper = field = this.table.newField(FN_storeAmountUpper, TypeFactory.NUMERIC(17,2));
		field.setTitle("����������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_recentStoreAmount = field = this.table.newField(FN_recentStoreAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�����浱ǰ���");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_saleGoodsCount = field = this.table.newField(FN_saleGoodsCount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�÷�����������Ʒ����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_stopGoodsCount = field = this.table.newField(FN_stopGoodsCount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�÷�����ͣ����Ʒ����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		IndexDeclare index;
		index = this.table.newIndex("SA_GOODS_TYPE_TONGJI1",f_goodsTypeGuid);
		index.setUnique(true);
	}

}
