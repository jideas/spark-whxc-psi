package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Store extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.StoreOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_recver;
	public final QueryColumnDefine c_storeNo;
	public final QueryColumnDefine c_storeName;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_consignee;
	public final QueryColumnDefine c_mobileNo;
	public final QueryColumnDefine c_province;
	public final QueryColumnDefine c_city;
	public final QueryColumnDefine c_town;
	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_postCode;
	public final QueryColumnDefine c_shelfCount;
	public final QueryColumnDefine c_defaultTiersCount;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_storeType;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creator;

	public Orm_Store() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_recver = this.orm.getColumns().get(1);
		this.c_storeNo = this.orm.getColumns().get(2);
		this.c_storeName = this.orm.getColumns().get(3);
		this.c_namePY = this.orm.getColumns().get(4);
		this.c_consignee = this.orm.getColumns().get(5);
		this.c_mobileNo = this.orm.getColumns().get(6);
		this.c_province = this.orm.getColumns().get(7);
		this.c_city = this.orm.getColumns().get(8);
		this.c_town = this.orm.getColumns().get(9);
		this.c_address = this.orm.getColumns().get(10);
		this.c_postCode = this.orm.getColumns().get(11);
		this.c_shelfCount = this.orm.getColumns().get(12);
		this.c_defaultTiersCount = this.orm.getColumns().get(13);
		this.c_status = this.orm.getColumns().get(14);
		this.c_storeType = this.orm.getColumns().get(15);
		this.c_createDate = this.orm.getColumns().get(16);
		this.c_creatorId = this.orm.getColumns().get(17);
		this.c_creator = this.orm.getColumns().get(18);
	}
}
