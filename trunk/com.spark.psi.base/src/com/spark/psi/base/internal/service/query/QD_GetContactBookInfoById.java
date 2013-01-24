package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetContactBookInfoById extends QueryStatementDeclarator {


		public final ArgumentDefine arg_id;

		public final QueryColumnDefine c_name;
		public final QueryColumnDefine c_sexCode;
		public final QueryColumnDefine c_nickName;
		public final QueryColumnDefine c_mobileNumber;
		public final QueryColumnDefine c_landLineNumber;
		public final QueryColumnDefine c_email;
		public final QueryColumnDefine c_company;
		public final QueryColumnDefine c_department;
		public final QueryColumnDefine c_position;
		public final QueryColumnDefine c_qqNumber;
		public final QueryColumnDefine c_msnNumber;
		public final QueryColumnDefine c_wwNumber;
		public final QueryColumnDefine c_birth;
		public final QueryColumnDefine c_hobbies;
		public final QueryColumnDefine c_memo;
		public final QueryColumnDefine c_partnerId;
		public final QueryColumnDefine c_province;
		public final QueryColumnDefine c_city;
		public final QueryColumnDefine c_district;
		public final QueryColumnDefine c_address;
		public final QueryColumnDefine c_postCode;
		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_main;
		public final QueryColumnDefine c_type;
		public final QueryColumnDefine c_lastDate;

		public QD_GetContactBookInfoById() {
			this.arg_id = this.query.getArguments().get(0);
			this.c_name = this.query.getColumns().get(0);
			this.c_sexCode = this.query.getColumns().get(1);
			this.c_nickName = this.query.getColumns().get(2);
			this.c_mobileNumber = this.query.getColumns().get(3);
			this.c_landLineNumber = this.query.getColumns().get(4);
			this.c_email = this.query.getColumns().get(5);
			this.c_company = this.query.getColumns().get(6);
			this.c_department = this.query.getColumns().get(7);
			this.c_position = this.query.getColumns().get(8);
			this.c_qqNumber = this.query.getColumns().get(9);
			this.c_msnNumber = this.query.getColumns().get(10);
			this.c_wwNumber = this.query.getColumns().get(11);
			this.c_birth = this.query.getColumns().get(12);
			this.c_hobbies = this.query.getColumns().get(13);
			this.c_memo = this.query.getColumns().get(14);
			this.c_partnerId = this.query.getColumns().get(15);
			this.c_province = this.query.getColumns().get(16);
			this.c_city = this.query.getColumns().get(17);
			this.c_district = this.query.getColumns().get(18);
			this.c_address = this.query.getColumns().get(19);
			this.c_postCode = this.query.getColumns().get(20);
			this.c_recid = this.query.getColumns().get(21);
			this.c_main = this.query.getColumns().get(22);
			this.c_type = this.query.getColumns().get(23);
			this.c_lastDate = this.query.getColumns().get(24);
		}
	}
