package com.spark.psi.message.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_MESSAGE_MAPPING extends TableDeclarator {

	public static final String TABLE_NAME ="SA_MESSAGE_MAPPING";

	public final TableFieldDefine f_userId;
	public final TableFieldDefine f_messageId;
	public final TableFieldDefine f_messageType;
	public final TableFieldDefine f_startTime;
	public final TableFieldDefine f_endTime;
	public final TableFieldDefine f_showFlag;

	public static final String FN_userId ="userId";
	public static final String FN_messageId ="messageId";
	public static final String FN_messageType ="messageType";
	public static final String FN_startTime ="startTime";
	public static final String FN_endTime ="endTime";
	public static final String FN_showFlag ="showFlag";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_MESSAGE_MAPPING() {
		super(TABLE_NAME);
		this.table.setTitle("��Ϣ��Աӳ���");
		this.table.setCategory("��������");
		TableFieldDeclare field;
		this.f_userId = field = this.table.newField(FN_userId, TypeFactory.GUID);
		field.setTitle("��Ϣ������");
		this.f_messageId = field = this.table.newField(FN_messageId, TypeFactory.GUID);
		field.setTitle("��Ϣ����");
		this.f_messageType = field = this.table.newField(FN_messageType, TypeFactory.CHAR(4));
		field.setTitle("��Ϣ����");
		this.f_startTime = field = this.table.newField(FN_startTime, TypeFactory.DATE);
		field.setTitle("��ʼʱ��");
		this.f_endTime = field = this.table.newField(FN_endTime, TypeFactory.DATE);
		field.setTitle("ֹͣʱ��");
		this.f_showFlag = field = this.table.newField(FN_showFlag, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ���ʾ��");
		field.setKeepValid(true);
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
