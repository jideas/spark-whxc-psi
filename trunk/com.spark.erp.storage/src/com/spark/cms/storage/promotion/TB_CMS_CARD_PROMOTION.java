package com.spark.cms.storage.promotion;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_CMS_CARD_PROMOTION extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_CARD_PROMOTION";

	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_vantages;
	public final TableFieldDefine f_activing;
	public final TableFieldDefine f_extraAmount;

	public static final String FN_amount ="amount";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_vantages ="vantages";
	public static final String FN_activing ="activing";
	public static final String FN_extraAmount ="extraAmount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_CARD_PROMOTION() {
		super(TABLE_NAME);
		this.table.setTitle("充值促销");
		this.table.setCategory("业务主体");
		this.f_amount = this.table.newField(FN_amount, TypeFactory.NUMERIC(10,2));
		this.f_goodsId = this.table.newField(FN_goodsId, TypeFactory.GUID);
		this.f_goodsCount = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(10,2));
		this.f_goodsName = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		this.f_vantages = this.table.newField(FN_vantages, TypeFactory.NUMERIC(10,0));
		this.f_activing = this.table.newField(FN_activing, TypeFactory.BOOLEAN);
		this.f_extraAmount = this.table.newField(FN_extraAmount, TypeFactory.NUMERIC(10,2));
	}

}
