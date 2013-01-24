package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_GIFT extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_GIFT";

	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_reason;
	public final TableFieldDefine f_lastDate;
	public final TableFieldDefine f_status;

	public static final String FN_memberId ="memberId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_reason ="reason";
	public static final String FN_lastDate ="lastDate";
	public static final String FN_status ="status";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_GIFT() {
		super(TABLE_NAME);
		this.table.setTitle("��Ʒ�Ǽ�");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.GUID);
		field.setTitle("��Աid");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("��Ʒid");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("��Ʒ����");
		this.f_reason = field = this.table.newField(FN_reason, TypeFactory.NVARCHAR(300));
		field.setTitle("����ԭ��");
		this.f_lastDate = field = this.table.newField(FN_lastDate, TypeFactory.DATE);
		field.setTitle("��ֹ����");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
	}

}
