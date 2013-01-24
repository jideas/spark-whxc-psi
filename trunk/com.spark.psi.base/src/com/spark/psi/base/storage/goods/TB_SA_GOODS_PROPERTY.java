package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_PROPERTY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_PROPERTY";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_goodsCountDigit;
	public final TableFieldDefine f_salePrice;
	public final TableFieldDefine f_goodsPropertyState;
	public final TableFieldDefine f_deleteFlag;
	public final TableFieldDefine f_reflag;
	public final TableFieldDefine f_propertyValue;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_avgBuyPrice;
	public final TableFieldDefine f_totalStoreUpper;
	public final TableFieldDefine f_totalStoreFlore;
	public final TableFieldDefine f_totalStoreAmount;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_tipsType;
	public final TableFieldDefine f_recentPurchasePrice;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_goodsCountDigit ="goodsCountDigit";
	public static final String FN_salePrice ="salePrice";
	public static final String FN_goodsPropertyState ="goodsPropertyState";
	public static final String FN_deleteFlag ="deleteFlag";
	public static final String FN_reflag ="reflag";
	public static final String FN_propertyValue ="propertyValue";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_avgBuyPrice ="avgBuyPrice";
	public static final String FN_totalStoreUpper ="totalStoreUpper";
	public static final String FN_totalStoreFlore ="totalStoreFlore";
	public static final String FN_totalStoreAmount ="totalStoreAmount";
	public static final String FN_createDate ="createDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_tipsType ="tipsType";
	public static final String FN_recentPurchasePrice ="recentPurchasePrice";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_GOODS_PROPERTY() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ʒ������Ϣ");
		this.table.setTitle("��Ʒ������Ϣ�� ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setDescription("�����⻧��");
		field.setTitle("�⻧GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setDescription("������Ʒ��");
		field.setTitle("��ƷGUID");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("��Ʒ���");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setDescription("������Ʒ������Ϣ");
		field.setTitle("��Ʒ����GUID");
		this.f_goodsCountDigit = field = this.table.newField(FN_goodsCountDigit, TypeFactory.INT);
		field.setTitle("��Ʒ��ʾλ��");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_salePrice = field = this.table.newField(FN_salePrice, TypeFactory.NUMERIC(17,2));
		field.setDescription("Ĭ��Ϊ0����ʾû������");
		field.setTitle("���ۼ۸�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsPropertyState = field = this.table.newField(FN_goodsPropertyState, TypeFactory.CHAR(2));
		field.setDescription("ö��(01���ۡ�02ͣ��)��Ĭ��Ϊ01");
		field.setTitle("��Ʒ����״̬");
		this.f_deleteFlag = field = this.table.newField(FN_deleteFlag, TypeFactory.BOOLEAN);
		field.setDescription("1��ɾ����0����ɾ����Ĭ��Ϊ1");
		field.setTitle("ɾ����־");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_reflag = field = this.table.newField(FN_reflag, TypeFactory.BOOLEAN);
		field.setDescription("1�ѹ�����0δ������Ĭ��Ϊ0");
		field.setTitle("������ʶ");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_propertyValue = field = this.table.newField(FN_propertyValue, TypeFactory.TEXT);
		field.setDescription("��JSON�ķ�ʽ����¼��Щ���Եļ�ֵ�ԣ����м�Ϊ�������ơ�ֵΪ¼������ݣ����磺{����λ��:������,{����ɫ��:{��01��:���족}}}");
		field.setTitle("����ֵ");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(40));
		field.setTitle("��Ʒ��λ");
		this.f_avgBuyPrice = field = this.table.newField(FN_avgBuyPrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("ƽ���ɹ��۸�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_totalStoreUpper = field = this.table.newField(FN_totalStoreUpper, TypeFactory.NUMERIC(17,5));
		field.setTitle("�ܿ����������");
		this.f_totalStoreFlore = field = this.table.newField(FN_totalStoreFlore, TypeFactory.NUMERIC(17,5));
		field.setTitle("�ܿ����������");
		this.f_totalStoreAmount = field = this.table.newField(FN_totalStoreAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ܿ�����޽��");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("���ݲ���ʱ��");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("���ݴ�����");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("��������޸�ʱ��");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("��������޸���");
		this.f_tipsType = field = this.table.newField(FN_tipsType, TypeFactory.CHAR(2));
		field.setTitle("��ƷԤ������");
		this.f_recentPurchasePrice = field = this.table.newField(FN_recentPurchasePrice, TypeFactory.VARCHAR(10));
		field.setDescription("������ЧΪ׼");
		field.setTitle("����ɹ��۸�");
		this.table.newIndex("SA_GOODS_PROPERTY_ONE",f_tenantsGuid,f_goodsPropertyState,f_goodsGuid);
	}

}
