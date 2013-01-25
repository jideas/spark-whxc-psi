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

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.psi.order.storage.Orm_PromotionDel2;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */

public class PromotionDelService2 extends Service {

	protected final Orm_PromotionDel2 q_Orm_PromotionDel2;

	protected PromotionDelService2(Orm_PromotionDel2 qOrmPromotionDel2) {
		super("com.spark.order.promotion.service.PromotionDelService2");
		q_Orm_PromotionDel2 = qOrmPromotionDel2;
	}
//
//	@Publish
//	protected class BasePromotion2Provider extends
//			OneKeyResultProvider<Promotion2, GUID> {
//		@Override
//		protected Promotion2 provide(Context context, GUID id) throws Throwable {
//			ORMAccessor<Promotion2> acc = context
//					.newORMAccessor(q_Orm_PromotionDel2);
//			return acc.findByRECID(id);
//		}
//	}
//
//	@Publish
//	protected class AllPromotion2Provider extends
//			ResultListProvider<Promotion2> {
//		@Override
//		protected void provide(Context context, List<Promotion2> resultList)
//				throws Throwable {
//			ORMAccessor<Promotion2> acc = context
//					.newORMAccessor(q_Orm_PromotionDel2);
//			resultList.addAll(acc.fetch());
//		}
//	}

	@Publish
	protected class AddPromotion2Handler extends
			TaskMethodHandler<PromotionDelTask2, PromotionDelTask2.Method> {
		public AddPromotion2Handler() {
			super(PromotionDelTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, PromotionDelTask2 task)
				throws Throwable {
			ORMAccessor<Promotion2> acc = context
					.newORMAccessor(q_Orm_PromotionDel2);
			acc.insert(task.entity);
		}
	}
//
//	@Publish
//	protected class ModifyPromotion2Handler extends
//			TaskMethodHandler<PromotionDelTask2, PromotionDelTask2.Method> {
//		public ModifyPromotion2Handler() {
//			super(PromotionDelTask2.Method.MODIFY);
//		}
//
//		@Override
//		protected void handle(Context context, PromotionDelTask2 task)
//				throws Throwable {
//			ORMAccessor<Promotion2> acc = context
//					.newORMAccessor(q_Orm_PromotionDel2);
//			acc.update(task.entity);
//		}
//	}
//
//	@Publish
//	protected class DeletePromotion2Handler extends
//			TaskMethodHandler<PromotionDelTask2, PromotionDelTask2.Method> {
//		public DeletePromotion2Handler() {
//			super(PromotionDelTask2.Method.DELETE);
//		}
//
//		@Override
//		protected void handle(Context context, PromotionDelTask2 task)
//				throws Throwable {
//			ORMAccessor<Promotion2> acc = context
//					.newORMAccessor(q_Orm_PromotionDel2);
//			acc.delete(task.recid);
//		}
//	}
}
