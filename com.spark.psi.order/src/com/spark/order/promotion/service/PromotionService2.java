/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.promotion.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-30     modi 
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.promotion.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-30     modi 
 * ============================================================*/

package com.spark.order.promotion.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.exceptions.DataStatusExpireException;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionListEntity2;
import com.spark.order.promotion.intf.PromotionSaledCountTask;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.promotion.intf.PromotionTask2;
import com.spark.order.promotion.intf.SelectPromotionKey;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.order.util.dnaSql.DnaSql_update;
import com.spark.order.util.dnaSql.IDnaSql;
import com.spark.psi.base.Department;
import com.spark.psi.base.event.TimerEvent;
import com.spark.psi.order.storage.Orm_Promotion2;
import com.spark.psi.order.storage.Orm_Promotion2ByGoods;

/**
 * <p>促销2Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */

public class PromotionService2 extends Service {

	protected final Orm_Promotion2 q_Orm_Promotion2;
	protected final Orm_Promotion2ByGoods orm_promotion2_goods;

	protected PromotionService2(Orm_Promotion2 qOrmPromotion2, Orm_Promotion2ByGoods orm_promotion2_goods) {
		super("com.spark.order.promotion.service.PromotionService2");
		q_Orm_Promotion2 = qOrmPromotion2;
		this.orm_promotion2_goods = orm_promotion2_goods;
	}
	
	@Publish
	protected class SelectPromotionPriceByRetailProvider extends OneKeyResultProvider<Double, SelectPromotionPriceByRetailKey>{

		@Override
		protected Double provide(Context context,
				SelectPromotionPriceByRetailKey key) throws Throwable {
			List<Promotion2> list = context.getList(Promotion2.class, key.getGoodsItemId());
			if(null == list || 0 == list.size()){
				return null;
			}
			Department dept = OrderUtil.getDepartment(context);
			Promotion2 p = null;
			if(null != dept){
				p = getPromotion2(list, dept.getId());
				while (null == p) {
					if(null == dept.getParent()){
						break;
					}
					dept = dept.getParent(context);
					p = getPromotion2(list, dept.getId());
				}
			}
			return p == null?null:p.getPrice_promotion();
		}
		private Promotion2 getPromotion2(List<Promotion2> list, GUID deptId){
			for(Promotion2 p : list){
				if(p.getDeptId().equals(deptId)){
					return p;
				}
			}
			return null;
		}
	}
	
	@Override
	protected void init(final Context context) throws Throwable {
		new PromotionStatus2Timer(context).action();
//		//启动定时线程
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//
//			@Override
//			public void run() {
//				new PromotionStatus2Timer(context).action();
//			}
//		};
//		long today = System.currentTimeMillis();
//		long endOfToday = DateUtil.truncDay(today) + DateUtil.ONE_DATE_TIME;
//		long begin = endOfToday - today;
//		// 从今天结束时开始执行，每天一次
//		timer.schedule(task, begin, DateUtil.ONE_DATE_TIME);
	}
	
	/**
	 * 促销每日状态梳理
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-25
	 */
	@Publish
	protected class Promotion2TimerEventListener extends EventListener<TimerEvent>{

		@Override
		protected void occur(Context context, TimerEvent event)
				throws Throwable {
			if(event.isEndOfToday()){
				new PromotionStatus2Timer(context).action();
			}
		}
	}
	
	
	/**
	 * <p>促销列表</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	@Publish
	protected class SelectPromotionListProvider extends OneKeyResultProvider<PromotionListEntity2, SelectPromotionKey>{

		@Override
		protected PromotionListEntity2 provide(Context context,
				SelectPromotionKey key) throws Throwable {
			SelectPromotionSql sql = new SelectPromotionSql(context, key);
			return new PromotionListEntity2(sql.getList(sql.executeQuery(key.getOffset(), key.getPageSize())), sql.rowCountOf());
		}
	}

	@Publish
	protected class BasePromotion2Provider extends
			OneKeyResultProvider<Promotion2, GUID> {
		@Override
		protected Promotion2 provide(Context context, GUID id) throws Throwable {
			ORMAccessor<Promotion2> acc = context
					.newORMAccessor(q_Orm_Promotion2);
			return acc.findByRECID(id);
		}
	}
	
	/**
	 * <p>通过商品GUID查询促销集合</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-30
	 */
	@Publish
	protected class Prommotion2ListByGoodsItemIdProvider extends OneKeyResultListProvider<Promotion2, GUID>{

		@Override
		protected void provide(Context context, GUID key,
				List<Promotion2> resultList) throws Throwable {
			ORMAccessor<Promotion2> acc = context
				.newORMAccessor(orm_promotion2_goods);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context), key, PromotionStatus2.Promotioning.getCode()));
		}
	}

	@Publish
	protected class AllPromotion2Provider extends
			ResultListProvider<Promotion2> {
		@Override
		protected void provide(Context context, List<Promotion2> resultList)
				throws Throwable {
			ORMAccessor<Promotion2> acc = context
					.newORMAccessor(q_Orm_Promotion2);
			resultList.addAll(acc.fetch());
		}
	}

	@Publish
	protected class AddPromotion2Handler extends
			TaskMethodHandler<PromotionTask2, PromotionTask2.Method> {
		public AddPromotion2Handler() {
			super(PromotionTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, PromotionTask2 task)
				throws Throwable {
			ORMAccessor<Promotion2> acc = context
					.newORMAccessor(q_Orm_Promotion2);
			acc.insert(task.entity);
			task.lenght = 1;
		}
	}

	@Publish
	protected class ModifyPromotion2Handler extends
			TaskMethodHandler<PromotionTask2, PromotionTask2.Method> {
		public ModifyPromotion2Handler() {
			super(PromotionTask2.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, PromotionTask2 task)
				throws Throwable {
			ORMAccessor<Promotion2> acc = context
					.newORMAccessor(q_Orm_Promotion2);
			task.lenght = acc.update(task.entity)?1:0;
		}
	}

	@Publish
	protected class DeletePromotion2Handler extends
			TaskMethodHandler<PromotionTask2, PromotionTask2.Method> {
		public DeletePromotion2Handler() {
			super(PromotionTask2.Method.DELETE);
		}

		@Override
		protected void handle(Context context, PromotionTask2 task)
				throws Throwable {
			//记录删除对象
			PromotionDelTask2 delTask = new PromotionDelTask2(); 
			delTask.entity = context.find(Promotion2.class, task.recid);
			context.handle(delTask, PromotionDelTask2.Method.ADD);
			//删除
			ORMAccessor<Promotion2> acc = context
					.newORMAccessor(q_Orm_Promotion2);
			task.lenght = acc.delete(task.recid)?1:0;
		}
	}
	//=======================促销状态处理========================
	/**
	 * <p>更新促销单状态</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	@Publish
	class UpdatePromotionStatus2Handler extends SimpleTaskMethodHandler<PromotionStatusTask>{

		@Override
		protected void handle(Context context, PromotionStatusTask task)
				throws Throwable {
			IDnaSql sql = new PromotionStatusSql(context, task);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() == 1);
		}
	}
	
	/**
	 * <p>修改已销售数量</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	@Publish
	class  AddPromotionSaledCountHandler extends SimpleTaskMethodHandler<PromotionSaledCountTask>{

		@Override
		protected void handle(Context context, PromotionSaledCountTask task)
				throws Throwable {
			IDnaSql sql = new PromotionSaledCountSql(context, task);
			task.lenght = sql.executeUpdate();
			if(task.isSucceed()){
				new FinishedPromotionSql(context, task.getId()).executeUpdate();
			}
			else{
				throw new DataStatusExpireException("已超促销数量！");
			}
		}
		//已售完判断
		class FinishedPromotionSql extends DnaSql_update{
			private final GUID id;

			public FinishedPromotionSql(Context context, GUID id) {
				super(context);
				this.id = id;
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(SelectPromotionSql.promotion_Table);
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" status = @status ");
				this.addParam("@status string", PromotionStatus2.Finished.getCode());
				sql.append(" where ");
				sql.append(" t.recid = @id ");
				this.addParam("@id guid", id);
				sql.append(" and ");
				sql.append(" t.promotionCount = t.saledCount ");
				return sql.toString();
			}
		}
	}

}
