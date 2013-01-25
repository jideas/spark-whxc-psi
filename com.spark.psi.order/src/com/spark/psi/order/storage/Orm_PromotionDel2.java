package com.spark.psi.order.storage;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_PromotionDel2 extends ORMDeclarator<com.spark.order.promotion.intf.Promotion2> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_approvalDate;
	public final QueryColumnDefine c_beginDate;
	public final QueryColumnDefine c_countDecimal;
	public final QueryColumnDefine c_deptId;
	public final QueryColumnDefine c_endDate;
	public final QueryColumnDefine c_goodsItemId;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_goodsNamePY;
	public final QueryColumnDefine c_goodsProperties;
	public final QueryColumnDefine c_price_goods;
	public final QueryColumnDefine c_price_promotion;
	public final QueryColumnDefine c_promotionCause;
	public final QueryColumnDefine c_promotionCount;
	public final QueryColumnDefine c_returnCause;
	public final QueryColumnDefine c_saledCount;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_tenantsId;

	public Orm_PromotionDel2() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_approvalDate = this.orm.getColumns().get(0);
		this.c_beginDate = this.orm.getColumns().get(1);
		this.c_countDecimal = this.orm.getColumns().get(2);
		this.c_deptId = this.orm.getColumns().get(3);
		this.c_endDate = this.orm.getColumns().get(4);
		this.c_goodsItemId = this.orm.getColumns().get(5);
		this.c_goodsName = this.orm.getColumns().get(6);
		this.c_goodsNamePY = this.orm.getColumns().get(7);
		this.c_goodsProperties = this.orm.getColumns().get(8);
		this.c_price_goods = this.orm.getColumns().get(9);
		this.c_price_promotion = this.orm.getColumns().get(10);
		this.c_promotionCause = this.orm.getColumns().get(11);
		this.c_promotionCount = this.orm.getColumns().get(12);
		this.c_returnCause = this.orm.getColumns().get(13);
		this.c_saledCount = this.orm.getColumns().get(14);
		this.c_status = this.orm.getColumns().get(15);
		this.c_createDate = this.orm.getColumns().get(16);
		this.c_creator = this.orm.getColumns().get(17);
		this.c_creatorId = this.orm.getColumns().get(18);
		this.c_recid = this.orm.getColumns().get(19);
		this.c_tenantsId = this.orm.getColumns().get(20);
	}
}
