package com.spark.cms.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_GOODS_CATEGORY extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_GOODS_CATEGORY";

	public final TableFieldDefine f_categoryNo;
	public final TableFieldDefine f_categoryName;
	public final TableFieldDefine f_parentId;
	public final TableFieldDefine f_path;
	public final TableFieldDefine f_levelNo;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;

	public static final String FN_categoryNo ="categoryNo";
	public static final String FN_categoryName ="categoryName";
	public static final String FN_parentId ="parentId";
	public static final String FN_path ="path";
	public static final String FN_levelNo ="levelNo";
	public static final String FN_createDate ="createDate";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_GOODS_CATEGORY() {
		super(TABLE_NAME);
		this.table.setTitle("网站商品分类");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_categoryNo = field = this.table.newField(FN_categoryNo, TypeFactory.NVARCHAR(25));
		field.setTitle("分类编号");
		this.f_categoryName = field = this.table.newField(FN_categoryName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品分类名称");
		this.f_parentId = field = this.table.newField(FN_parentId, TypeFactory.GUID);
		field.setTitle("父分类GUID");
		this.f_path = field = this.table.newField(FN_path, TypeFactory.VARBINARY(544));
		field.setTitle("节点的路径");
		this.f_levelNo = field = this.table.newField(FN_levelNo, TypeFactory.INT);
		field.setTitle("节点级数");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("数据插入时间");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("数据最后修改时间");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("数据最后修改人");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人");
	}

}
