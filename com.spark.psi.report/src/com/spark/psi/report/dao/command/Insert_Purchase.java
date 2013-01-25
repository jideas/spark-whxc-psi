package com.spark.psi.report.dao.command;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.InsertStatementDeclarator;

public final class Insert_Purchase extends InsertStatementDeclarator {


		public final ArgumentDefine arg_recid;
		public final ArgumentDefine arg_tenantId;
		public final ArgumentDefine arg_cusProGuid;
		public final ArgumentDefine arg_dateNo;
		public final ArgumentDefine arg_monthNo;
		public final ArgumentDefine arg_quarter;
		public final ArgumentDefine arg_yearNo;
		public final ArgumentDefine arg_ordAmount;
		public final ArgumentDefine arg_outstoAmount;
		public final ArgumentDefine arg_receiptAmount;
		public final ArgumentDefine arg_deductionAmount;
		public final ArgumentDefine arg_rtnAmount;
		public final ArgumentDefine arg_createDate;


		public Insert_Purchase() {
			this.arg_recid = this.statement.getArguments().get(0);
			this.arg_tenantId = this.statement.getArguments().get(1);
			this.arg_cusProGuid = this.statement.getArguments().get(2);
			this.arg_dateNo = this.statement.getArguments().get(3);
			this.arg_monthNo = this.statement.getArguments().get(4);
			this.arg_quarter = this.statement.getArguments().get(5);
			this.arg_yearNo = this.statement.getArguments().get(6);
			this.arg_ordAmount = this.statement.getArguments().get(7);
			this.arg_outstoAmount = this.statement.getArguments().get(8);
			this.arg_receiptAmount = this.statement.getArguments().get(9);
			this.arg_deductionAmount = this.statement.getArguments().get(10);
			this.arg_rtnAmount = this.statement.getArguments().get(11);
			this.arg_createDate = this.statement.getArguments().get(12);
		}
	}
