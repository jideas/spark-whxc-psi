package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetStoreSalesEmployeeListByTenantId extends QueryStatementDeclarator {


		public final ArgumentDefine arg_tenantId;
		public final ArgumentDefine arg_type;

		public final QueryColumnDefine c_employeeGuid;
		public final QueryColumnDefine c_storeGuid;

		public QD_GetStoreSalesEmployeeListByTenantId() {
			this.arg_tenantId = this.query.getArguments().get(0);
			this.arg_type = this.query.getArguments().get(1);
			this.c_employeeGuid = this.query.getColumns().get(0);
			this.c_storeGuid = this.query.getColumns().get(1);
		}
	}
