package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER_DEALING extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_DEALING";

	public final TableFieldDefine f_memberID;
	public final TableFieldDefine f_dealType;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_occurDate;

	public static final String FN_memberID ="memberID";
	public static final String FN_dealType ="dealType";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_amount ="amount";
	public static final String FN_occurDate ="occurDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_MEMBER_DEALING() {
		super(TABLE_NAME);
		this.table.setTitle("往来记录");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_memberID = field = this.table.newField(FN_memberID, TypeFactory.GUID);
		field.setTitle("会员id");
		this.f_dealType = field = this.table.newField(FN_dealType, TypeFactory.CHAR(2));
		field.setTitle("往来类型");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("面值卡、订单id");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单号");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(10,2));
		field.setTitle("金额");
		this.f_occurDate = field = this.table.newField(FN_occurDate, TypeFactory.DATE);
		field.setTitle("发生日期");
	}

}
