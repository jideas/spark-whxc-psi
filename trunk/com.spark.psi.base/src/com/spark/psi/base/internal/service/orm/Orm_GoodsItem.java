package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_GoodsItem extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.GoodsItemOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_goodsCode;
	public final QueryColumnDefine c_goodsNo;
	public final QueryColumnDefine c_goodsName;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_categoryId;
	public final QueryColumnDefine c_spec;
	public final QueryColumnDefine c_scale;
	public final QueryColumnDefine c_needProduce;
	public final QueryColumnDefine c_inventoryStrategy;
	public final QueryColumnDefine c_goodsUnit;
	public final QueryColumnDefine c_avgCost;
	public final QueryColumnDefine c_standardCost;
	public final QueryColumnDefine c_assessCost;
	public final QueryColumnDefine c_shelfLife;
	public final QueryColumnDefine c_warningDay;
	public final QueryColumnDefine c_salePrice;
	public final QueryColumnDefine c_originalPrice;
	public final QueryColumnDefine c_lossRate;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_canDelete;
	public final QueryColumnDefine c_refFlag;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_lastModifyDate;
	public final QueryColumnDefine c_lastModifyPerson;
	public final QueryColumnDefine c_goodsProperties;
	public final QueryColumnDefine c_bomId;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_goodsId;
	public final QueryColumnDefine c_serialNumber;

	public Orm_GoodsItem() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_goodsCode = this.orm.getColumns().get(1);
		this.c_goodsNo = this.orm.getColumns().get(2);
		this.c_goodsName = this.orm.getColumns().get(3);
		this.c_namePY = this.orm.getColumns().get(4);
		this.c_categoryId = this.orm.getColumns().get(5);
		this.c_spec = this.orm.getColumns().get(6);
		this.c_scale = this.orm.getColumns().get(7);
		this.c_needProduce = this.orm.getColumns().get(8);
		this.c_inventoryStrategy = this.orm.getColumns().get(9);
		this.c_goodsUnit = this.orm.getColumns().get(10);
		this.c_avgCost = this.orm.getColumns().get(11);
		this.c_standardCost = this.orm.getColumns().get(12);
		this.c_assessCost = this.orm.getColumns().get(13);
		this.c_shelfLife = this.orm.getColumns().get(14);
		this.c_warningDay = this.orm.getColumns().get(15);
		this.c_salePrice = this.orm.getColumns().get(16);
		this.c_originalPrice = this.orm.getColumns().get(17);
		this.c_lossRate = this.orm.getColumns().get(18);
		this.c_status = this.orm.getColumns().get(19);
		this.c_canDelete = this.orm.getColumns().get(20);
		this.c_refFlag = this.orm.getColumns().get(21);
		this.c_createDate = this.orm.getColumns().get(22);
		this.c_lastModifyDate = this.orm.getColumns().get(23);
		this.c_lastModifyPerson = this.orm.getColumns().get(24);
		this.c_goodsProperties = this.orm.getColumns().get(25);
		this.c_bomId = this.orm.getColumns().get(26);
		this.c_creatorId = this.orm.getColumns().get(27);
		this.c_creator = this.orm.getColumns().get(28);
		this.c_goodsId = this.orm.getColumns().get(29);
		this.c_serialNumber = this.orm.getColumns().get(30);
	}
}
