package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetContactListByPartnerId extends QueryStatementDeclarator {


		public final ArgumentDefine arg_partnerId;
		public final ArgumentDefine arg_type;

		public final QueryColumnDefine c_id;

		public QD_GetContactListByPartnerId() {
			this.arg_partnerId = this.query.getArguments().get(0);
			this.arg_type = this.query.getArguments().get(1);
			this.c_id = this.query.getColumns().get(0);
		}
	}
