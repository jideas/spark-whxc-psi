/**
 * 
 */
/**
 * 
 */
package com.spark.psi.inventory.internal.service;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.inventory.internal.entity.Dismounting;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.dismounting.DismountingKey;
import com.spark.psi.inventory.intf.task.dismounting.DismountingItemTask;
import com.spark.psi.inventory.intf.task.dismounting.DismountingTask;
import com.spark.psi.inventory.intf.util.dismounting.StoreDismountingOperator;
import com.spark.psi.inventory.service.orm.Orm_Store_Dismounting;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;

/**
 * @author durendong
 * 
 */
public class StoreDismountingService extends Service {

	private Orm_Store_Dismounting ormStoreDismounting;

	protected StoreDismountingService(Orm_Store_Dismounting ormStoreDismounting) {
		super(
				"com.jiuqi.assa.bus.store.dismounting.service.StoreDismountingService");
		this.ormStoreDismounting = ormStoreDismounting;
	}

	/**
	 * 查询
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class queryAll extends
			OneKeyResultListProvider<Dismounting, DismountingKey> {

		@Override
		protected void provide(Context context, DismountingKey dismountingKey,
				List<Dismounting> dismountings) throws Throwable {
			Login login = context.find(Login.class);
			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();

			dnaSql.append("define query queryDismount(@tenantId guid");
			paramList.add(login.getTenantId());
			conditionSql.append(" where t.tenantsGuid=@tenantId \n");
			List<GUID> storeIdList = new ArrayList<GUID>();

			GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE,
					StoreStatus.ONCOUNTING, StoreStatus.STOP);
			ListEntity<StoreItem> listEntity = context.find(ListEntity.class,
					key);
			if (CheckIsNull.isNotEmpty(listEntity.getItemList())) {
				for (StoreItem item : listEntity.getItemList()) {
					storeIdList.add(item.getId());
				}
			}

			if (CheckIsNull.isEmpty(storeIdList)) {
				dnaSql.append(",@markPerson guid");
				conditionSql.append("and t.markPerson=@markPerson\n");
				paramList.add(login.getEmployeeId());
			} else {
				conditionSql.append("and t.storeGuid in(");
				for (int i = 0; i < storeIdList.size(); i++) {
					dnaSql.append(",@storeGuid").append(i).append(" guid");
					if (0 == i) {
						conditionSql.append("@storeGuid").append(i);
					} else {
						conditionSql.append(",@storeGuid").append(i);
					}
					paramList.add(storeIdList.get(i));
				}
				conditionSql.append(")\n");
			}
			if (CheckIsNull.isNotEmpty(dismountingKey.getSearceText())) {
				dnaSql.append(",@searchText string");
				conditionSql
						.append("and (t.storeName like '%'+@searchText+'%'");
				conditionSql.append(" or t.storePY like '%'+@searchText+'%'");
				conditionSql.append(" or t.dismOrdNo like '%'+@searchText+'%'");
				conditionSql
						.append(" or t.createPerson like '%'+@searchText+'%')");
				;
				paramList.add(dismountingKey.getSearceText());
			}
			dnaSql.append(") begin\n");
			StoreDismountingOperator.defaultSql(dnaSql);
			dnaSql.append("\n").append(conditionSql);
			if (CheckIsNull.isNotEmpty(dismountingKey.getSortField())) {
				dnaSql.append(" order by t.").append(
						dismountingKey.getSortField()).append(" ").append(
						dismountingKey.getSortType());
			}
			dnaSql.append("\n end");
			DBCommand db = context.prepareStatement(dnaSql);
			for (int i = 0; i < paramList.size(); i++) {
				db.setArgumentValue(i, paramList.get(i));
			}
			try {
				RecordSet rs;
				if (dismountingKey.getCount() > 0) {
					rs = db.executeQueryLimit(dismountingKey.getOffset(),
							dismountingKey.getCount());
				} else {
					rs = db.executeQuery();
				}
				while (rs.next()) {
					dismountings.add(StoreDismountingOperator
							.setStoreDismounting(rs));
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 查询一条拆装单
	 */
	@Publish
	protected class GetDismountingById extends
			OneKeyResultProvider<Dismounting, GUID> {

		@Override
		protected Dismounting provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<Dismounting> orm = context
					.newORMAccessor(ormStoreDismounting);
			try {
				return orm.findByRECID(id);
			} finally {
				orm.unuse();
			}
		}

	}

	/**
	 * 新增
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class dismountingAddTask extends
			TaskMethodHandler<DismountingTask, Method> {

		protected dismountingAddTask() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context,
				DismountingTask storeDismountingTask) throws Throwable {
			ORMAccessor<Dismounting> accessor = context
					.newORMAccessor(ormStoreDismounting);

			try {
				String sheetNumber = context.get(String.class,
						SheetNumberType.InventoryRefactor);
				storeDismountingTask.getDismounting().setDismOrdNo(sheetNumber);
				accessor.insert(storeDismountingTask.getDismounting());
				DismountingItemTask itemTask = new DismountingItemTask();
				itemTask.setSheetId(storeDismountingTask.getDismounting()
						.getRECID());
				itemTask.setSheetNumber(sheetNumber);
				itemTask.setStoreId(storeDismountingTask.getDismounting()
						.getStoreGuid());
				itemTask.setList(storeDismountingTask.getList());
				context.handle(itemTask, Method.INSERT);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				accessor.unuse();
			}
		}

	}

	/**
	 * 删除
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class dismountingDelTask extends
			TaskMethodHandler<DismountingTask, Method> {

		protected dismountingDelTask() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context,
				DismountingTask storeDismountingTask) throws Throwable {
			ORMAccessor<Dismounting> accessor = context
					.newORMAccessor(ormStoreDismounting);
			try {
				accessor.delete(storeDismountingTask.getDismounting());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				accessor.unuse();
			}
		}

	}
}
