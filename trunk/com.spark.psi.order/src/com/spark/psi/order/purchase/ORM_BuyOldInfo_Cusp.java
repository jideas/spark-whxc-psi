package com.spark.psi.order.purchase;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_BuyOldInfo_Cusp extends ORMDeclarator<com.spark.order.purchase.intf.entity.PurchaseLatelyInfo> {

	public final ArgumentDefine arg_tenants;
	public final ArgumentDefine arg_goods;
	public final ArgumentDefine arg_cusp;

	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_consignee;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_billsNo;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_isStoped;
	public final QueryColumnDefine c_linkman;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_totalAmount;

	public ORM_BuyOldInfo_Cusp() {
		this.arg_tenants = this.orm.getArguments().get(0);
		this.arg_goods = this.orm.getArguments().get(1);
		this.arg_cusp = this.orm.getArguments().get(2);
		this.c_address = this.orm.getColumns().get(0);
		this.c_consignee = this.orm.getColumns().get(1);
		this.c_status = this.orm.getColumns().get(2);
		this.c_billsNo = this.orm.getColumns().get(3);
		this.c_createDate = this.orm.getColumns().get(4);
		this.c_isStoped = this.orm.getColumns().get(5);
		this.c_linkman = this.orm.getColumns().get(6);
		this.c_remark = this.orm.getColumns().get(7);
		this.c_totalAmount = this.orm.getColumns().get(8);
	}
}
