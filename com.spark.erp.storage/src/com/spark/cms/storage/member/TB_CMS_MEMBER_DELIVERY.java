package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_CMS_MEMBER_DELIVERY extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_DELIVERY";

	public final TableFieldDefine f_member;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_dcount;
	public final TableFieldDefine f_counted;
	public final TableFieldDefine f_lockedDeliveryBalance;

	public static final String FN_member ="member";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_dcount ="dcount";
	public static final String FN_counted ="counted";
	public static final String FN_lockedDeliveryBalance ="lockedDeliveryBalance";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_MEMBER_DELIVERY() {
		super(TABLE_NAME);
		this.table.setTitle("送货上门数据");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_member = field = this.table.newField(FN_member, TypeFactory.GUID);
		field.setTitle("会员id");
		this.f_beginDate = field = this.table.newField(FN_beginDate, TypeFactory.DATE);
		field.setTitle("开始日期");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("结束日期");
		this.f_dcount = field = this.table.newField(FN_dcount, TypeFactory.NUMERIC(10,0));
		field.setTitle("次数");
		this.f_counted = field = this.table.newField(FN_counted, TypeFactory.NUMERIC(10,0));
		field.setTitle("已用次数");
		this.f_lockedDeliveryBalance = field = this.table.newField(FN_lockedDeliveryBalance, TypeFactory.NUMERIC(17,0));
		field.setTitle("锁定上门送货包月次数");
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
