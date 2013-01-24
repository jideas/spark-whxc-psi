package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.ormentity.GoodsTraderLogOrmEntity;
import com.spark.psi.base.storage.goods.TableGoodsTraderLog;

public class Orm_GoodsTraderLogByGoodsId extends ORMDeclarator<GoodsTraderLogOrmEntity>{

	public static final String Name = "Orm_GoodsTraderLogByGoodsId";

	protected QuRelationRefDeclare tableReference;

	public Orm_GoodsTraderLogByGoodsId(TableGoodsTraderLog table){
		super(Name);
		this.tableReference = this.orm.newReference(table);
		this.orm.newColumn(table.f_id, "id");
		this.orm.newColumn(table.f_goodsItemId, "goodsItemId");
		this.orm.newColumn(table.f_partnerId, "partnerId");
		this.orm.newColumn(table.f_type, "type");
		this.orm.newColumn(table.f_data, "data");
		this.orm.setCondition(tableReference.expOf(table.f_goodsId).xEq(
		        this.orm.newArgument(table.f_goodsId)).and(
		        tableReference.expOf(table.f_type).xEq(
		                orm.newArgument(table.f_type))));
	}

}
