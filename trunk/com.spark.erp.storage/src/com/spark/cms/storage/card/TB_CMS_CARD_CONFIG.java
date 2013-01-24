package com.spark.cms.storage.card;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_CARD_CONFIG extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_CARD_CONFIG";

	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_promotionType;
	public final TableFieldDefine f_giftId;
	public final TableFieldDefine f_realValue;
	public final TableFieldDefine f_giftLastDate;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;

	public static final String FN_amount ="amount";
	public static final String FN_promotionType ="promotionType";
	public static final String FN_giftId ="giftId";
	public static final String FN_realValue ="realValue";
	public static final String FN_giftLastDate ="giftLastDate";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_CARD_CONFIG() {
		super(TABLE_NAME);
		this.table.setTitle("充值配置");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(10,2));
		field.setTitle("面值");
		this.f_promotionType = field = this.table.newField(FN_promotionType, TypeFactory.CHAR(2));
		field.setTitle("促销方式");
		this.f_giftId = field = this.table.newField(FN_giftId, TypeFactory.GUID);
		field.setTitle("赠品id");
		this.f_realValue = field = this.table.newField(FN_realValue, TypeFactory.NUMERIC(10,0));
		field.setTitle("值（积分数，赠品数，送金额）");
		this.f_giftLastDate = field = this.table.newField(FN_giftLastDate, TypeFactory.DATE);
		field.setTitle("赠品截止日期");
		this.f_beginDate = field = this.table.newField(FN_beginDate, TypeFactory.DATE);
		field.setTitle("开始日期");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("结束日期");
	}

}
