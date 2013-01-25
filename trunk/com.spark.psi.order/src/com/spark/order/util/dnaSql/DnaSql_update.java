package com.spark.order.util.dnaSql;

import com.jiuqi.dna.core.Context;

public abstract class DnaSql_update extends DnaSqlImpl{
	public DnaSql_update(Context context) {
		super(context);
	}
	@Override
	protected String getDnaSqlTitle() {
		return DnaSqlType.Update.getValue();
	}
}
