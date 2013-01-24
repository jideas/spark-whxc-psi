package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_cms_channel_goods extends TableDeclarator {

	public static final String TABLE_NAME ="cms_channel_goods";

	public final TableFieldDefine f_channelId;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_ordinal;

	public static final String FN_channelId ="channelId";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_ordinal ="ordinal";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_cms_channel_goods() {
		super(TABLE_NAME);
		this.table.setTitle("商品");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_channelId = this.table.newField(FN_channelId, TypeFactory.GUID);
		this.f_creatorId = this.table.newField(FN_creatorId, TypeFactory.GUID);
		this.f_creator = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.DATE);
		this.f_status = this.table.newField(FN_status, TypeFactory.CHAR(2));
		this.f_goodsId = this.table.newField(FN_goodsId, TypeFactory.GUID);
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.INT);
		field.setTitle("序号");
	}

}
