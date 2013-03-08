package com.spark.cms.storage.log;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_CMS_PAYING_LOG extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_PAYING_LOG";

	public final TableFieldDefine f_ptype;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_orderId;
	public final TableFieldDefine f_transDate;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_logDate;
	public final TableFieldDefine f_resultXml;

	public static final String FN_ptype ="ptype";
	public static final String FN_amount ="amount";
	public static final String FN_createDate ="createDate";
	public static final String FN_orderId ="orderId";
	public static final String FN_transDate ="transDate";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_logDate ="logDate";
	public static final String FN_resultXml ="resultXml";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_PAYING_LOG() {
		super(TABLE_NAME);
		this.table.setTitle("单笔查询记录");
		this.table.setCategory("报表主体");
		this.f_ptype = this.table.newField(FN_ptype, TypeFactory.CHAR(2));
		this.f_amount = this.table.newField(FN_amount, TypeFactory.VARCHAR(20));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.VARCHAR(20));
		this.f_orderId = this.table.newField(FN_orderId, TypeFactory.VARCHAR(100));
		this.f_transDate = this.table.newField(FN_transDate, TypeFactory.VARCHAR(20));
		this.f_relaBillsId = this.table.newField(FN_relaBillsId, TypeFactory.VARCHAR(100));
		this.f_logDate = this.table.newField(FN_logDate, TypeFactory.VARCHAR(20));
		this.f_resultXml = this.table.newField(FN_resultXml, TypeFactory.VARCHAR(500));
	}

}
