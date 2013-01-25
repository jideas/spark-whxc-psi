package com.spark.psi.storage.index;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_COMM_MONITOR extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_MONITOR";

	public final TableFieldDefine f_monitors;

	public static final String FN_monitors ="monitors";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_MONITOR() {
		super(TABLE_NAME);
		this.table.setDescription("监控配置");
		this.table.setTitle("监控配置");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_monitors = field = this.table.newField(FN_monitors, TypeFactory.VARCHAR(255));
		field.setTitle("监控项");
	}

}
