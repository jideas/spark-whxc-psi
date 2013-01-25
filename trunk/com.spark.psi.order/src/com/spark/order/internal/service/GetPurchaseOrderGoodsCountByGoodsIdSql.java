package com.spark.order.internal.service;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.psi.base.key.GetPurchaseOrderGoodsCountByGoodsIdKey;

public class GetPurchaseOrderGoodsCountByGoodsIdSql extends DnaSql_query{
private final GetPurchaseOrderGoodsCountByGoodsIdKey key;
	public GetPurchaseOrderGoodsCountByGoodsIdSql(Context context, GetPurchaseOrderGoodsCountByGoodsIdKey key) {
		super(context);
		this.key = key;
	}

	@Override
	protected String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(z.num) ");
		sql.append(" from( ");
		sql.append(" select sum(t.\"count\") as num from ");
		//销售订单明细
		sql.append(OrderEnum.Purchase.getDb_table_item());
		sql.append(" as t ");
		sql.append(" join ");
		//销售订单
		sql.append(OrderEnum.Purchase.getDb_table());
		sql.append(" as buy ");
		sql.append(" on  t.billsId = buy.recid ");
		sql.append(" where (buy.status=@status ");
		addParam("@status string", StatusEnum.Submit.getKey());
		sql.append(" or buy.status=@status1 ");
		addParam("@status1 string", StatusEnum.Approve.getKey());
		sql.append(" or buy.status=@status2 ");
		addParam("@status2 string", StatusEnum.Return.getKey());
		sql.append(" ) ");
		//===============
		sql.append(" and t.goodsId = @goodsGuid ");
		addParam("@goodsGuid guid", key.getGoodsItemId());
		sql.append(" and buy.storeId = @storeGuid ");
		addParam("@storeGuid guid", key.getStoreId());
		//采购清单部分
		sql.append(" union ");
		sql.append(" select sum(s.purchaseCount) as num from ");
		sql.append(OrderEnum.Purchase_Goods.getDb_table());
		sql.append(" as s ");
		sql.append(" where  s.creatorId = @userGuid ");
		addParam("@userGuid guid", BillsConstant.getUserGuid(context));
		sql.append(" and s.goodsItemId = @goodsGuid ");
		addParam("@goodsGuid guid", key.getGoodsItemId());
		sql.append(" and s.sourceId = @storeGuid ");
		addParam("@storeGuid guid", key.getStoreId());
		sql.append(" ) as z ");
		return sql.toString();
	}

}
