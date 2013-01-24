package com.spark.psi.base.internal.service.query;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.QueryStatementDeclarator;

public final class QD_GetAllCfgExam extends QueryStatementDeclarator {


		public final QueryColumnDefine c_isOpenExam;
		public final QueryColumnDefine c_maxAmount;
		public final QueryColumnDefine c_modeid;
		public final QueryColumnDefine c_recid;
		public final QueryColumnDefine c_tenantsGuid;
		public final QueryColumnDefine c_updatePerson;
		public final QueryColumnDefine c_updateDate;

		public QD_GetAllCfgExam() {
			this.c_isOpenExam = this.query.getColumns().get(0);
			this.c_maxAmount = this.query.getColumns().get(1);
			this.c_modeid = this.query.getColumns().get(2);
			this.c_recid = this.query.getColumns().get(3);
			this.c_tenantsGuid = this.query.getColumns().get(4);
			this.c_updatePerson = this.query.getColumns().get(5);
			this.c_updateDate = this.query.getColumns().get(6);
		}
	}
