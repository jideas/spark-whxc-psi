package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.ormentity.GoodsTraderLogOrmEntity;
import com.spark.psi.base.storage.goods.TableGoodsTraderLog;

public class Orm_GoodsTraderLogByGoodsItemIdAndPartnerId extends ORMDeclarator<GoodsTraderLogOrmEntity>{

	public static final String Name = "Orm_GoodsTraderLogByGoodsItemIdAndPartnerId";

	protected QuRelationRefDeclare tableReference;
	
	public Orm_GoodsTraderLogByGoodsItemIdAndPartnerId(TableGoodsTraderLog table){
	    super(Name);
	    this.tableReference = this.orm.newReference(table);
	    this.orm.newColumn(table.f_id, "id");
	    this.orm.newColumn(table.f_goodsItemId,"goodsItemId");
	    this.orm.newColumn(table.f_partnerId,"partnerId");
	    this.orm.newColumn(table.f_type,"type");
	    this.orm.newColumn(table.f_data,"data");
		this.orm.setCondition(tableReference.expOf(table.f_goodsItemId).xEq(
		        this.orm.newArgument(table.f_goodsItemId)).and(
		        tableReference.expOf(table.f_partnerId).xEq(
		                this.orm.newArgument(table.f_partnerId))));
    }
	
	
}
