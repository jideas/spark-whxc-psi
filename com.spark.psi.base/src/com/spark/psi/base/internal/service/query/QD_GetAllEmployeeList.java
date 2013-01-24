package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllEmployeeList extends QueryStatementDeclarator {


		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_empname;
		public final QueryColumnDefine c_birthday;
		public final QueryColumnDefine c_departmentid;
		public final QueryColumnDefine c_email;
		public final QueryColumnDefine c_idnum;
		public final QueryColumnDefine c_mobilephone;
		public final QueryColumnDefine c_duty;
		public final QueryColumnDefine c_roles;
		public final QueryColumnDefine c_rolestatue;
		public final QueryColumnDefine c_tenantsGuid;
		public final QueryColumnDefine c_createDate;
		public final QueryColumnDefine c_empimg;
		public final QueryColumnDefine c_phone;
		public final QueryColumnDefine c_style;

		public QD_GetAllEmployeeList() {
			this.c_recid = this.query.getColumns().get(0);
			this.c_empname = this.query.getColumns().get(1);
			this.c_birthday = this.query.getColumns().get(2);
			this.c_departmentid = this.query.getColumns().get(3);
			this.c_email = this.query.getColumns().get(4);
			this.c_idnum = this.query.getColumns().get(5);
			this.c_mobilephone = this.query.getColumns().get(6);
			this.c_duty = this.query.getColumns().get(7);
			this.c_roles = this.query.getColumns().get(8);
			this.c_rolestatue = this.query.getColumns().get(9);
			this.c_tenantsGuid = this.query.getColumns().get(10);
			this.c_createDate = this.query.getColumns().get(11);
			this.c_empimg = this.query.getColumns().get(12);
			this.c_phone = this.query.getColumns().get(13);
			this.c_style = this.query.getColumns().get(14);
		}
	}
