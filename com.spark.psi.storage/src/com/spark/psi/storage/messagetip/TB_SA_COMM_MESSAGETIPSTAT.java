package com.spark.psi.storage.messagetip;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_SA_COMM_MESSAGETIPSTAT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_MESSAGETIPSTAT";

	public final TableFieldDefine f_receivePerson;
	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_lastViewTime;
	public final TableFieldDefine f_settings;

	public static final String FN_receivePerson ="receivePerson";
	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_lastViewTime ="lastViewTime";
	public static final String FN_settings ="settings";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_MESSAGETIPSTAT() {
		super(TABLE_NAME);
		this.table.setDescription("存放消息提醒状态");
		this.table.setTitle("消息提醒状态");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_receivePerson = field = this.table.newField(FN_receivePerson, TypeFactory.GUID);
		field.setTitle("消息接收人");
		field.setKeepValid(true);
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_lastViewTime = field = this.table.newField(FN_lastViewTime, TypeFactory.DATE);
		field.setTitle("最后查看时间");
		this.f_settings = field = this.table.newField(FN_settings, TypeFactory.VARCHAR(50));
		field.setTitle("浮出提示设置");
		IndexDeclare index;
		index = this.table.newIndex("INDEX_MESSAGETIP_STAT",f_tenantsGuid,f_receivePerson);
		index.setUnique(true);
	}

}
