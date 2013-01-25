package com.spark.psi.storage.index;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_COMM_NOTE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_NOTE";

	public final TableFieldDefine f_noteText;

	public static final String FN_noteText ="noteText";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_NOTE() {
		super(TABLE_NAME);
		this.table.setDescription("随手记");
		this.table.setTitle("随手记");
		TableFieldDeclare field;
		this.f_noteText = field = this.table.newField(FN_noteText, TypeFactory.VARCHAR(255));
		field.setTitle("文本内容");
	}

}
