package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER_DELIVER_LOG extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_DELIVER_LOG";

	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_deliveryId;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_occurDate;

	public static final String FN_memberId ="memberId";
	public static final String FN_deliveryId ="deliveryId";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_count ="count";
	public static final String FN_occurDate ="occurDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_MEMBER_DELIVER_LOG() {
		super(TABLE_NAME);
		this.table.setTitle("送货上门记录");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("会员id");
		this.f_deliveryId = field = this.table.newField(FN_deliveryId, TypeFactory.GUID);
		field.setTitle("deliveryId");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("相关单据id");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单据号");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(10,0));
		field.setTitle("次数");
		this.f_occurDate = field = this.table.newField(FN_occurDate, TypeFactory.DATE);
		field.setTitle("发生日期");
	}

}
