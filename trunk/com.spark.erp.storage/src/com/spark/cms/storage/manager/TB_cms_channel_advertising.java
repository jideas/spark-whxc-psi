package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_cms_channel_advertising extends TableDeclarator {

	public static final String TABLE_NAME ="cms_channel_advertising";

	public final TableFieldDefine f_channelId;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_imageUrl;
	public final TableFieldDefine f_url;

	public static final String FN_channelId ="channelId";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";
	public static final String FN_imageUrl ="imageUrl";
	public static final String FN_url ="url";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_cms_channel_advertising() {
		super(TABLE_NAME);
		this.table.setTitle("���λ����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_channelId = field = this.table.newField(FN_channelId, TypeFactory.GUID);
		field.setTitle("��Ŀid");
		this.f_creatorId = this.table.newField(FN_creatorId, TypeFactory.GUID);
		this.f_creator = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.DATE);
		this.f_status = this.table.newField(FN_status, TypeFactory.CHAR(2));
		this.f_imageUrl = field = this.table.newField(FN_imageUrl, TypeFactory.NVARCHAR(200));
		field.setTitle("ͼƬ��ַ");
		this.f_url = field = this.table.newField(FN_url, TypeFactory.NVARCHAR(200));
		field.setTitle("���ӵ�ַ");
	}

}
