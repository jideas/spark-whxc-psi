package com.spark.psi.storage.index;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_COMM_INDEX_TOOL extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_INDEX_TOOL";

	public final TableFieldDefine f_name;
	public final TableFieldDefine f_x;
	public final TableFieldDefine f_y;
	public final TableFieldDefine f_userid;

	public static final String FN_name ="name";
	public static final String FN_x ="x";
	public static final String FN_y ="y";
	public static final String FN_userid ="userid";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_INDEX_TOOL() {
		super(TABLE_NAME);
		this.table.setDescription("桌面图标");
		this.table.setTitle("桌面图标");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_name = field = this.table.newField(FN_name, TypeFactory.VARCHAR(255));
		field.setTitle("小工具名称");
		this.f_x = field = this.table.newField(FN_x, TypeFactory.INT);
		field.setTitle("X坐标");
		this.f_y = field = this.table.newField(FN_y, TypeFactory.INT);
		field.setTitle("Y坐标");
		this.f_userid = field = this.table.newField(FN_userid, TypeFactory.GUID);
		field.setTitle("用户GUID");
	}

}
