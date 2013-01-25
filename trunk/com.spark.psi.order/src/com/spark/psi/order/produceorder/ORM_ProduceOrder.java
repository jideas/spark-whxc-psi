package com.spark.psi.order.produceorder;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_ProduceOrder extends ORMDeclarator<com.spark.produceorder.intf.entity.ProduceOrderEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_billsNo;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_finishDate;
	public final QueryColumnDefine c_goodsCount;
	public final QueryColumnDefine c_planDate;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_rejectReason;
	public final QueryColumnDefine c_approvePerson;
	public final QueryColumnDefine c_approveDate;

	public ORM_ProduceOrder() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_billsNo = this.orm.getColumns().get(1);
		this.c_createDate = this.orm.getColumns().get(2);
		this.c_creator = this.orm.getColumns().get(3);
		this.c_finishDate = this.orm.getColumns().get(4);
		this.c_goodsCount = this.orm.getColumns().get(5);
		this.c_planDate = this.orm.getColumns().get(6);
		this.c_remark = this.orm.getColumns().get(7);
		this.c_status = this.orm.getColumns().get(8);
		this.c_rejectReason = this.orm.getColumns().get(9);
		this.c_approvePerson = this.orm.getColumns().get(10);
		this.c_approveDate = this.orm.getColumns().get(11);
	}
}
