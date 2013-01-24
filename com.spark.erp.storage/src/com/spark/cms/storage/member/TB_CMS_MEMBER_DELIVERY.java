package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_CMS_MEMBER_DELIVERY extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_DELIVERY";

	public final TableFieldDefine f_member;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_dcount;
	public final TableFieldDefine f_counted;
	public final TableFieldDefine f_lockedDeliveryBalance;

	public static final String FN_member ="member";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_dcount ="dcount";
	public static final String FN_counted ="counted";
	public static final String FN_lockedDeliveryBalance ="lockedDeliveryBalance";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_MEMBER_DELIVERY() {
		super(TABLE_NAME);
		this.table.setTitle("�ͻ���������");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_member = field = this.table.newField(FN_member, TypeFactory.GUID);
		field.setTitle("��Աid");
		this.f_beginDate = field = this.table.newField(FN_beginDate, TypeFactory.DATE);
		field.setTitle("��ʼ����");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_dcount = field = this.table.newField(FN_dcount, TypeFactory.NUMERIC(10,0));
		field.setTitle("����");
		this.f_counted = field = this.table.newField(FN_counted, TypeFactory.NUMERIC(10,0));
		field.setTitle("���ô���");
		this.f_lockedDeliveryBalance = field = this.table.newField(FN_lockedDeliveryBalance, TypeFactory.NUMERIC(17,0));
		field.setTitle("���������ͻ����´���");
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
