package com.spark.psi.storage.store.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_STOREAGE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_STOREAGE";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_initCount;
	public final TableFieldDefine f_initAmount;
	public final TableFieldDefine f_initUnitCost;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsProperty;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_goodsUnitCost;
	public final TableFieldDefine f_goodsStoreAmount;
	public final TableFieldDefine f_onWayCount;
	public final TableFieldDefine f_toDeliverCount;
	public final TableFieldDefine f_storeUpper;
	public final TableFieldDefine f_storeFloor;
	public final TableFieldDefine f_storeAmountUpper;
	public final TableFieldDefine f_storeType;
	public final TableFieldDefine f_isInit;
	public final TableFieldDefine f_lockedCount;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_initCount ="initCount";
	public static final String FN_initAmount ="initAmount";
	public static final String FN_initUnitCost ="initUnitCost";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsProperty ="goodsProperty";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_goodsUnitCost ="goodsUnitCost";
	public static final String FN_goodsStoreAmount ="goodsStoreAmount";
	public static final String FN_onWayCount ="onWayCount";
	public static final String FN_toDeliverCount ="toDeliverCount";
	public static final String FN_storeUpper ="storeUpper";
	public static final String FN_storeFloor ="storeFloor";
	public static final String FN_storeAmountUpper ="storeAmountUpper";
	public static final String FN_storeType ="storeType";
	public static final String FN_isInit ="isInit";
	public static final String FN_lockedCount ="lockedCount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_STORE_STOREAGE() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("�ֿ�GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
		this.f_initCount = field = this.table.newField(FN_initCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��ʼ������");
		this.f_initAmount = field = this.table.newField(FN_initAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��ʼ�����");
		this.f_initUnitCost = field = this.table.newField(FN_initUnitCost, TypeFactory.NUMERIC(17,2));
		field.setTitle("��ʼ����Ʒ���ƽ���ɱ�");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(100));
		field.setTitle("�����Ʒ����Ʒ������");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("�����Ʒ����Ʒ�����");
		this.f_goodsProperty = this.table.newField(FN_goodsProperty, TypeFactory.VARCHAR(1000));
		this.f_goodsUnit = this.table.newField(FN_goodsUnit, TypeFactory.VARCHAR(12));
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��Ʒ�������");
		this.f_goodsUnitCost = field = this.table.newField(FN_goodsUnitCost, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ʒ��浥λ�ɱ�");
		this.f_goodsStoreAmount = field = this.table.newField(FN_goodsStoreAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ʒ�����");
		this.f_onWayCount = field = this.table.newField(FN_onWayCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("�ɹ���;����");
		this.f_toDeliverCount = field = this.table.newField(FN_toDeliverCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_storeUpper = field = this.table.newField(FN_storeUpper, TypeFactory.NUMERIC(17,5));
		field.setTitle("��Ʒ�������");
		this.f_storeFloor = field = this.table.newField(FN_storeFloor, TypeFactory.NUMERIC(17,5));
		field.setTitle("��Ʒ�������");
		this.f_storeAmountUpper = field = this.table.newField(FN_storeAmountUpper, TypeFactory.NUMERIC(17,2));
		field.setTitle("���������");
		this.f_storeType = field = this.table.newField(FN_storeType, TypeFactory.CHAR(2));
		field.setTitle("�������");
		this.f_isInit = field = this.table.newField(FN_isInit, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ��ʼ��");
		this.f_lockedCount = field = this.table.newField(FN_lockedCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.table.newIndex("index_1",f_tenantsGuid,f_storeGuid,f_goodsGuid);
	}

}
