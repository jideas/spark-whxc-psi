package com.spark.psi.storage.base.notice;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Notice extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Notice";

	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_publishScope;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_noticeTitle;
	public final TableFieldDefine f_noticeTitlePY;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_publishDate;
	public final TableFieldDefine f_cancelDate;
	public final TableFieldDefine f_isTop;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_noticeContent;

	public static final String FN_deptId ="deptId";
	public static final String FN_publishScope ="publishScope";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_noticeTitle ="noticeTitle";
	public static final String FN_noticeTitlePY ="noticeTitlePY";
	public static final String FN_createDate ="createDate";
	public static final String FN_publishDate ="publishDate";
	public static final String FN_cancelDate ="cancelDate";
	public static final String FN_isTop ="isTop";
	public static final String FN_status ="status";
	public static final String FN_noticeContent ="noticeContent";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_Notice() {
		super(TABLE_NAME);
		this.table.setTitle("公告");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("部门编号");
		this.f_publishScope = field = this.table.newField(FN_publishScope, TypeFactory.NVARCHAR(1000));
		field.setTitle("发布范围");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人");
		this.f_noticeTitle = field = this.table.newField(FN_noticeTitle, TypeFactory.NVARCHAR(100));
		field.setTitle("公告标题");
		this.f_noticeTitlePY = field = this.table.newField(FN_noticeTitlePY, TypeFactory.NVARCHAR(100));
		field.setTitle("公告标题拼音");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_publishDate = field = this.table.newField(FN_publishDate, TypeFactory.DATE);
		field.setTitle("发布时间");
		this.f_cancelDate = field = this.table.newField(FN_cancelDate, TypeFactory.DATE);
		field.setTitle("撤销时间");
		this.f_isTop = field = this.table.newField(FN_isTop, TypeFactory.BOOLEAN);
		field.setTitle("是否置顶");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("公告状态");
		this.f_noticeContent = field = this.table.newField(FN_noticeContent, TypeFactory.TEXT);
		field.setTitle("公告内容");
	}

}
