package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_cms_floor_advertising extends TableDeclarator {

	public static final String TABLE_NAME ="cms_floor_advertising";

	public final TableFieldDefine f_floorId;
	public final TableFieldDefine f_imageurl;
	public final TableFieldDefine f_url;
	public final TableFieldDefine f_title;

	public static final String FN_floorId ="floorId";
	public static final String FN_imageurl ="imageurl";
	public static final String FN_url ="url";
	public static final String FN_title ="title";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_cms_floor_advertising() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_floorId = field = this.table.newField(FN_floorId, TypeFactory.GUID);
		field.setTitle("楼层ID");
		this.f_imageurl = field = this.table.newField(FN_imageurl, TypeFactory.VARCHAR(200));
		field.setTitle("图片地址");
		this.f_url = field = this.table.newField(FN_url, TypeFactory.VARCHAR(200));
		field.setTitle("地址");
		this.f_title = field = this.table.newField(FN_title, TypeFactory.VARCHAR(50));
		field.setTitle("标题");
	}

}
