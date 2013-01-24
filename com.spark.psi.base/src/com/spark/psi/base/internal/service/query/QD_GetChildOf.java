package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetChildOf extends QueryStatementDeclarator {


		public final ArgumentDefine arg_rec;

		public final QueryColumnDefine c_RECID;
		public final QueryColumnDefine c_stauts;

		public QD_GetChildOf() {
			this.arg_rec = this.query.getArguments().get(0);
			this.c_RECID = this.query.getColumns().get(0);
			this.c_stauts = this.query.getColumns().get(1);
		}
	}
