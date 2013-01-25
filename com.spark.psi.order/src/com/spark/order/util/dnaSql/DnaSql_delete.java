package com.spark.order.util.dnaSql;

import com.jiuqi.dna.core.Context;

public abstract class DnaSql_delete extends DnaSqlImpl{
	public DnaSql_delete(Context context) {
		super(context);
	}
	@Override
	protected String getDnaSqlTitle() {
		return DnaSqlType.Delete.getValue();
	}
}
