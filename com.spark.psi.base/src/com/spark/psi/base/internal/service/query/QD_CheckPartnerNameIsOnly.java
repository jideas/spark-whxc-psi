package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_CheckPartnerNameIsOnly extends QueryStatementDeclarator {


		public final ArgumentDefine arg_tenantId;
		public final ArgumentDefine arg_type;
		public final ArgumentDefine arg_name;
		public final ArgumentDefine arg_shortName;
		public final ArgumentDefine arg_id;

		public final QueryColumnDefine c_count;

		public QD_CheckPartnerNameIsOnly() {
			this.arg_tenantId = this.query.getArguments().get(0);
			this.arg_type = this.query.getArguments().get(1);
			this.arg_name = this.query.getArguments().get(2);
			this.arg_shortName = this.query.getArguments().get(3);
			this.arg_id = this.query.getArguments().get(4);
			this.c_count = this.query.getColumns().get(0);
		}
	}
