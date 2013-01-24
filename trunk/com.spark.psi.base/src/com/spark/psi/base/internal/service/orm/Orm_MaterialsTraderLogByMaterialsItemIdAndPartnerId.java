package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.ormentity.MaterialsTraderLogOrmEntity;
import com.spark.psi.base.storage.goods.TableMaterialsTraderLog;

public class Orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId extends ORMDeclarator<MaterialsTraderLogOrmEntity>{

	public static final String Name = "Orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId";

	protected QuRelationRefDeclare tableReference;
	
	public Orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId(TableMaterialsTraderLog table){
	    super(Name);
	    this.tableReference = this.orm.newReference(table);
	    this.orm.newColumn(table.f_id, "id");
	    this.orm.newColumn(table.f_materialsItemId,"materialsItemId");
	    this.orm.newColumn(table.f_partnerId,"partnerId");
	    this.orm.newColumn(table.f_type,"type");
	    this.orm.newColumn(table.f_data,"data");
		this.orm.setCondition(tableReference.expOf(table.f_materialsItemId).xEq(
		        this.orm.newArgument(table.f_materialsItemId)).and(
		        tableReference.expOf(table.f_partnerId).xEq(
		                this.orm.newArgument(table.f_partnerId))));
    }
	
	
}
