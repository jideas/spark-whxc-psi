package com.spark.psi.storage.customer;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_AUTHOR_GOODTEMPLETE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_AUTHOR_GOODTEMPLETE";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_authorPrice;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_goodsStatus;
	public final TableFieldDefine f_goodsNum;
	public final TableFieldDefine f_proGoodsNo;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_authorPrice ="authorPrice";
	public static final String FN_remark ="remark";
	public static final String FN_goodsStatus ="goodsStatus";
	public static final String FN_goodsNum ="goodsNum";
	public static final String FN_proGoodsNo ="proGoodsNo";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_AUTHOR_GOODTEMPLETE() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ���Ա��");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.VARCHAR(1000));
		field.setTitle("��Ʒ����");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.VARCHAR(12));
		field.setTitle("��λ");
		this.f_authorPrice = field = this.table.newField(FN_authorPrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ȩ�۸�");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.VARCHAR(400));
		field.setTitle("˵��");
		this.f_goodsStatus = field = this.table.newField(FN_goodsStatus, TypeFactory.CHAR(2));
		field.setTitle("��Ʒ״̬");
		this.f_goodsNum = field = this.table.newField(FN_goodsNum, TypeFactory.INT);
		field.setTitle("��Ʒλ��");
		this.f_proGoodsNo = field = this.table.newField(FN_proGoodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("��Ȩ��Ʒ���");
	}

}
