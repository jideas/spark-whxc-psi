package com.spark.psi.inventory.service.common;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.InsertStatementDeclarator;

public final class Insert_OtherStorage extends InsertStatementDeclarator {


		public final ArgumentDefine arg_recid;
		public final ArgumentDefine arg_tenantsGuid;
		public final ArgumentDefine arg_storeGuid;
		public final ArgumentDefine arg_goodsGuid;
		public final ArgumentDefine arg_initCount;
		public final ArgumentDefine arg_goodsName;
		public final ArgumentDefine arg_goodsProperty;
		public final ArgumentDefine arg_storeType;
		public final ArgumentDefine arg_isInit;


		public Insert_OtherStorage() { 
			this.arg_recid = this.statement.getArguments().get(0);
			this.arg_tenantsGuid = this.statement.getArguments().get(1);
			this.arg_storeGuid = this.statement.getArguments().get(2);
			this.arg_goodsGuid = this.statement.getArguments().get(3);
			this.arg_initCount = this.statement.getArguments().get(4);
			this.arg_goodsName = this.statement.getArguments().get(5);
			this.arg_goodsProperty = this.statement.getArguments().get(6);
			this.arg_storeType = this.statement.getArguments().get(7);
			this.arg_isInit = this.statement.getArguments().get(8);
		}
	}
