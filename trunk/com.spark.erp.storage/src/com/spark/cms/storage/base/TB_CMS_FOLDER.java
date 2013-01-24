package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_FOLDER extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_FOLDER";

	public final TableFieldDefine f_parentId;
	public final TableFieldDefine f_title;
	public final TableFieldDefine f_hasImages;
	public final TableFieldDefine f_code;

	public static final String FN_parentId ="parentId";
	public static final String FN_title ="title";
	public static final String FN_hasImages ="hasImages";
	public static final String FN_code ="code";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_FOLDER() {
		super(TABLE_NAME);
		this.table.setTitle("文件夹信息");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_parentId = this.table.newField(FN_parentId, TypeFactory.GUID);
		this.f_title = this.table.newField(FN_title, TypeFactory.NVARCHAR(20));
		this.f_hasImages = this.table.newField(FN_hasImages, TypeFactory.BOOLEAN);
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(20));
		field.setKeepValid(true);
	}

}
