package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_GOODS_TYPE_PROPERTY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE_PROPERTY";

	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_propertyName;
	public final TableFieldDefine f_propertyInputType;
	public final TableFieldDefine f_propertyOrder;

	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_propertyName ="propertyName";
	public static final String FN_propertyInputType ="propertyInputType";
	public static final String FN_propertyOrder ="propertyOrder";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_GOODS_TYPE_PROPERTY() {
		super(TABLE_NAME);
		this.table.setDescription("存放商品分类的属性");
		this.table.setTitle("商品分类属性表 ");
		TableFieldDeclare field;
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setTitle("商品分类GUID");
		this.f_propertyName = field = this.table.newField(FN_propertyName, TypeFactory.NVARCHAR(12));
		field.setTitle("商品分类属性名称");
		this.f_propertyInputType = field = this.table.newField(FN_propertyInputType, TypeFactory.CHAR(2));
		field.setDescription("枚举（01手动录入、02下拉选择）");
		field.setTitle("属性录入类型");
		this.f_propertyOrder = field = this.table.newField(FN_propertyOrder, TypeFactory.INT);
		field.setDescription("数据为从1开始的数字");
		field.setTitle("属性显示顺序");
		this.table.newIndex("SA_GOODS_TYPE_TYPEGUID",f_goodsTypeGuid);
	}

}
