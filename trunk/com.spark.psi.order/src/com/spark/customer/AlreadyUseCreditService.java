/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.customer
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-9     jiuqi      
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.customer
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-9     jiuqi      
 * ============================================================*/

package com.spark.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.service.dao.sql.impl.modify.AlreadyUseCreditSql;
import com.spark.psi.base.key.GetCustomerAvailableCreditAmountKey;
import com.spark.psi.order.storage.ORM_AlreadyUseCredit;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;

/**
 * <p>已用信用额度</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */

public class AlreadyUseCreditService extends Service {

	protected final ORM_AlreadyUseCredit q_ORM_AlreadyUseCredit;

	protected AlreadyUseCreditService(ORM_AlreadyUseCredit qORMAlreadyUseCredit) {
		super("AlreadyUseCreditService");
		q_ORM_AlreadyUseCredit = qORMAlreadyUseCredit;
	}

	/**
	 * <p>客户GUID查询客户已用信用额度</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-29
	 */
	@Publish
	protected class BaseAlreadyUseCreditProvider extends
			OneKeyResultProvider<AlreadyUseCredit, GUID> {
		@Override
		protected AlreadyUseCredit provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<AlreadyUseCredit> acc = context
					.newORMAccessor(q_ORM_AlreadyUseCredit);
			return acc.findByPKey(id);
		}
	}

//	@Publish
//	protected class AllAlreadyUseCreditProvider extends
//			ResultListProvider<AlreadyUseCredit> {
//		@Override
//		protected void provide(Context context,
//				List<AlreadyUseCredit> resultList) throws Throwable {
//			ORMAccessor<AlreadyUseCredit> acc = context
//					.newORMAccessor(q_ORM_AlreadyUseCredit);
//			resultList.addAll(acc.fetch());
//		}
//	}

	@Publish
	protected class AddAlreadyUseCreditHandler
			extends
			TaskMethodHandler<AlreadyUseCreditTask, AlreadyUseCreditTask.Method> {
		public AddAlreadyUseCreditHandler() {
			super(AlreadyUseCreditTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, AlreadyUseCreditTask task)
				throws Throwable {
//			ORMAccessor<AlreadyUseCredit> acc = context
//					.newORMAccessor(q_ORM_AlreadyUseCredit);
//			acc.update(task.getEntity());
			AlreadyUseCreditSql sql = new AlreadyUseCreditSql(context);
			DBCommand db = sql.getDB(null);
//			"@cusId guid, @change double";
			db.setArgumentValues(task.getCustomerId(), task.getChangeAmount());
			task.setLenght(db.executeUpdate());
			//如无，自动加入一条记录
			if(0 == task.lenght()){
				AlreadyUseCredit auc = new AlreadyUseCredit();
				auc.setAleardyUseCredit(task.getChangeAmount());
				auc.setCustomerId(task.getCustomerId());
				auc.setId(context.newRECID());
				AlreadyUseCreditTask addTask = new AlreadyUseCreditTask(auc);
				context.handle(addTask, AlreadyUseCreditTask.Method.Create);
			}
			task.setSucceed(true);
		}
	}

	@Publish
	protected class SUBAlreadyUseCreditHandler
			extends
			TaskMethodHandler<AlreadyUseCreditTask, AlreadyUseCreditTask.Method> {
		public SUBAlreadyUseCreditHandler() {
			super(AlreadyUseCreditTask.Method.SUB);
		}

		@Override
		protected void handle(Context context, AlreadyUseCreditTask task)
				throws Throwable {
//			ORMAccessor<AlreadyUseCredit> acc = context
//					.newORMAccessor(q_ORM_AlreadyUseCredit);
//			acc.update(task.getEntity());
			AlreadyUseCreditSql sql = new AlreadyUseCreditSql(context);
			DBCommand db = sql.getDB(null);
//			"@cusId guid, @change double";
			db.setArgumentValues(task.getCustomerId(), -task.getChangeAmount());
			task.setLenght(db.executeUpdate());
			//如无，自动加入一条记录
			if(0 == task.lenght()){
				AlreadyUseCredit auc = new AlreadyUseCredit();
				auc.setAleardyUseCredit(-task.getChangeAmount());
				auc.setCustomerId(task.getCustomerId());
				auc.setId(context.newRECID());
				AlreadyUseCreditTask addTask = new AlreadyUseCreditTask(auc);
				context.handle(addTask, AlreadyUseCreditTask.Method.Create);
			}
			task.setSucceed(true);
		}
	}

	@Publish
	protected class CreateAlreadyUseCreditHandler
			extends
			TaskMethodHandler<AlreadyUseCreditTask, AlreadyUseCreditTask.Method> {
		public CreateAlreadyUseCreditHandler() {
			super(AlreadyUseCreditTask.Method.Create);
		}

		@Override
		protected void handle(Context context, AlreadyUseCreditTask task)
				throws Throwable {
			ORMAccessor<AlreadyUseCredit> acc = context
					.newORMAccessor(q_ORM_AlreadyUseCredit);
			acc.insert(task.getEntity());
		}
	}
	
	/**
	 * <p>查询当前客户可用信用额度</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-19
	 */
	@Publish
	protected class GetCustomerAvailableCreditAmountProvider extends OneKeyResultProvider<Double, GetCustomerAvailableCreditAmountKey>{

		@Override
		protected Double provide(Context context,
				GetCustomerAvailableCreditAmountKey key) throws Throwable {
			CustomerInfo customer = context.find(CustomerInfo.class, key.getCustomerId());
			AlreadyUseCredit use = context.find(AlreadyUseCredit.class, key.getCustomerId());
			double creditAmount;
			if(null == use){
				creditAmount = customer.getCreditAmount();
			}
			else{
				creditAmount = customer.getCreditAmount() - use.getAleardyUseCredit();
			}
			return creditAmount;
		}
		
	}
}
