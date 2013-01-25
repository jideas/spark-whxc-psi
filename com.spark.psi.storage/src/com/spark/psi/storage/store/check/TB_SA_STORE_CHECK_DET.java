package com.spark.psi.storage.store.check;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_CHECK_DET extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_CHECK_DET";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_checkOrdGuid;
	public final TableFieldDefine f_carryCount;
	public final TableFieldDefine f_realCount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_goodsItemNo;
	public final TableFieldDefine f_goodsItemCode;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_newGoods;
	public final TableFieldDefine f_goodsScale;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_remark ="remark";
	public static final String FN_checkOrdGuid ="checkOrdGuid";
	public static final String FN_carryCount ="carryCount";
	public static final String FN_realCount ="realCount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_unit ="unit";
	public static final String FN_goodsItemNo ="goodsItemNo";
	public static final String FN_goodsItemCode ="goodsItemCode";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_newGoods ="newGoods";
	public static final String FN_goodsScale ="goodsScale";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_STORE_CHECK_DET() {
		super(TABLE_NAME);
		this.table.setDescription("����̵���ϸ");
		this.table.setTitle("����̵���ϸ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.VARCHAR(1000));
		field.setTitle("˵��");
		this.f_checkOrdGuid = field = this.table.newField(FN_checkOrdGuid, TypeFactory.GUID);
		field.setTitle("�̵㵥���");
		this.f_carryCount = field = this.table.newField(FN_carryCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_realCount = field = this.table.newField(FN_realCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("ʵ������");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.VARCHAR(20));
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(50));
		field.setTitle("��Ʒ����");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.VARCHAR(1000));
		field.setTitle("��Ʒ����");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.VARCHAR(20));
		field.setTitle("��λ");
		this.f_goodsItemNo = field = this.table.newField(FN_goodsItemNo, TypeFactory.VARCHAR(20));
		field.setTitle("��Ʒ���");
		this.f_goodsItemCode = this.table.newField(FN_goodsItemCode, TypeFactory.VARCHAR(10));
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_newGoods = field = this.table.newField(FN_newGoods, TypeFactory.CHAR(2));
		field.setTitle("�Ƿ�Ϊ������Ʒ");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("��Ʒ����");
	}

}
