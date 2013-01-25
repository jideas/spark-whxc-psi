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

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_MESSAGE_MAPPING() {
		super(TABLE_NAME);
		this.table.setTitle("消息人员映射表");
		this.table.setCategory("报表主体");
		TableFieldDeclare field;
		this.f_userId = field = this.table.newField(FN_userId, TypeFactory.GUID);
		field.setTitle("消息接收人");
		this.f_messageId = field = this.table.newField(FN_messageId, TypeFactory.GUID);
		field.setTitle("消息主体");
		this.f_messageType = field = this.table.newField(FN_messageType, TypeFactory.CHAR(4));
		field.setTitle("消息类型");
		this.f_startTime = field = this.table.newField(FN_startTime, TypeFactory.DATE);
		field.setTitle("开始时间");
		this.f_endTime = field = this.table.newField(FN_endTime, TypeFactory.DATE);
		field.setTitle("停止时间");
		this.f_showFlag = field = this.table.newField(FN_showFlag, TypeFactory.BOOLEAN);
		field.setTitle("是否显示过");
		field.setKeepValid(true);
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
