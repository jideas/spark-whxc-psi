package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ShelfItemByStoreId extends ORMDeclarator<com.spark.psi.base.publicimpl.ShelfItemImpl> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_shelfNo;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_tiersCount;

	public Orm_ShelfItemByStoreId() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_shelfNo = this.orm.getColumns().get(1);
		this.c_storeId = this.orm.getColumns().get(2);
		this.c_tiersCount = this.orm.getColumns().get(3);
	}
}
