package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_GIFT extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_GIFT";

	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_reason;
	public final TableFieldDefine f_lastDate;
	public final TableFieldDefine f_status;

	public static final String FN_memberId ="memberId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_reason ="reason";
	public static final String FN_lastDate ="lastDate";
	public static final String FN_status ="status";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_GIFT() {
		super(TABLE_NAME);
		this.table.setTitle("赠品登记");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("会员id");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品id");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("商品数量");
		this.f_reason = field = this.table.newField(FN_reason, TypeFactory.NVARCHAR(300));
		field.setTitle("赠送原因");
		this.f_lastDate = field = this.table.newField(FN_lastDate, TypeFactory.DATE);
		field.setTitle("截止日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
	}

}
