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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_Notice() {
		super(TABLE_NAME);
		this.table.setTitle("����");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("���ű��");
		this.f_publishScope = field = this.table.newField(FN_publishScope, TypeFactory.NVARCHAR(1000));
		field.setTitle("������Χ");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
		this.f_noticeTitle = field = this.table.newField(FN_noticeTitle, TypeFactory.NVARCHAR(100));
		field.setTitle("�������");
		this.f_noticeTitlePY = field = this.table.newField(FN_noticeTitlePY, TypeFactory.NVARCHAR(100));
		field.setTitle("�������ƴ��");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_publishDate = field = this.table.newField(FN_publishDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_cancelDate = field = this.table.newField(FN_cancelDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_isTop = field = this.table.newField(FN_isTop, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ��ö�");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("����״̬");
		this.f_noticeContent = field = this.table.newField(FN_noticeContent, TypeFactory.TEXT);
		field.setTitle("��������");
	}

}
