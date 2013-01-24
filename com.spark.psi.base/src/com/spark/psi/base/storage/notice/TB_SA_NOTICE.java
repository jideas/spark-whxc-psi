package com.spark.psi.base.storage.notice;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_NOTICE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_NOTICE";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_deptNameStr;
	public final TableFieldDefine f_createGuid;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_noticeTitle;
	public final TableFieldDefine f_noticeTitlePY;
	public final TableFieldDefine f_createTime;
	public final TableFieldDefine f_publishTime;
	public final TableFieldDefine f_cancelTime;
	public final TableFieldDefine f_isTop;
	public final TableFieldDefine f_noticeState;
	public final TableFieldDefine f_noticeContent;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_deptNameStr ="deptNameStr";
	public static final String FN_createGuid ="createGuid";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_noticeTitle ="noticeTitle";
	public static final String FN_noticeTitlePY ="noticeTitlePY";
	public static final String FN_createTime ="createTime";
	public static final String FN_publishTime ="publishTime";
	public static final String FN_cancelTime ="cancelTime";
	public static final String FN_isTop ="isTop";
	public static final String FN_noticeState ="noticeState";
	public static final String FN_noticeContent ="noticeContent";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_NOTICE() {
		super(TABLE_NAME);
		this.table.setTitle("公告基础信息表");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("部门编号");
		this.f_deptNameStr = field = this.table.newField(FN_deptNameStr, TypeFactory.NVARCHAR(1000));
		field.setTitle("发布范围");
		this.f_createGuid = field = this.table.newField(FN_createGuid, TypeFactory.GUID);
		field.setTitle("创建人Guid");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(20));
		field.setTitle("创建人");
		this.f_noticeTitle = field = this.table.newField(FN_noticeTitle, TypeFactory.NVARCHAR(100));
		field.setTitle("公告标题");
		this.f_noticeTitlePY = field = this.table.newField(FN_noticeTitlePY, TypeFactory.NVARCHAR(100));
		field.setTitle("公告标题拼音");
		this.f_createTime = field = this.table.newField(FN_createTime, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_publishTime = field = this.table.newField(FN_publishTime, TypeFactory.DATE);
		field.setTitle("发布时间");
		this.f_cancelTime = field = this.table.newField(FN_cancelTime, TypeFactory.DATE);
		field.setTitle("撤销时间");
		this.f_isTop = field = this.table.newField(FN_isTop, TypeFactory.BOOLEAN);
		field.setTitle("是否置顶");
		this.f_noticeState = field = this.table.newField(FN_noticeState, TypeFactory.CHAR(2));
		field.setTitle("公告状态");
		this.f_noticeContent = field = this.table.newField(FN_noticeContent, TypeFactory.TEXT);
		field.setTitle("公告内容");
	}

}
