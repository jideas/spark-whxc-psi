package com.spark.psi.base.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_sa_dictionary_manage extends TableDeclarator {

	public static final String TABLE_NAME ="sa_dictionary_manage";

	public final TableFieldDefine f_typeName;
	public final TableFieldDefine f_typeTitle;
	public final TableFieldDefine f_installDate;
	public final TableFieldDefine f_installPerson;
	public final TableFieldDefine f_parentname;

	public static final String FN_typeName ="typeName";
	public static final String FN_typeTitle ="typeTitle";
	public static final String FN_installDate ="installDate";
	public static final String FN_installPerson ="installPerson";
	public static final String FN_parentname ="parentname";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_sa_dictionary_manage() {
		super(TABLE_NAME);
		this.table.setDescription(" 存放所有的数据字典类型");
		this.table.setTitle("数据字典管理表 ");
		TableFieldDeclare field;
		this.f_typeName = field = this.table.newPrimaryField(FN_typeName, TypeFactory.NVARCHAR(20));
		field.setTitle("数据字典类型名");
		field.setKeepValid(true);
		this.f_typeTitle = field = this.table.newField(FN_typeTitle, TypeFactory.NVARCHAR(20));
		field.setTitle("数据字典标题");
		this.f_installDate = field = this.table.newField(FN_installDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_installPerson = field = this.table.newField(FN_installPerson, TypeFactory.GUID);
		field.setTitle("创建人");
		this.f_parentname = field = this.table.newField(FN_parentname, TypeFactory.NVARCHAR(20));
		field.setTitle("父级名称");
	}

}
