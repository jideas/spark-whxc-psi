package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_CMS_IMAGE_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_IMAGE_INFO";

	public final TableFieldDefine f_imgUrl;
	public final TableFieldDefine f_imgName;
	public final TableFieldDefine f_imgWidth;
	public final TableFieldDefine f_imgHeight;
	public final TableFieldDefine f_folderId;

	public static final String FN_imgUrl ="imgUrl";
	public static final String FN_imgName ="imgName";
	public static final String FN_imgWidth ="imgWidth";
	public static final String FN_imgHeight ="imgHeight";
	public static final String FN_folderId ="folderId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_IMAGE_INFO() {
		super(TABLE_NAME);
		this.table.setTitle("图片信息表");
		this.table.setCategory("业务主体");
		this.f_imgUrl = this.table.newField(FN_imgUrl, TypeFactory.NVARCHAR(1000));
		this.f_imgName = this.table.newField(FN_imgName, TypeFactory.NVARCHAR(100));
		this.f_imgWidth = this.table.newField(FN_imgWidth, TypeFactory.INT);
		this.f_imgHeight = this.table.newField(FN_imgHeight, TypeFactory.INT);
		this.f_folderId = this.table.newField(FN_folderId, TypeFactory.NVARCHAR(32));
	}

}
