package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllGoodsCatgoryList extends QueryStatementDeclarator {


		public final QueryColumnDefine c_count;

		public QD_GetAllGoodsCatgoryList() {
			this.c_count = this.query.getColumns().get(0);
		}
	}
