package com.spark.psi.storage.messagetip;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_SA_COMM_MESSAGETIPTEMPLATE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_MESSAGETIPTEMPLATE";

	public final TableFieldDefine f_templateType;
	public final TableFieldDefine f_prefix;
	public final TableFieldDefine f_suffix;

	public static final String FN_templateType ="templateType";
	public static final String FN_prefix ="prefix";
	public static final String FN_suffix ="suffix";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_MESSAGETIPTEMPLATE() {
		super(TABLE_NAME);
		this.table.setTitle("消息提醒显示模板");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_templateType = field = this.table.newField(FN_templateType, TypeFactory.VARCHAR(100));
		field.setTitle("模板类型");
		this.f_prefix = field = this.table.newField(FN_prefix, TypeFactory.NVARCHAR(400));
		field.setTitle("前缀");
		this.f_suffix = field = this.table.newField(FN_suffix, TypeFactory.NVARCHAR(400));
		field.setTitle("后缀");
		IndexDeclare index;
		index = this.table.newIndex("INDEX_MESSAGETIP_TEMPLATE",f_templateType);
		index.setUnique(true);
	}

}
