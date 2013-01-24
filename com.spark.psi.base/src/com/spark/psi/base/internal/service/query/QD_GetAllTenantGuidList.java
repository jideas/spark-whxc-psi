package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllTenantGuidList extends QueryStatementDeclarator {


		public final QueryColumnDefine c_recid;

		public QD_GetAllTenantGuidList() {
			this.c_recid = this.query.getColumns().get(0);
		}
	}
