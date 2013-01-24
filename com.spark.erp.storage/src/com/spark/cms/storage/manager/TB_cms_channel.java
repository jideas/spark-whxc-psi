package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_cms_channel extends TableDeclarator {

	public static final String TABLE_NAME ="cms_channel";

	public final TableFieldDefine f_code;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_floorId;
	public final TableFieldDefine f_channelType;
	public final TableFieldDefine f_pageType;
	public final TableFieldDefine f_isMainMenu;

	public static final String FN_code ="code";
	public static final String FN_name ="name";
	public static final String FN_floorId ="floorId";
	public static final String FN_channelType ="channelType";
	public static final String FN_pageType ="pageType";
	public static final String FN_isMainMenu ="isMainMenu";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_cms_channel() {
		super(TABLE_NAME);
		this.table.setTitle("栏目管理");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(20));
		field.setTitle("栏目编号");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(50));
		field.setTitle("栏目名称");
		this.f_floorId = field = this.table.newField(FN_floorId, TypeFactory.GUID);
		field.setTitle("楼层id");
		this.f_channelType = field = this.table.newField(FN_channelType, TypeFactory.CHAR(2));
		field.setTitle("类型(内容||广告||商品)");
		this.f_pageType = field = this.table.newField(FN_pageType, TypeFactory.CHAR(2));
		field.setTitle("页面级别");
		this.f_isMainMenu = field = this.table.newField(FN_isMainMenu, TypeFactory.BOOLEAN);
		field.setTitle("主菜单栏标记");
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
