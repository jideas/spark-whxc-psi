package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_GOODS_TYPE_PROJECT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_TYPE_PROJECT";

	public final TableFieldDefine f_propertyGuid;
	public final TableFieldDefine f_projectValue;
	public final TableFieldDefine f_projectKey;
	public final TableFieldDefine f_projectOrder;

	public static final String FN_propertyGuid ="propertyGuid";
	public static final String FN_projectValue ="projectValue";
	public static final String FN_projectKey ="projectKey";
	public static final String FN_projectOrder ="projectOrder";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_GOODS_TYPE_PROJECT() {
		super(TABLE_NAME);
		this.table.setDescription("存放商品分类的属性项目");
		this.table.setTitle("商品分类属性项目表 ");
		TableFieldDeclare field;
		this.f_propertyGuid = field = this.table.newField(FN_propertyGuid, TypeFactory.GUID);
		field.setTitle("属性GUID");
		this.f_projectValue = field = this.table.newField(FN_projectValue, TypeFactory.NVARCHAR(12));
		field.setTitle("项目值");
		this.f_projectKey = field = this.table.newField(FN_projectKey, TypeFactory.CHAR(2));
		field.setTitle("项目键");
		this.f_projectOrder = field = this.table.newField(FN_projectOrder, TypeFactory.INT);
		field.setDescription("数据为从1开始的数字");
		field.setTitle("属性项目显示顺序");
		this.table.newIndex("SA_GOODS_TYPE_PROJECT",f_propertyGuid);
	}

}
