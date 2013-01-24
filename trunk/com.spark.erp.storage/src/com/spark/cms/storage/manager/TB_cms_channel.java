package com.spark.cms.storage.manager;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_cms_channel extends TableDeclarator {

	public static final String TABLE_NAME ="cms_channel";

	public final TableFieldDefine f_code;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_floorId;
	public final TableFieldDefine f_channelType;
	public final TableFieldDefine f_pageType;
	public final TableFieldDefine f_isMainMenu;

	public static final String FN_code ="code";
	public static final String FN_name ="name";
	public static final String FN_floorId ="floorId";
	public static final String FN_channelType ="channelType";
	public static final String FN_pageType ="pageType";
	public static final String FN_isMainMenu ="isMainMenu";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_cms_channel() {
		super(TABLE_NAME);
		this.table.setTitle("��Ŀ����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(20));
		field.setTitle("��Ŀ���");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(50));
		field.setTitle("��Ŀ����");
		this.f_floorId = field = this.table.newField(FN_floorId, TypeFactory.GUID);
		field.setTitle("¥��id");
		this.f_channelType = field = this.table.newField(FN_channelType, TypeFactory.CHAR(2));
		field.setTitle("����(����||���||��Ʒ)");
		this.f_pageType = field = this.table.newField(FN_pageType, TypeFactory.CHAR(2));
		field.setTitle("ҳ�漶��");
		this.f_isMainMenu = field = this.table.newField(FN_isMainMenu, TypeFactory.BOOLEAN);
		field.setTitle("���˵������");
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
