package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Produce_FinishedLog extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Produce_FinishedLog";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_goodsScale;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_bomId;
	public final TableFieldDefine f_thistimeCount;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;

	public static final String FN_billsId ="billsId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_goodsScale ="goodsScale";
	public static final String FN_count ="count";
	public static final String FN_bomId ="bomId";
	public static final String FN_thistimeCount ="thistimeCount";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Produce_FinishedLog() {
		super(TABLE_NAME);
		this.table.setTitle("�������������Ʒ��¼");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("��ƷGuid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ���");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("��Ʒ����");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(1000));
		field.setTitle("��Ʒ���");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(12));
		field.setTitle("��λ");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("��ƷС��λ��");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("����");
		this.f_bomId = field = this.table.newField(FN_bomId, TypeFactory.GUID);
		field.setTitle("bom��Id");
		this.f_thistimeCount = field = this.table.newField(FN_thistimeCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("�����������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("����������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����");
	}

}
