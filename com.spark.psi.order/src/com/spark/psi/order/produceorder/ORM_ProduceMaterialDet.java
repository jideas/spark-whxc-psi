package com.spark.psi.order.produceorder;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_ProduceMaterialDet extends ORMDeclarator<com.spark.produceorder.intf.entity.ProduceMaterialDetEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_MaterialCode;
	public final QueryColumnDefine c_MaterialId;
	public final QueryColumnDefine c_MaterialName;
	public final QueryColumnDefine c_MaterialNo;
	public final QueryColumnDefine c_MaterialScale;
	public final QueryColumnDefine c_MaterialSpec;
	public final QueryColumnDefine c_billsId;
	public final QueryColumnDefine c_count;
	public final QueryColumnDefine c_receivedCount;
	public final QueryColumnDefine c_returnedCount;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_storeName;
	public final QueryColumnDefine c_unit;

	public ORM_ProduceMaterialDet() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_MaterialCode = this.orm.getColumns().get(1);
		this.c_MaterialId = this.orm.getColumns().get(2);
		this.c_MaterialName = this.orm.getColumns().get(3);
		this.c_MaterialNo = this.orm.getColumns().get(4);
		this.c_MaterialScale = this.orm.getColumns().get(5);
		this.c_MaterialSpec = this.orm.getColumns().get(6);
		this.c_billsId = this.orm.getColumns().get(7);
		this.c_count = this.orm.getColumns().get(8);
		this.c_receivedCount = this.orm.getColumns().get(9);
		this.c_returnedCount = this.orm.getColumns().get(10);
		this.c_storeId = this.orm.getColumns().get(11);
		this.c_storeName = this.orm.getColumns().get(12);
		this.c_unit = this.orm.getColumns().get(13);
	}
}
