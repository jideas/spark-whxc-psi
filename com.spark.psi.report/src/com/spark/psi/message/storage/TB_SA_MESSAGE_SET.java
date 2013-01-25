package com.spark.psi.message.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_MESSAGE_SET extends TableDeclarator {

	public static final String TABLE_NAME ="SA_MESSAGE_SET";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_setType;
	public final TableFieldDefine f_messageType;
	public final TableFieldDefine f_userId;
	public final TableFieldDefine f_bvalue;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_setType ="setType";
	public static final String FN_messageType ="messageType";
	public static final String FN_userId ="userId";
	public static final String FN_bvalue ="bvalue";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_MESSAGE_SET() {
		super(TABLE_NAME);
		this.table.setTitle("��Ϣ��������");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_setType = field = this.table.newField(FN_setType, TypeFactory.CHAR(4));
		field.setTitle("��������");
		this.f_messageType = field = this.table.newField(FN_messageType, TypeFactory.CHAR(4));
		field.setTitle("��Ϣ����");
		this.f_userId = field = this.table.newField(FN_userId, TypeFactory.GUID);
		field.setTitle("������id");
		this.f_bvalue = field = this.table.newField(FN_bvalue, TypeFactory.BOOLEAN);
		field.setTitle("����ֵ");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.table.newIndex("SA_MESSAGE_INDEX",f_userId,f_messageType);
	}

}
