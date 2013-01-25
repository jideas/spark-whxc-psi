package com.spark.psi.storage.store.dismounting;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_DISMOUNT_DET extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_DISMOUNT_DET";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_dismFlag;
	public final TableFieldDefine f_dismGuid;
	public final TableFieldDefine f_dismCount;
	public final TableFieldDefine f_storeCost;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_money;
	public final TableFieldDefine f_goodsScale;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_dismFlag ="dismFlag";
	public static final String FN_dismGuid ="dismGuid";
	public static final String FN_dismCount ="dismCount";
	public static final String FN_storeCost ="storeCost";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_unit ="unit";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_money ="money";
	public static final String FN_goodsScale ="goodsScale";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_STORE_DISMOUNT_DET() {
		super(TABLE_NAME);
		this.table.setDescription("����װ��ϸ");
		this.table.setTitle("����װ��ϸ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧GUID");
		this.f_dismFlag = field = this.table.newField(FN_dismFlag, TypeFactory.CHAR(2));
		field.setTitle("��װǰ���ʶ");
		this.f_dismGuid = field = this.table.newField(FN_dismGuid, TypeFactory.GUID);
		field.setTitle("��װ�����");
		this.f_dismCount = field = this.table.newField(FN_dismCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��װ����");
		this.f_storeCost = field = this.table.newField(FN_storeCost, TypeFactory.NUMERIC(17,2));
		field.setTitle("��װ�ɱ�");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
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
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("��Ʒ��");
		this.f_money = field = this.table.newField(FN_money, TypeFactory.NUMERIC(17,2));
		field.setTitle("���");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("��Ʒ����");
	}

}
