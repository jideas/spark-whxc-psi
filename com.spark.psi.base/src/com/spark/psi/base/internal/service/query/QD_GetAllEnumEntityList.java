package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllEnumEntityList extends QueryStatementDeclarator {


		public final QueryColumnDefine c_typeName;
		public final QueryColumnDefine c_code;
		public final QueryColumnDefine c_codeName;
		public final QueryColumnDefine c_parentCode;

		public QD_GetAllEnumEntityList() {
			this.c_typeName = this.query.getColumns().get(0);
			this.c_code = this.query.getColumns().get(1);
			this.c_codeName = this.query.getColumns().get(2);
			this.c_parentCode = this.query.getColumns().get(3);
		}
	}
