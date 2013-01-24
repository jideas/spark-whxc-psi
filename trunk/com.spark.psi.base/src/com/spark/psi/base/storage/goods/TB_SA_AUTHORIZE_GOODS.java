package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_AUTHORIZE_GOODS extends TableDeclarator {

	public static final String TABLE_NAME ="SA_AUTHORIZE_GOODS";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_proTenantsGuid;
	public final TableFieldDefine f_providerGuid;
	public final TableFieldDefine f_proGoodsPropertyGuid;
	public final TableFieldDefine f_proGoodsProperty;
	public final TableFieldDefine f_proGoodsName;
	public final TableFieldDefine f_proSalePrice;
	public final TableFieldDefine f_priceA;
	public final TableFieldDefine f_priceB;
	public final TableFieldDefine f_proGoodsStatus;
	public final TableFieldDefine f_goodsPropertyGuid;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_goodsCountDigit;
	public final TableFieldDefine f_proGoodsNo;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_proTenantsGuid ="proTenantsGuid";
	public static final String FN_providerGuid ="providerGuid";
	public static final String FN_proGoodsPropertyGuid ="proGoodsPropertyGuid";
	public static final String FN_proGoodsProperty ="proGoodsProperty";
	public static final String FN_proGoodsName ="proGoodsName";
	public static final String FN_proSalePrice ="proSalePrice";
	public static final String FN_priceA ="priceA";
	public static final String FN_priceB ="priceB";
	public static final String FN_proGoodsStatus ="proGoodsStatus";
	public static final String FN_goodsPropertyGuid ="goodsPropertyGuid";
	public static final String FN_createDate ="createDate";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_goodsCountDigit ="goodsCountDigit";
	public static final String FN_proGoodsNo ="proGoodsNo";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_AUTHORIZE_GOODS() {
		super(TABLE_NAME);
		this.table.setDescription("��Ʒ������");
		this.table.setTitle("��Ź�Ӧ����Ȩ����Ʒ��Ϣ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�����⻧GUID");
		this.f_proTenantsGuid = field = this.table.newField(FN_proTenantsGuid, TypeFactory.GUID);
		field.setTitle("��Ȩ��Ӧ���⻧GUID");
		this.f_providerGuid = field = this.table.newField(FN_providerGuid, TypeFactory.GUID);
		field.setTitle("��Ȩ��Ӧ��GUID");
		this.f_proGoodsPropertyGuid = field = this.table.newField(FN_proGoodsPropertyGuid, TypeFactory.GUID);
		field.setTitle("��Ȩ��Ʒ����GUID");
		this.f_proGoodsProperty = field = this.table.newField(FN_proGoodsProperty, TypeFactory.VARCHAR(1000));
		field.setTitle("��Ȩ��Ʒ����1��������λ");
		this.f_proGoodsName = field = this.table.newField(FN_proGoodsName, TypeFactory.VARCHAR(50));
		field.setTitle("��Ȩ��Ʒ����");
		this.f_proSalePrice = field = this.table.newField(FN_proSalePrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ȩ��Ʒ�۸�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_priceA = field = this.table.newField(FN_priceA, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ԥ���۸����ڴ����۸�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_priceB = field = this.table.newField(FN_priceB, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ԥ���۸���������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_proGoodsStatus = field = this.table.newField(FN_proGoodsStatus, TypeFactory.CHAR(2));
		field.setTitle("��Ȩ��Ʒ״̬");
		field.setDefault(ConstExpression.builder.expOf("02"));
		this.f_goodsPropertyGuid = field = this.table.newField(FN_goodsPropertyGuid, TypeFactory.GUID);
		field.setTitle("������Ʒ����GUID");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("���ݲ���ʱ��");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("��������޸�ʱ��");
		this.f_goodsCountDigit = field = this.table.newField(FN_goodsCountDigit, TypeFactory.INT);
		field.setTitle("��Ʒ��ʾλ��");
		this.f_proGoodsNo = field = this.table.newField(FN_proGoodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("��Ȩ��Ʒ���");
		this.table.newIndex("INDEX_AUTHORIZE_GOODS",f_tenantsGuid,f_providerGuid,f_goodsPropertyGuid);
		this.table.newIndex("SA_AUTHORIZE_GOODS_ONE",f_tenantsGuid,f_proGoodsStatus,f_providerGuid);
	}

}
