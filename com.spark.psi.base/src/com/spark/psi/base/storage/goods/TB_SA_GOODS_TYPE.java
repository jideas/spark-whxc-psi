package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_TYPE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsTypeName;
	public final TableFieldDefine f_parentGuid;
	public final TableFieldDefine f_path;
	public final TableFieldDefine f_yzFlag;
	public final TableFieldDefine f_goodsCountDigit;
	public final TableFieldDefine f_deleteFlag;
	public final TableFieldDefine f_reflag;
	public final TableFieldDefine f_setPropertyFlag;
	public final TableFieldDefine f_properties;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsTypeName ="goodsTypeName";
	public static final String FN_parentGuid ="parentGuid";
	public static final String FN_path ="path";
	public static final String FN_yzFlag ="yzFlag";
	public static final String FN_goodsCountDigit ="goodsCountDigit";
	public static final String FN_deleteFlag ="deleteFlag";
	public static final String FN_reflag ="reflag";
	public static final String FN_setPropertyFlag ="setPropertyFlag";
	public static final String FN_properties ="properties";
	public static final String FN_createDate ="createDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_GOODS_TYPE() {
		super(TABLE_NAME);
		this.table.setDescription("存放商品分类内容,注意：该表新创建时需要加入一条guid为“10000000000000000000000000000001”，名称为“全部”，无上级分类的顶级节点");
		this.table.setTitle("商品分类表 ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setDescription("关联租户");
		field.setTitle("租户GUID");
		this.f_goodsTypeName = field = this.table.newField(FN_goodsTypeName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品分类名称");
		this.f_parentGuid = field = this.table.newField(FN_parentGuid, TypeFactory.GUID);
		field.setTitle("父分类GUID");
		this.f_path = field = this.table.newField(FN_path, TypeFactory.VARBINARY(544));
		field.setDescription("节点从其最主宗节点到它本节点的路径，这个路径最长为544位，也就是最多为17级");
		field.setTitle("节点的路径");
		this.f_yzFlag = field = this.table.newField(FN_yzFlag, TypeFactory.BOOLEAN);
		field.setDescription("1，叶子节点，0非叶子节点");
		field.setTitle("叶子节点标识");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_goodsCountDigit = field = this.table.newField(FN_goodsCountDigit, TypeFactory.INT);
		field.setTitle("商品显示位数");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_deleteFlag = field = this.table.newField(FN_deleteFlag, TypeFactory.BOOLEAN);
		field.setDescription("1可删除、0不可删除，默认为1");
		field.setTitle("删除标志");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_reflag = field = this.table.newField(FN_reflag, TypeFactory.BOOLEAN);
		field.setDescription("1已关联、0未关联，默认为0");
		field.setTitle("关联标识");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_setPropertyFlag = field = this.table.newField(FN_setPropertyFlag, TypeFactory.BOOLEAN);
		field.setDescription("1已设置、0未设置），默认为0");
		field.setTitle("是否已设置属性标识");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_properties = field = this.table.newField(FN_properties, TypeFactory.TEXT);
		field.setTitle("商品属性");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("数据插入时间");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("数据创建人");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("数据最后修改时间");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("数据最后修改人");
		this.table.newIndex("SA_GOODS_TYPE_1",f_parentGuid);
		this.table.newIndex("INDEX_GOODS_TYPE_TENANT",f_tenantsGuid,f_parentGuid);
	}

}
