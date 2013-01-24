package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllSysParam extends QueryStatementDeclarator {


		public final QueryColumnDefine c_key;
		public final QueryColumnDefine c_value;
		public final QueryColumnDefine c_tenantsGuid;
		public final QueryColumnDefine c_recid;

		public QD_GetAllSysParam() {
			this.c_key = this.query.getColumns().get(0);
			this.c_value = this.query.getColumns().get(1);
			this.c_tenantsGuid = this.query.getColumns().get(2);
			this.c_recid = this.query.getColumns().get(3);
		}
	}
