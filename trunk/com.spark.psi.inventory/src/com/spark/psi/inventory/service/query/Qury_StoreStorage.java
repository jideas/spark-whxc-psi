package com.spark.psi.inventory.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class Qury_StoreStorage extends QueryStatementDeclarator {


		public final ArgumentDefine arg_tenantsGuid;
		public final ArgumentDefine arg_storeGuid;
		public final ArgumentDefine arg_storageType;
		public final ArgumentDefine arg_isInit;

		public final QueryColumnDefine c_goodsCount;
		public final QueryColumnDefine c_goodsGuid;
		public final QueryColumnDefine c_goodsName;
		public final QueryColumnDefine c_goodsNo;
		public final QueryColumnDefine c_goodsProperty;
		public final QueryColumnDefine c_goodsStoreAmount;
		public final QueryColumnDefine c_goodsUnit;
		public final QueryColumnDefine c_goodsUnitCost;
		public final QueryColumnDefine c_initAmount;
		public final QueryColumnDefine c_initCount;
		public final QueryColumnDefine c_initUnitCost;
		public final QueryColumnDefine c_isInit;
		public final QueryColumnDefine c_onWayCount;
		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_storeAmountUpper;
		public final QueryColumnDefine c_storeFloor;
		public final QueryColumnDefine c_storeGuid;
		public final QueryColumnDefine c_storeType;
		public final QueryColumnDefine c_storeUpper;
		public final QueryColumnDefine c_tenantsGuid;
		public final QueryColumnDefine c_toDeliverCount;

		public Qury_StoreStorage() {
			this.arg_tenantsGuid = this.query.getArguments().get(0);
			this.arg_storeGuid = this.query.getArguments().get(1);
			this.arg_storageType = this.query.getArguments().get(2);
			this.arg_isInit = this.query.getArguments().get(3);
			this.c_goodsCount = this.query.getColumns().get(0);
			this.c_goodsGuid = this.query.getColumns().get(1);
			this.c_goodsName = this.query.getColumns().get(2);
			this.c_goodsNo = this.query.getColumns().get(3);
			this.c_goodsProperty = this.query.getColumns().get(4);
			this.c_goodsStoreAmount = this.query.getColumns().get(5);
			this.c_goodsUnit = this.query.getColumns().get(6);
			this.c_goodsUnitCost = this.query.getColumns().get(7);
			this.c_initAmount = this.query.getColumns().get(8);
			this.c_initCount = this.query.getColumns().get(9);
			this.c_initUnitCost = this.query.getColumns().get(10);
			this.c_isInit = this.query.getColumns().get(11);
			this.c_onWayCount = this.query.getColumns().get(12);
			this.c_recid = this.query.getColumns().get(13);
			this.c_storeAmountUpper = this.query.getColumns().get(14);
			this.c_storeFloor = this.query.getColumns().get(15);
			this.c_storeGuid = this.query.getColumns().get(16);
			this.c_storeType = this.query.getColumns().get(17);
			this.c_storeUpper = this.query.getColumns().get(18);
			this.c_tenantsGuid = this.query.getColumns().get(19);
			this.c_toDeliverCount = this.query.getColumns().get(20);
		}
	}
