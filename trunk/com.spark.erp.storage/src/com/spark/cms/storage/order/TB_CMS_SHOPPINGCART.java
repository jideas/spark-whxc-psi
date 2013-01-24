package com.spark.cms.storage.order;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_SHOPPINGCART extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_SHOPPINGCART";

	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_ordinal;
	public final TableFieldDefine f_createDate;

	public static final String FN_memberId ="memberId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_ordinal ="ordinal";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_SHOPPINGCART() {
		super(TABLE_NAME);
		this.table.setTitle("购物车");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("会员id");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品id");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("商品数量");
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.INT);
		field.setTitle("顺序号");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("添加时间");
	}

}
