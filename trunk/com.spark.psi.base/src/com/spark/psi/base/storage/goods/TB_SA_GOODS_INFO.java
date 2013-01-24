package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_INFO";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_salePrice;
	public final TableFieldDefine f_goodsStatus;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_deleteFlag;
	public final TableFieldDefine f_refFlag;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_tipsType;
	public final TableFieldDefine f_setPriceFlag;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_salePrice ="salePrice";
	public static final String FN_goodsStatus ="goodsStatus";
	public static final String FN_remark ="remark";
	public static final String FN_deleteFlag ="deleteFlag";
	public static final String FN_refFlag ="refFlag";
	public static final String FN_createDate ="createDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_tipsType ="tipsType";
	public static final String FN_setPriceFlag ="setPriceFlag";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_GOODS_INFO() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ʒ������Ϣ");
		this.table.setTitle("��Ʒ��Ϣ�� ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setDescription("�����⻧");
		field.setTitle("�⻧GUID");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("��Ʒ���");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setDescription("������Ʒ������Ϣ");
		field.setTitle("��Ʒ����GUID");
		this.f_salePrice = field = this.table.newField(FN_salePrice, TypeFactory.NUMERIC(17,2));
		field.setDescription("Ĭ��Ϊ0��0��ʾû�����ø��ֶ�");
		field.setTitle("ͳһ���ۼ۸�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsStatus = field = this.table.newField(FN_goodsStatus, TypeFactory.CHAR(2));
		field.setDescription("ö��(01���ۡ�02ͣ�ۡ�03������������ͣ��)");
		field.setTitle("��Ʒ״̬");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_deleteFlag = field = this.table.newField(FN_deleteFlag, TypeFactory.BOOLEAN);
		field.setDescription("1��ɾ����0����ɾ����Ĭ��Ϊ1");
		field.setTitle("ɾ����־");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_refFlag = field = this.table.newField(FN_refFlag, TypeFactory.BOOLEAN);
		field.setDescription("1�ѹ�����0δ������Ĭ��Ϊ0");
		field.setTitle("������ʶ");
		field.setDefault(ConstExpression.builder.expOf(false));
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
		this.f_setPriceFlag = field = this.table.newField(FN_setPriceFlag, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����������ۼ۸�");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.table.newIndex("SA_GOODS_INFO_ONE",f_tenantsGuid,f_goodsStatus,f_setPriceFlag);
	}

}
