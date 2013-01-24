package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_weather extends ORMDeclarator<com.spark.psi.base.publicimpl.WeatherImpl> {

	public final ArgumentDefine arg_user_id;

	public final QueryColumnDefine c_city_name;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_city_order;

	public ORM_weather() {
		this.arg_user_id = this.orm.getArguments().get(0);
		this.c_city_name = this.orm.getColumns().get(0);
		this.c_recid = this.orm.getColumns().get(1);
		this.c_city_order = this.orm.getColumns().get(2);
	}
}
