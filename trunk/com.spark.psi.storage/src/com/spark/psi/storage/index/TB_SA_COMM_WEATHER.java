package com.spark.psi.storage.index;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_COMM_WEATHER extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_WEATHER";

	public final TableFieldDefine f_city_name;
	public final TableFieldDefine f_city_order;

	public static final String FN_city_name ="city_name";
	public static final String FN_city_order ="city_order";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_COMM_WEATHER() {
		super(TABLE_NAME);
		this.table.setDescription("����");
		this.table.setTitle("����");
		TableFieldDeclare field;
		this.f_city_name = field = this.table.newField(FN_city_name, TypeFactory.VARCHAR(255));
		field.setTitle("��������");
		this.f_city_order = field = this.table.newField(FN_city_order, TypeFactory.VARCHAR(255));
		field.setTitle("����¼��˳��");
	}

}
