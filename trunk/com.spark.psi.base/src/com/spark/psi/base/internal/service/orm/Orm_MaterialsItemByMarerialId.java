package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_MaterialsItemByMarerialId extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.MaterialsItemOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_materialCode;
	public final QueryColumnDefine c_materialNo;
	public final QueryColumnDefine c_materialName;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_categoryId;
	public final QueryColumnDefine c_spec;
	public final QueryColumnDefine c_scale;
	public final QueryColumnDefine c_inventoryStrategy;
	public final QueryColumnDefine c_materialUnit;
	public final QueryColumnDefine c_avgBuyPrice;
	public final QueryColumnDefine c_totalStoreUpper;
	public final QueryColumnDefine c_totalStoreFlore;
	public final QueryColumnDefine c_totalStoreAmount;
	public final QueryColumnDefine c_shelfLife;
	public final QueryColumnDefine c_warningDay;
	public final QueryColumnDefine c_salePrice;
	public final QueryColumnDefine c_standardPrice;
	public final QueryColumnDefine c_planPrice;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_canDelete;
	public final QueryColumnDefine c_refFlag;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_lastModifyDate;
	public final QueryColumnDefine c_lastModifyPerson;
	public final QueryColumnDefine c_warningType;
	public final QueryColumnDefine c_materialProperties;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_materialId;
	public final QueryColumnDefine c_lossRate;
	public final QueryColumnDefine c_recentPurchasePrice;

	public Orm_MaterialsItemByMarerialId() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_materialCode = this.orm.getColumns().get(1);
		this.c_materialNo = this.orm.getColumns().get(2);
		this.c_materialName = this.orm.getColumns().get(3);
		this.c_namePY = this.orm.getColumns().get(4);
		this.c_categoryId = this.orm.getColumns().get(5);
		this.c_spec = this.orm.getColumns().get(6);
		this.c_scale = this.orm.getColumns().get(7);
		this.c_inventoryStrategy = this.orm.getColumns().get(8);
		this.c_materialUnit = this.orm.getColumns().get(9);
		this.c_avgBuyPrice = this.orm.getColumns().get(10);
		this.c_totalStoreUpper = this.orm.getColumns().get(11);
		this.c_totalStoreFlore = this.orm.getColumns().get(12);
		this.c_totalStoreAmount = this.orm.getColumns().get(13);
		this.c_shelfLife = this.orm.getColumns().get(14);
		this.c_warningDay = this.orm.getColumns().get(15);
		this.c_salePrice = this.orm.getColumns().get(16);
		this.c_standardPrice = this.orm.getColumns().get(17);
		this.c_planPrice = this.orm.getColumns().get(18);
		this.c_status = this.orm.getColumns().get(19);
		this.c_remark = this.orm.getColumns().get(20);
		this.c_canDelete = this.orm.getColumns().get(21);
		this.c_refFlag = this.orm.getColumns().get(22);
		this.c_createDate = this.orm.getColumns().get(23);
		this.c_lastModifyDate = this.orm.getColumns().get(24);
		this.c_lastModifyPerson = this.orm.getColumns().get(25);
		this.c_warningType = this.orm.getColumns().get(26);
		this.c_materialProperties = this.orm.getColumns().get(27);
		this.c_creatorId = this.orm.getColumns().get(28);
		this.c_creator = this.orm.getColumns().get(29);
		this.c_materialId = this.orm.getColumns().get(30);
		this.c_lossRate = this.orm.getColumns().get(31);
		this.c_recentPurchasePrice = this.orm.getColumns().get(32);
	}
}
