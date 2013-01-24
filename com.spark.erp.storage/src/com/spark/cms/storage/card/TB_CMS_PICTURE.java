package com.spark.cms.storage.card;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_PICTURE extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_PICTURE";

	public final TableFieldDefine f_pictureType;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_picturePath;
	public final TableFieldDefine f_description;

	public static final String FN_pictureType ="pictureType";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_picturePath ="picturePath";
	public static final String FN_description ="description";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_PICTURE() {
		super(TABLE_NAME);
		this.table.setTitle("ͼƬ");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_pictureType = field = this.table.newField(FN_pictureType, TypeFactory.CHAR(2));
		field.setTitle("ͼƬ����");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("goodsId");
		this.f_picturePath = field = this.table.newField(FN_picturePath, TypeFactory.NVARCHAR(500));
		field.setTitle("·��");
		this.f_description = field = this.table.newField(FN_description, TypeFactory.NVARCHAR(1000));
		field.setTitle("����");
	}

}
