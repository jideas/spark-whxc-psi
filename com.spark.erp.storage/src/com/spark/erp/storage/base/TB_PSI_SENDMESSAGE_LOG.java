package com.spark.erp.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_PSI_SENDMESSAGE_LOG extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_SENDMESSAGE_LOG";

	public final TableFieldDefine f_phoneNo;
	public final TableFieldDefine f_sendType;
	public final TableFieldDefine f_remoteAddress;
	public final TableFieldDefine f_remoteHost;
	public final TableFieldDefine f_createDate;

	public static final String FN_phoneNo ="phoneNo";
	public static final String FN_sendType ="sendType";
	public static final String FN_remoteAddress ="remoteAddress";
	public static final String FN_remoteHost ="remoteHost";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_SENDMESSAGE_LOG() {
		super(TABLE_NAME);
		this.table.setTitle("短信日志");
		this.table.setCategory("报表主体");
		this.f_phoneNo = this.table.newField(FN_phoneNo, TypeFactory.NVARCHAR(15));
		this.f_sendType = this.table.newField(FN_sendType, TypeFactory.CHAR(2));
		this.f_remoteAddress = this.table.newField(FN_remoteAddress, TypeFactory.NVARCHAR(20));
		this.f_remoteHost = this.table.newField(FN_remoteHost, TypeFactory.NVARCHAR(20));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.LONG);
	}

}
