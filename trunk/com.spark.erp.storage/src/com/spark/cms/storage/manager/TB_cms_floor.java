package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_cms_floor extends TableDeclarator {

	public static final String TABLE_NAME ="cms_floor";

	public final TableFieldDefine f_ordinal;
	public final TableFieldDefine f_title;
	public final TableFieldDefine f_theme;
	public final TableFieldDefine f_floorType;
	public final TableFieldDefine f_goodsCategoryId;
	public final TableFieldDefine f_imageUrl;
	public final TableFieldDefine f_url;

	public static final String FN_ordinal ="ordinal";
	public static final String FN_title ="title";
	public static final String FN_theme ="theme";
	public static final String FN_floorType ="floorType";
	public static final String FN_goodsCategoryId ="goodsCategoryId";
	public static final String FN_imageUrl ="imageUrl";
	public static final String FN_url ="url";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_cms_floor() {
		super(TABLE_NAME);
		this.table.setTitle("¥�����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.INT);
		field.setTitle("���");
		this.f_title = this.table.newField(FN_title, TypeFactory.NVARCHAR(50));
		this.f_theme = field = this.table.newField(FN_theme, TypeFactory.CHAR(2));
		field.setTitle("����");
		this.f_floorType = field = this.table.newField(FN_floorType, TypeFactory.CHAR(2));
		field.setTitle("����(���||��Ʒ)");
		this.f_goodsCategoryId = field = this.table.newField(FN_goodsCategoryId, TypeFactory.GUID);
		field.setTitle("��¥��Ӧ����Ʒ��������id");
		this.f_imageUrl = field = this.table.newField(FN_imageUrl, TypeFactory.NVARCHAR(200));
		field.setTitle("��¥���ͼƬ��ַ");
		this.f_url = field = this.table.newField(FN_url, TypeFactory.NVARCHAR(200));
		field.setTitle("��¥������ӵ�ַ");
	}

}
