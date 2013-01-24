package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetPartnerById extends QueryStatementDeclarator {


		public final ArgumentDefine arg_id;

		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_cusproName;
		public final QueryColumnDefine c_cusproShortName;
		public final QueryColumnDefine c_fax;
		public final QueryColumnDefine c_creditline;
		public final QueryColumnDefine c_tenantsGuid;
		public final QueryColumnDefine c_cusproType;
		public final QueryColumnDefine c_busPerson;
		public final QueryColumnDefine c_province;
		public final QueryColumnDefine c_city;
		public final QueryColumnDefine c_address;
		public final QueryColumnDefine c_accountPeriod;
		public final QueryColumnDefine c_accountRemind;
		public final QueryColumnDefine c_creditLinePerson;
		public final QueryColumnDefine c_isReflag;
		public final QueryColumnDefine c_cusproGrd;

		public QD_GetPartnerById() {
			this.arg_id = this.query.getArguments().get(0);
			this.c_recid = this.query.getColumns().get(0);
			this.c_cusproName = this.query.getColumns().get(1);
			this.c_cusproShortName = this.query.getColumns().get(2);
			this.c_fax = this.query.getColumns().get(3);
			this.c_creditline = this.query.getColumns().get(4);
			this.c_tenantsGuid = this.query.getColumns().get(5);
			this.c_cusproType = this.query.getColumns().get(6);
			this.c_busPerson = this.query.getColumns().get(7);
			this.c_province = this.query.getColumns().get(8);
			this.c_city = this.query.getColumns().get(9);
			this.c_address = this.query.getColumns().get(10);
			this.c_accountPeriod = this.query.getColumns().get(11);
			this.c_accountRemind = this.query.getColumns().get(12);
			this.c_creditLinePerson = this.query.getColumns().get(13);
			this.c_isReflag = this.query.getColumns().get(14);
			this.c_cusproGrd = this.query.getColumns().get(15);
		}
	}
