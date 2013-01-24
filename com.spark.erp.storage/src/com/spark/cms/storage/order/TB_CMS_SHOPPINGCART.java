package com.spark.cms.storage.order;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_SHOPPINGCART extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_SHOPPINGCART";

	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_ordinal;
	public final TableFieldDefine f_createDate;

	public static final String FN_memberId ="memberId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_ordinal ="ordinal";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_SHOPPINGCART() {
		super(TABLE_NAME);
		this.table.setTitle("���ﳵ");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("��Աid");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("��Ʒid");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("��Ʒ����");
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.INT);
		field.setTitle("˳���");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("���ʱ��");
	}

}
