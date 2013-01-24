package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllDepartmentList extends QueryStatementDeclarator {


		public final ArgumentDefine arg_r;

		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_name;
		public final QueryColumnDefine c_parent;
		public final QueryColumnDefine c_vaild;
		public final QueryColumnDefine c_dtype;
		public final QueryColumnDefine c_tenantId;

		public QD_GetAllDepartmentList() {
			this.arg_r = this.query.getArguments().get(0);
			this.c_recid = this.query.getColumns().get(0);
			this.c_name = this.query.getColumns().get(1);
			this.c_parent = this.query.getColumns().get(2);
			this.c_vaild = this.query.getColumns().get(3);
			this.c_dtype = this.query.getColumns().get(4);
			this.c_tenantId = this.query.getColumns().get(5);
		}
	}
