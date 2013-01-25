package com.spark.psi.order.deliver;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_DeliverDet extends ORMDeclarator<com.spark.deliver.intf.entity.DeliverDetEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_deliverSheetId;
	public final QueryColumnDefine c_memberRealName;
	public final QueryColumnDefine c_onlineOrderId;
	public final QueryColumnDefine c_onlineOrderNo;
	public final QueryColumnDefine c_orderAmount;

	public ORM_DeliverDet() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_deliverSheetId = this.orm.getColumns().get(1);
		this.c_memberRealName = this.orm.getColumns().get(2);
		this.c_onlineOrderId = this.orm.getColumns().get(3);
		this.c_onlineOrderNo = this.orm.getColumns().get(4);
		this.c_orderAmount = this.orm.getColumns().get(5);
	}
}
