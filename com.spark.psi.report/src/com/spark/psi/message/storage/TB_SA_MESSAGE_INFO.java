package com.spark.psi.message.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_MESSAGE_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="SA_MESSAGE_INFO";

	public final TableFieldDefine f_messageType;
	public final TableFieldDefine f_relaObjId;
	public final TableFieldDefine f_templateCode;
	public final TableFieldDefine f_stringValue1;
	public final TableFieldDefine f_stringValue2;
	public final TableFieldDefine f_stringValue3;

	public static final String FN_messageType ="messageType";
	public static final String FN_relaObjId ="relaObjId";
	public static final String FN_templateCode ="templateCode";
	public static final String FN_stringValue1 ="stringValue1";
	public static final String FN_stringValue2 ="stringValue2";
	public static final String FN_stringValue3 ="stringValue3";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_MESSAGE_INFO() {
		super(TABLE_NAME);
		this.table.setCategory("报表主体");
		TableFieldDeclare field;
		this.f_messageType = field = this.table.newField(FN_messageType, TypeFactory.CHAR(4));
		field.setTitle("消息类型");
		this.f_relaObjId = field = this.table.newField(FN_relaObjId, TypeFactory.GUID);
		field.setTitle("关联对象id");
		this.f_templateCode = field = this.table.newField(FN_templateCode, TypeFactory.CHAR(4));
		field.setTitle("显示模板code");
		this.f_stringValue1 = field = this.table.newField(FN_stringValue1, TypeFactory.VARCHAR(50));
		field.setTitle("内容");
		this.f_stringValue2 = field = this.table.newField(FN_stringValue2, TypeFactory.VARCHAR(50));
		field.setTitle("内容");
		this.f_stringValue3 = field = this.table.newField(FN_stringValue3, TypeFactory.VARCHAR(50));
		field.setTitle("内容");
		this.table.newIndex("SA_MESSAGE_INDEX",f_relaObjId,f_messageType);
	}

}
