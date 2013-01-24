package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Goods extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.GoodsOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_goodsCode;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_categoryId;
	public final QueryColumnDefine c_salePrice;
	public final QueryColumnDefine c_isJointVenture;
	public final QueryColumnDefine c_supplierId;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_shelfLife;
	public final QueryColumnDefine c_warningDay;
	public final QueryColumnDefine c_canDelete;
	public final QueryColumnDefine c_refFlag;
	public final QueryColumnDefine c_inventoryWarningType;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_lastModifyDate;
	public final QueryColumnDefine c_lastModifyPerson;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_status;

	public Orm_Goods() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_goodsCode = this.orm.getColumns().get(1);
		this.c_goodsName = this.orm.getColumns().get(2);
		this.c_namePY = this.orm.getColumns().get(3);
		this.c_categoryId = this.orm.getColumns().get(4);
		this.c_salePrice = this.orm.getColumns().get(5);
		this.c_isJointVenture = this.orm.getColumns().get(6);
		this.c_supplierId = this.orm.getColumns().get(7);
		this.c_remark = this.orm.getColumns().get(8);
		this.c_shelfLife = this.orm.getColumns().get(9);
		this.c_warningDay = this.orm.getColumns().get(10);
		this.c_canDelete = this.orm.getColumns().get(11);
		this.c_refFlag = this.orm.getColumns().get(12);
		this.c_inventoryWarningType = this.orm.getColumns().get(13);
		this.c_createDate = this.orm.getColumns().get(14);
		this.c_lastModifyDate = this.orm.getColumns().get(15);
		this.c_lastModifyPerson = this.orm.getColumns().get(16);
		this.c_creatorId = this.orm.getColumns().get(17);
		this.c_creator = this.orm.getColumns().get(18);
		this.c_status = this.orm.getColumns().get(19);
	}
}
