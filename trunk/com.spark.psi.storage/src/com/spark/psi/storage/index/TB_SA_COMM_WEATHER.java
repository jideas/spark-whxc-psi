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

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_COMM_WEATHER() {
		super(TABLE_NAME);
		this.table.setDescription("天气");
		this.table.setTitle("天气");
		TableFieldDeclare field;
		this.f_city_name = field = this.table.newField(FN_city_name, TypeFactory.VARCHAR(255));
		field.setTitle("城市名称");
		this.f_city_order = field = this.table.newField(FN_city_order, TypeFactory.VARCHAR(255));
		field.setTitle("城市录入顺序");
	}

}
