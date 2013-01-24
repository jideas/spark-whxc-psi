package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.FunctionPositionImpl;
import com.spark.psi.base.storage.function.TableFunctionPosition;

public class Orm_FunctionPosition extends ORMDeclarator<FunctionPositionImpl>{
	
	public static final String NAME = "ORM_FunctionPosition";	

	protected QuRelationRefDeclare tableReference;
	
	public Orm_FunctionPosition(TableFunctionPosition table){
	    super(NAME);
	    tableReference = this.orm.newReference(table);
	    this.orm.newColumn(table.f_RECID,"id");
	    this.orm.newColumn(table.f_functionId, "functionId");
	    this.orm.newColumn(table.f_userId, "userId");
	    this.orm.newColumn(table.f_xindex,"xindex");
	    this.orm.newColumn(table.f_yindex,"yindex");
	    this.orm.newColumn(table.f_isPutMain,"putMain");
	    this.orm.setCondition(tableReference.expOf(table.f_userId).xEq(this.orm.newArgument(table.f_userId)));
    }
}
