package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetStoreById extends QueryStatementDeclarator {


		public final ArgumentDefine arg_id;

		public final QueryColumnDefine c_name;
		public final QueryColumnDefine c_storeStatus;
		public final QueryColumnDefine c_province;
		public final QueryColumnDefine c_city;
		public final QueryColumnDefine c_country;
		public final QueryColumnDefine c_address;
		public final QueryColumnDefine c_postCode;
		public final QueryColumnDefine c_mobile;
		public final QueryColumnDefine c_consignee;
		public final QueryColumnDefine c_id;
		public final QueryColumnDefine c_recver;
		public final QueryColumnDefine c_createPerson;
		public final QueryColumnDefine c_storeNo;
		public final QueryColumnDefine c_shelfCount;
		public final QueryColumnDefine c_defaultTiersCount;
		public final QueryColumnDefine c_storeType;

		public QD_GetStoreById() {
			this.arg_id = this.query.getArguments().get(0);
			this.c_name = this.query.getColumns().get(0);
			this.c_storeStatus = this.query.getColumns().get(1);
			this.c_province = this.query.getColumns().get(2);
			this.c_city = this.query.getColumns().get(3);
			this.c_country = this.query.getColumns().get(4);
			this.c_address = this.query.getColumns().get(5);
			this.c_postCode = this.query.getColumns().get(6);
			this.c_mobile = this.query.getColumns().get(7);
			this.c_consignee = this.query.getColumns().get(8);
			this.c_id = this.query.getColumns().get(9);
			this.c_recver = this.query.getColumns().get(10);
			this.c_createPerson = this.query.getColumns().get(11);
			this.c_storeNo = this.query.getColumns().get(12);
			this.c_shelfCount = this.query.getColumns().get(13);
			this.c_defaultTiersCount = this.query.getColumns().get(14);
			this.c_storeType = this.query.getColumns().get(15);
		}
	}
