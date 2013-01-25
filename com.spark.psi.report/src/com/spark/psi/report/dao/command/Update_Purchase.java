package com.spark.psi.report.dao.command;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.UpdateStatementDeclarator;

public final class Update_Purchase extends UpdateStatementDeclarator {


		public final ArgumentDefine arg_tenantId;
		public final ArgumentDefine arg_cusProGuid;
		public final ArgumentDefine arg_dateNo;
		public final ArgumentDefine arg_ordAmount;
		public final ArgumentDefine arg_outstoAmount;
		public final ArgumentDefine arg_receiptAmount;
		public final ArgumentDefine arg_deductionAmount;
		public final ArgumentDefine arg_rtnAmount;


		public Update_Purchase() {
			this.arg_tenantId = this.statement.getArguments().get(0);
			this.arg_cusProGuid = this.statement.getArguments().get(1);
			this.arg_dateNo = this.statement.getArguments().get(2);
			this.arg_ordAmount = this.statement.getArguments().get(3);
			this.arg_outstoAmount = this.statement.getArguments().get(4);
			this.arg_receiptAmount = this.statement.getArguments().get(5);
			this.arg_deductionAmount = this.statement.getArguments().get(6);
			this.arg_rtnAmount = this.statement.getArguments().get(7);
		}
	}
