package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_cms_channel_content extends TableDeclarator {

	public static final String TABLE_NAME ="cms_channel_content";

	public final TableFieldDefine f_channelId;
	public final TableFieldDefine f_code;
	public final TableFieldDefine f_title;
	public final TableFieldDefine f_content;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;

	public static final String FN_channelId ="channelId";
	public static final String FN_code ="code";
	public static final String FN_title ="title";
	public static final String FN_content ="content";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_cms_channel_content() {
		super(TABLE_NAME);
		this.table.setTitle("���ݹ���");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_channelId = field = this.table.newField(FN_channelId, TypeFactory.GUID);
		field.setTitle("��Ŀid");
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(20));
		field.setTitle("���");
		this.f_title = field = this.table.newField(FN_title, TypeFactory.NVARCHAR(50));
		field.setTitle("����");
		this.f_content = field = this.table.newField(FN_content, TypeFactory.TEXT);
		field.setTitle("����");
		this.f_creatorId = this.table.newField(FN_creatorId, TypeFactory.GUID);
		this.f_creator = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.DATE);
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
	}

}
