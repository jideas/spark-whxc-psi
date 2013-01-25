package com.spark.psi.storage.messagetip;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_COMM_MESSAGETIPRECORD extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_MESSAGETIPRECORD";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_receivePerson;
	public final TableFieldDefine f_messageTipType;
	public final TableFieldDefine f_monitorObjGuid;
	public final TableFieldDefine f_templateCode;
	public final TableFieldDefine f_subjectContent;
	public final TableFieldDefine f_subjectRecid;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_isShow;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_alertDate;
	public final TableFieldDefine f_startDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_deptName;
	public final TableFieldDefine f_sysFunction;
	public final TableFieldDefine f_amountBackup;
	public final TableFieldDefine f_remark;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_receivePerson ="receivePerson";
	public static final String FN_messageTipType ="messageTipType";
	public static final String FN_monitorObjGuid ="monitorObjGuid";
	public static final String FN_templateCode ="templateCode";
	public static final String FN_subjectContent ="subjectContent";
	public static final String FN_subjectRecid ="subjectRecid";
	public static final String FN_amount ="amount";
	public static final String FN_isShow ="isShow";
	public static final String FN_createDate ="createDate";
	public static final String FN_alertDate ="alertDate";
	public static final String FN_startDate ="startDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_deptName ="deptName";
	public static final String FN_sysFunction ="sysFunction";
	public static final String FN_amountBackup ="amountBackup";
	public static final String FN_remark ="remark";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_MESSAGETIPRECORD() {
		super(TABLE_NAME);
		this.table.setTitle("消息提醒详细记录");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_receivePerson = field = this.table.newField(FN_receivePerson, TypeFactory.GUID);
		field.setTitle("提醒接收人");
		this.f_messageTipType = field = this.table.newField(FN_messageTipType, TypeFactory.CHAR(2));
		field.setTitle("消息提醒类型");
		this.f_monitorObjGuid = field = this.table.newField(FN_monitorObjGuid, TypeFactory.GUID);
		field.setTitle("关联对象");
		this.f_templateCode = field = this.table.newField(FN_templateCode, TypeFactory.NVARCHAR(100));
		field.setTitle("消息显示模板编码");
		this.f_subjectContent = field = this.table.newField(FN_subjectContent, TypeFactory.NVARCHAR(400));
		field.setTitle("主体内容");
		this.f_subjectRecid = field = this.table.newField(FN_subjectRecid, TypeFactory.GUID);
		field.setTitle("主体关联");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("金额");
		this.f_isShow = field = this.table.newField(FN_isShow, TypeFactory.BOOLEAN);
		field.setTitle("是否显示");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("记录新增时间");
		this.f_alertDate = field = this.table.newField(FN_alertDate, TypeFactory.DATE);
		field.setTitle("原始提醒时间");
		this.f_startDate = field = this.table.newField(FN_startDate, TypeFactory.DATE);
		field.setTitle("开始提醒时间");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("提醒结束时间");
		this.f_deptName = field = this.table.newField(FN_deptName, TypeFactory.NVARCHAR(50));
		field.setTitle("部门名称");
		this.f_sysFunction = field = this.table.newField(FN_sysFunction, TypeFactory.GUID);
		field.setTitle("息对应的模块");
		this.f_amountBackup = field = this.table.newField(FN_amountBackup, TypeFactory.NUMERIC(17,2));
		field.setTitle("金额数量备用字段");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.VARCHAR(255));
		field.setTitle("备注");
		this.table.newIndex("INDEX_MESSAGETIPRECORD_1",f_tenantsGuid,f_monitorObjGuid,f_messageTipType,f_receivePerson,f_isShow);
		this.table.newIndex("INDEX_MESSAGETIPRECORD_2",f_tenantsGuid,f_receivePerson,f_createDate);
	}

}
