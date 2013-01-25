/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.purchase.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-1     modi 
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.purchase.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-1     modi 
 * ============================================================*/

package com.spark.order.purchase.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.task.PurchaseGoodsTask2;
import com.spark.order.sales.intf.task.ModifyDirectGoodsStopStatusTask;
import com.spark.order.service.dao.sql.impl.modify.ModifyDirectGoodsStopStatusSql;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.order.util.dnaSql.IDnaSql;
import com.spark.psi.order.purchase.ORM_BuyOrdeByStoreAndGoods;
import com.spark.psi.order.storage.purchase.Orm_PurchaseGoods;

/**
 * <p>采购商品Service，包括直供</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */

public class PurchaseGoodsService extends Service {

	protected final Orm_PurchaseGoods q_Orm_PurchaseGoods; 
	protected final ORM_BuyOrdeByStoreAndGoods q_Orm_lastPurchase;
	

	protected PurchaseGoodsService(Orm_PurchaseGoods qOrmPurchaseGoods,  
			ORM_BuyOrdeByStoreAndGoods q_Orm_lastPurchase) {
		super("com.spark.order.purchase.service.PurchaseGoodsService");
		q_Orm_PurchaseGoods = qOrmPurchaseGoods; 
		this.q_Orm_lastPurchase = q_Orm_lastPurchase;
	}
	
	/**
	 * <p>销售订单中止或重新执行修改直供商品中止状态</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 莫迪
	 * @version 2012-1-6
	 */
	@Publish
	protected class ModifyDirectGoodsStopTypeHandle extends SimpleTaskMethodHandler<ModifyDirectGoodsStopStatusTask>{

		@Override
		protected void handle(Context context, ModifyDirectGoodsStopStatusTask task)
				throws Throwable {
			ModifyDirectGoodsStopStatusSql sql = new ModifyDirectGoodsStopStatusSql(context);
			DBCommand db = sql.getDB(task);
			GUID tenantsGuid = task.getTenantsGuid() == null ? BillsConstant.getTenantsGuid(context):task.getTenantsGuid();
			db.setArgumentValues(tenantsGuid, task.getSaleRecid(), task.isStoped());
			task.lenght = db.executeUpdate();
		}
	}

	@Publish
	protected class BasePurchaseGoods2Provider extends
			OneKeyResultProvider<PurchaseGoods2, GUID> {
		@Override
		protected PurchaseGoods2 provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<PurchaseGoods2> acc = context
					.newORMAccessor(q_Orm_PurchaseGoods);
			return acc.findByRECID(id);
		}
	} 

	/**
	 * <p>获取当前采购清单列表</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-1
	 */
	@Publish
	protected class AllPurchaseGoods2Provider extends
			ResultListProvider<PurchaseGoods2> {
		@Override
		protected void provide(Context context, List<PurchaseGoods2> resultList)
				throws Throwable {  
			ORMAccessor<PurchaseGoods2> acc = context
					.newORMAccessor(q_Orm_PurchaseGoods);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context), BillsConstant.getUserGuid(context)));
		}
	}

	@Publish
	protected class AddPurchaseGoods2Handler extends
			TaskMethodHandler<PurchaseGoodsTask2, PurchaseGoodsTask2.Method> {
		public AddPurchaseGoods2Handler() {
			super(PurchaseGoodsTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, PurchaseGoodsTask2 task)
				throws Throwable {
			ORMAccessor<PurchaseGoods2> acc = context
					.newORMAccessor(q_Orm_PurchaseGoods);
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifyPurchaseGoods2Handler extends
			TaskMethodHandler<PurchaseGoodsTask2, PurchaseGoodsTask2.Method> {
		public ModifyPurchaseGoods2Handler() {
			super(PurchaseGoodsTask2.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, PurchaseGoodsTask2 task)
				throws Throwable {
			ORMAccessor<PurchaseGoods2> acc = context
					.newORMAccessor(q_Orm_PurchaseGoods);
			acc.update(task.entity);
		}
	}

	@Publish
	protected class DeletePurchaseGoods2Handler extends
			TaskMethodHandler<PurchaseGoodsTask2, PurchaseGoodsTask2.Method> {
		public DeletePurchaseGoods2Handler() {
			super(PurchaseGoodsTask2.Method.DELETE);
		}

		@Override
		protected void handle(Context context, PurchaseGoodsTask2 task)
				throws Throwable {
			ORMAccessor<PurchaseGoods2> acc = context
					.newORMAccessor(q_Orm_PurchaseGoods);
			acc.delete(task.recid);
		}
	}   
	//========采购记录相关查询===========
	@Publish
	class SelectLasPurchasePartneProvider extends OneKeyResultProvider<PurchaseOrderInfo, SelectLastPurchasePartnerKey>{

		@Override
		protected PurchaseOrderInfo provide(Context context,
				SelectLastPurchasePartnerKey key) throws Throwable {
			ORMAccessor<PurchaseOrderInfo> acc = context.newORMAccessor(q_Orm_lastPurchase);
			return acc.first(BillsConstant.getTenantsGuid(context), key.getStoreId(), key.getGoodsItemid());
		}
	}
	@Publish
	class SelectLastPurchasePriceProvider extends OneKeyResultProvider<Double, SelectLastPurchasePriceKey>{

		@Override
		protected Double provide(Context context, SelectLastPurchasePriceKey key)
				throws Throwable {
			IDnaSql sql = new SelectLastPurchasePriceSql(context, key);
			Object obj = sql.getDBCommand_FinishedParams().executeScalar();
			if(null != obj){
				return (Double)obj;
			}
			return null;
		}
		class SelectLastPurchasePriceSql extends DnaSql_query{
			private final SelectLastPurchasePriceKey key;
			public SelectLastPurchasePriceSql(Context context, SelectLastPurchasePriceKey key) {
				super(context);
				this.key = key;
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" select ");
				sql.append(" t.price as price ");
				sql.append(" from ");
				sql.append(" PSI_Purchase_Order AS o JOIN PSI_Purchase_Det AS t ");
				sql.append(" on  o.RECID = t.billsId and o.partnerId = @partnerId "); 
				this.addParam("@partnerId guid", key.getPartnerId());
				sql.append(" where ");
				sql.append(" t.goodsId = @goodsItemId ");
				this.addParam("@goodsItemId guid", key.getGoodsItemId());
				sql.append(" order by ");
				sql.append(" t.createDate ");
				sql.append(" desc ");
				return sql.toString();
			}
			
		}
	}

}
