package com.spark.psi.order.onlineorder;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_OnlineOrderDetByOrderId extends ORMDeclarator<com.spark.onlineorder.intf.entity.OnlineOrderDetEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_billsId;
	public final QueryColumnDefine c_count;
	public final QueryColumnDefine c_disAmount;
	public final QueryColumnDefine c_discount;
	public final QueryColumnDefine c_goodsCode;
	public final QueryColumnDefine c_goodsId;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsNo;
	public final QueryColumnDefine c_goodsScale;
	public final QueryColumnDefine c_goodsSpec;
	public final QueryColumnDefine c_price;
	public final QueryColumnDefine c_promotionId;
	public final QueryColumnDefine c_unit;

	public ORM_OnlineOrderDetByOrderId() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_amount = this.orm.getColumns().get(1);
		this.c_billsId = this.orm.getColumns().get(2);
		this.c_count = this.orm.getColumns().get(3);
		this.c_disAmount = this.orm.getColumns().get(4);
		this.c_discount = this.orm.getColumns().get(5);
		this.c_goodsCode = this.orm.getColumns().get(6);
		this.c_goodsId = this.orm.getColumns().get(7);
		this.c_goodsName = this.orm.getColumns().get(8);
		this.c_goodsNo = this.orm.getColumns().get(9);
		this.c_goodsScale = this.orm.getColumns().get(10);
		this.c_goodsSpec = this.orm.getColumns().get(11);
		this.c_price = this.orm.getColumns().get(12);
		this.c_promotionId = this.orm.getColumns().get(13);
		this.c_unit = this.orm.getColumns().get(14);
	}
}
