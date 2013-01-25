package com.spark.order.util.dnaSql;

import com.jiuqi.dna.core.Context;

public abstract class DnaSql_query extends DnaSqlImpl{
	public DnaSql_query(Context context) {
		super(context);
	}
	@Override
	protected String getDnaSqlTitle() {
		return DnaSqlType.Query.getValue();
	}
}
