package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetContactPersonResourceById extends QueryStatementDeclarator {


		public final ArgumentDefine arg_id;

		public final QueryColumnDefine c_id;
		public final QueryColumnDefine c_name;
		public final QueryColumnDefine c_mobileNumber;
		public final QueryColumnDefine c_landLineNumber;
		public final QueryColumnDefine c_partnerId;
		public final QueryColumnDefine c_province;
		public final QueryColumnDefine c_department;
		public final QueryColumnDefine c_city;
		public final QueryColumnDefine c_district;
		public final QueryColumnDefine c_address;
		public final QueryColumnDefine c_postCode;
		public final QueryColumnDefine c_type;
		public final QueryColumnDefine c_email;
		public final QueryColumnDefine c_lastDate;

		public QD_GetContactPersonResourceById() {
			this.arg_id = this.query.getArguments().get(0);
			this.c_id = this.query.getColumns().get(0);
			this.c_name = this.query.getColumns().get(1);
			this.c_mobileNumber = this.query.getColumns().get(2);
			this.c_landLineNumber = this.query.getColumns().get(3);
			this.c_partnerId = this.query.getColumns().get(4);
			this.c_province = this.query.getColumns().get(5);
			this.c_department = this.query.getColumns().get(6);
			this.c_city = this.query.getColumns().get(7);
			this.c_district = this.query.getColumns().get(8);
			this.c_address = this.query.getColumns().get(9);
			this.c_postCode = this.query.getColumns().get(10);
			this.c_type = this.query.getColumns().get(11);
			this.c_email = this.query.getColumns().get(12);
			this.c_lastDate = this.query.getColumns().get(13);
		}
	}
