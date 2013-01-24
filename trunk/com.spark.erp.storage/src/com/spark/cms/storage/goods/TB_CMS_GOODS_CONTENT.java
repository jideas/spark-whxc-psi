package com.spark.cms.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_GOODS_CONTENT extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_GOODS_CONTENT";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_contentTitle;
	public final TableFieldDefine f_ordinal;
	public final TableFieldDefine f_goodsContent;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_contentTitle ="contentTitle";
	public static final String FN_ordinal ="ordinal";
	public static final String FN_goodsContent ="goodsContent";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_GOODS_CONTENT() {
		super(TABLE_NAME);
		this.table.setTitle("商品说明");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("goodsId");
		this.f_contentTitle = field = this.table.newField(FN_contentTitle, TypeFactory.NVARCHAR(10));
		field.setTitle("事项名称");
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.INT);
		field.setTitle("序号");
		this.f_goodsContent = field = this.table.newField(FN_goodsContent, TypeFactory.TEXT);
		field.setTitle("goodsContent");
	}

}
