/**
 * 
 */
/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.SortComparator;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.event.StoreStatusChangeEvent;
import com.spark.psi.base.internal.entity.StoreEmployee;
import com.spark.psi.base.internal.entity.StoreEmployee.StoreEmployeeType;
import com.spark.psi.base.internal.entity.helper.EmployeeHelper;
import com.spark.psi.base.internal.entity.helper.StoreHelper;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.StoreOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Shelf;
import com.spark.psi.base.internal.service.orm.Orm_ShelfItemByStoreId;
import com.spark.psi.base.internal.service.orm.Orm_Store;
import com.spark.psi.base.internal.service.orm.Orm_StoreEmployee;
import com.spark.psi.base.internal.service.query.QD_GetStoreSalesEmployeeListByTenantId;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetStoreBySaleManKey;
import com.spark.psi.base.publicimpl.SelectSalesEmployeeByStoreItemImpl;
import com.spark.psi.base.publicimpl.ShelfItemImpl;
import com.spark.psi.base.publicimpl.StoreInfoImpl;
import com.spark.psi.base.publicimpl.StoreItemImpl;
import com.spark.psi.base.task.UpdateStoreStatusTask;
import com.spark.psi.base.task.resource.UpdateStoreResourceTask;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.StoreType;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.entity.RoleInfo;
import com.spark.psi.publish.base.store.entity.SelectSalesEmployeeByStoreItem;
import com.spark.psi.publish.base.store.entity.ShelfItem;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.entity.StoreSummaryData;
import com.spark.psi.publish.base.store.key.FindEnabledStorageCountKey;
import com.spark.psi.publish.base.store.key.FindStorageCountKey;
import com.spark.psi.publish.base.store.key.GetSalesEmployeeListForStoreKey;
import com.spark.psi.publish.base.store.key.GetSalesEmployeeStoreKey;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.base.store.key.GetStoreListKey.SortField;
import com.spark.psi.publish.base.store.task.ChangeStoreStatusTask;
import com.spark.psi.publish.base.store.task.DeleteStoreTask;
import com.spark.psi.publish.base.store.task.StoreInfoTask;
import com.spark.psi.publish.base.store.task.ValidationStoreNameIsValidTask;
import com.spark.psi.publish.base.store.task.StoreInfoTask.ShelfInfoItem;
import com.spark.psi.publish.exception.PageExpiredException;

/**
 
 * 
 */
public class StorePublishService extends Service {

	private static class Lock {

		private final static Map<GUID, Lock> instances = new HashMap<GUID, Lock>();

		private Lock() {
		}

		public static Lock getInstance(GUID tenantId) {
			Lock instance = instances.get(tenantId);
			if (instance == null) {
				instance = new Lock();
				instances.put(tenantId, instance);
			}
			return instance;
		}

	}

	final Orm_Store orm_Store;

	final Orm_StoreEmployee orm_StoreEmployee;

	final QD_GetStoreSalesEmployeeListByTenantId qD_GetStoreSalesEmployeeListByTenantId;
	
	final Orm_Shelf orm_Shelf;
	final Orm_ShelfItemByStoreId orm_ShelfItemByStoreId;

	protected StorePublishService(
			final Orm_Store orm_Store,
			final Orm_StoreEmployee orm_StoreEmployee,
			final QD_GetStoreSalesEmployeeListByTenantId qD_GetStoreSalesEmployeeListByTenantId,
			final Orm_Shelf orm_Shelf,
			final Orm_ShelfItemByStoreId orm_ShelfItemByStoreId) {
		super("com.spark.psi.base.internal.service.StorePublishService");

		this.orm_Store = orm_Store;
		this.orm_StoreEmployee = orm_StoreEmployee;
		this.qD_GetStoreSalesEmployeeListByTenantId = qD_GetStoreSalesEmployeeListByTenantId;
		this.orm_Shelf = orm_Shelf;
		this.orm_ShelfItemByStoreId = orm_ShelfItemByStoreId;
	}

	/**
	 * 获得仓库维护对象
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetStoreInfoByIdProvider extends
			OneKeyResultProvider<StoreInfo, GUID> {

		@Override
		protected StoreInfo provide(Context context, GUID guid)
				throws Throwable {
			Store store = context.find(Store.class, guid);
			if (store == null) {
				return null;
			}
			StoreInfoImpl entity = StoreHelper.storeToStoreInfo(store);
			List<EmployeeInfo> empList = new ArrayList<EmployeeInfo>();
			if (store.getKeeperIds() != null) {
				for (int i = 0; i < store.getKeeperIds().length; i++) {
					try {
						empList.add(EmployeeHelper.employeeToInfo(context.find(
								Employee.class, store.getKeeperIds()[i])));
					} catch (Throwable t) {
					}
				}
				entity.setKeepers(empList.toArray(new EmployeeInfo[0]));
			}
			ORMAccessor<ShelfItemImpl> orm = context.newORMAccessor(orm_ShelfItemByStoreId);
			List<ShelfItemImpl> shelfList = orm.fetch(entity.getId());
			ShelfItem[] shelfItems = new ShelfItem[shelfList.size()];
			for(int i=0;i<shelfList.size();i++)
			{
				shelfItems[i] = shelfList.get(i);
			}
			entity.setShelfItems(shelfItems);
			// if(store.getSalesmanIds()!=null){
			// empList = new ArrayList<EmployeeInfo>();
			// for(int i = 0; i < store.getSalesmanIds().length; i++){
			// try{
			// empList
			// .add(EmployeeHelper.employeeToInfo(context.find(
			// Employee.class, store
			// .getSalesmanIds()[i])));
			// }
			// catch(Throwable t){
			// }
			// }
			// entity.setSalesMen(empList.toArray(new EmployeeInfo[0]));
			// }
			return entity;
		}
	}

	/**
	 * 获得仓库列表
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetStoreListProvider extends
			OneKeyResultProvider<ListEntity<StoreItem>, GetStoreListKey> {

		@Override
		protected ListEntity<StoreItem> provide(final Context context,
				final GetStoreListKey key) throws Throwable {
			Employee emp = context.find(Employee.class);
			List<StoreItemImpl> result = new ArrayList<StoreItemImpl>();
			ResourceToken<Tenant> toke = TenantHelper.getTenantToken(context);
//			List<Store> list = context.getResourceReferences(Store.class, toke);
			List<Store> list = context.getList(Store.class);
			for (Store store : list) {
				if(store.getId().equals(Store.GoodsStoreId))
					continue;
				if ((StoreModuleService.storeIsEmp(store, emp, context) || key
						.isAllStore())) {
					StoreItemImpl entity = StoreHelper.storeToStoreItem(store);
					entity.setKeeperIds(entity.getKeeperIds());
					String keeperInfo = "";
					for (int i = 0; i < entity.getKeeperIds().length; i++) {
						Employee er = context.find(Employee.class, entity
								.getKeeperIds()[i]);
						if (i > 0) {
							keeperInfo += ";";
						}
						keeperInfo += er.getName();
					}
					entity.setKeeperInfo(keeperInfo);
					result.add(entity);
				}
			}
			// if(result.isEmpty()){ //如果一个仓库都没有查出来，则返回所有仓库列表 诡异的要求
			// for(Store store : list){
			// StoreItemImpl entity = StoreHelper.storeToStoreItem(store);
			// entity.setKeeperIds(entity.getKeeperIds());
			// String keeperInfo = "";
			// for(int i = 0; i < entity.getKeeperIds().length; i++){
			// Employee er =
			// context.find(Employee.class, entity
			// .getKeeperIds()[i]);
			// if(i > 0){
			// keeperInfo += ";";
			// }
			// keeperInfo += er.getName();
			// }
			// entity.setKeeperInfo(keeperInfo);
			// result.add(entity);
			// }
			// }
			List<GUID> myEmployees = getMyEmployee(context);
			List<StoreItem> resultList = new ArrayList<StoreItem>();
			for (StoreItemImpl store : result) {
				if (storeIsstatus(store, key.getStatus())) {
					List<Action> actions = new ArrayList<Action>();
					// 可以停用的条件
					// 1.必须是启用的仓库
					// 2.租户仓库数量必须大于1 或者 开启了直供
					if (store.getStatus() == StoreStatus.ENABLE
							&& (result.size() > 1 || toke.getFacade()
									.isDirectSupply())) {
						actions.add(Action.DeActive);

					}
					if (store.getStatus() == StoreStatus.DISABLED) {
						actions.add(Action.Active);
						if (myEmployees.contains(store.getCreatePerson()))
							actions.add(Action.Delete);
					}

					if (store.getStatus() == StoreStatus.STOP) {
						actions.add(Action.Active);
					}
					store.setAction(actions.toArray(new Action[0]));
					resultList.add(store);
				}
			}
			Collections.sort(resultList, new SortComparator<StoreItem>() {

				public int compare(StoreItem o1, StoreItem o2) {
					int result = 0;
					if (key.getSortField() == SortField.None) {
						result = compare(o1.getId(), o2.getId());
					} else if (key.getSortField() == SortField.Name) {
						result = compare(o1.getName(), o2.getName());
					} else if (key.getSortField() == SortField.Address) {
						result = compare(o1.getAddress(), o2.getAddress());
					} else if (key.getSortField() == SortField.Keepers) {
						result = compare(o1.getKepperInfo(), o2.getKepperInfo());
					} else if (key.getSortField() == SortField.status) {
						result = compare(o1.getStatus().name(), o2.getStatus()
								.name());
					}
					return desc(result, key.getSortType().name());
				}
			});
			return new ListEntity<StoreItem>(resultList, result.size());
		}

		/**
		 * 获得当前员工管辖的员工列表
		 * 
		 * @param context
		 * @return List<GUID>
		 */
		private List<GUID> getMyEmployee(Context context) {
			Employee emp = context.find(Employee.class);
			List<Employee> list = new ArrayList<Employee>();
			if (emp.hasAuth(Auth.Boss)) {
				ResourceToken<Tenant> toke = TenantHelper
						.getTenantToken(context);
				list
						.addAll(context.getResourceReferences(Employee.class,
								toke));
			} else if (emp.hasAuth(Auth.StoreKeeperManager)) {
				list = context.getList(Employee.class,
						new GetChildrenDeptEmployeeListByAuthKey(emp
								.getDepartmentId(), Auth.StoreKeeper,
								Auth.StoreKeeperManager));
			} else {
				list.add(emp);
			}
			List<GUID> resultList = new ArrayList<GUID>();
			for (Employee employee : list) {
				resultList.add(employee.getId());
			}
			return resultList;
		}
	}

	/**
	 * 获得一个StoreItem对象
	 * 
	 */
	@Publish
	protected class GetStoreItemProvider extends
			OneKeyResultProvider<StoreItem, GUID> {
		@Override
		protected StoreItem provide(Context context, GUID key) throws Throwable {
			Store store = context.find(Store.class, key);
			StoreItemImpl entity = StoreHelper.storeToStoreItem(store);
			entity.setKeeperIds(entity.getKeeperIds());
			String keeperInfo = "";
			for (int i = 0; i < entity.getKeeperIds().length; i++) {
				Employee er = context.find(Employee.class, entity
						.getKeeperIds()[i]);
				if (i > 0) {
					keeperInfo += ";";
				}
				keeperInfo += er.getName();
			}
			entity.setKeeperInfo(keeperInfo);
			return entity;
		}
	}

	/**
	 * 获得仓库的系统参数
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetStoreSummaryDataProvider extends
			ResultProvider<StoreSummaryData> {

		@Override
		protected StoreSummaryData provide(Context context) throws Throwable {
			Tenant tr = TenantHelper.getTenant(context);
			boolean directSupply = tr
					.getSysParamstatus(SysParamKey.STORE_DIRECT);
			Store[] list = context.find(Store[].class);
			int totalCount = list.length;
			int initedCount = 0;
			int usingCount = 0;
			for (Store store : list) {
				if (store.getStatus() != StoreStatus.DISABLED) {
					usingCount++;
				}
			}
			return new StoreSummaryData(totalCount, initedCount, usingCount);
		}

	}

	/**
	 * 启用/停用仓库
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class ChanageStoreStatusHandler extends
			SimpleTaskMethodHandler<ChangeStoreStatusTask> {

		@Override
		protected void handle(Context context, ChangeStoreStatusTask task)
				throws Throwable {
			context.handle(new UpdateStoreStatusTask(task.getStoreId(), task
					.getStoreStatus()));
			context.dispatch(new StoreStatusChangeEvent(task.getStoreId()));
		}

	}

	private boolean storeIsstatus(StoreItem store, StoreStatus... status) {
		for (StoreStatus StoreStatus : status) {
			if (StoreStatus == StoreStatus.ALL)
				return true;
			if (store.getStatus() == StoreStatus)
				return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * 新建仓库
	 * </p>
	 * 
	 */
	@Publish
	protected final class createStoreHandler extends
			TaskMethodHandler<StoreInfoTask, StoreInfoTask.Method> {

		protected createStoreHandler() {
			super(StoreInfoTask.Method.Create);
		}

		@Override
		protected void handle(Context context, StoreInfoTask task)
				throws Throwable {
			synchronized (Lock.getInstance(context.find(Login.class)
					.getTenantId())) {
				ORMAccessor<StoreOrmEntity> accessor = context
						.newORMAccessor(orm_Store);
				Login login = context.find(Login.class);
				Employee emp = context.find(Employee.class,login.getEmployeeId());
				task.setId(context.newRECID());
				try {
					StoreOrmEntity entity = StoreHelper
							.storeInfoTaskToStoreOrmEntity(task);
					entity.setId(task.getId());
					entity.setCreateDate(System.currentTimeMillis());
					entity.setCreatorId(emp.getId());
					entity.setCreator(emp.getName());
					entity.setShelfCount(task.getShelfCount());
					entity.setDefaultTiersCount(task.getDefaultTiersCount());
					entity.setStoreNo(task.getStoreNo());
					entity.setStoreType(StoreType.MerterialsStore.getCode());
					if(task.getId().equals(Store.GoodsStoreId))
					{
						entity.setStoreType(StoreType.GoodsStore.getCode());
					}
					accessor.insert(entity);
					for (StoreEmployeeType set : StoreEmployeeType.values()) {
						saveStoreEmployee(context, task, entity, set);
					}
					saveShelfItem(context, task, entity);
					context.handle(new UpdateStoreResourceTask(entity.getId()),
							UpdateStoreResourceTask.Method.Put);
				}catch(Exception e){
					e.printStackTrace();
				} finally {
					accessor.unuse();

				}
			}

		}

	}

	/**
	 * 
	 * <p>
	 * 修改仓库
	 * </p>
	 * 
	 */
	@Publish
	protected final class UpdateStoreHandler extends
			TaskMethodHandler<StoreInfoTask, StoreInfoTask.Method> {

		protected UpdateStoreHandler() {
			super(StoreInfoTask.Method.Update);
		}

		@Override
		protected void handle(Context context, StoreInfoTask task)
				throws Throwable {
			synchronized (Lock.getInstance(context.find(Login.class)
					.getTenantId())) {
				ORMAccessor<StoreOrmEntity> accessor = context
						.newORMAccessor(orm_Store);
				try {
					StoreOrmEntity entity = accessor.findByRECID(task.getId());
					if (entity.getRecver() != task.getRecver()) {
						throw new PageExpiredException();
					}
					StoreHelper.storeInfoTaskToStoreOrmEntity(task, entity);
					entity.setRecver(entity.getRecver() + 1);
					entity.setShelfCount(task.getShelfCount());
					entity.setDefaultTiersCount(task.getDefaultTiersCount());
					accessor.update(entity);
					for (StoreEmployeeType set : StoreEmployeeType.values()) {
						saveStoreEmployee(context, task, entity, set);
					}
					if(StoreStatus.DISABLED.equals(task.getStatus()))
					{
						saveShelfItem(context, task, entity);
					}
					context.handle(new UpdateStoreResourceTask(task.getId()),
							UpdateStoreResourceTask.Method.Modify);
				} finally {
					accessor.unuse();
				}
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 删除仓库
	 * </p>
	 * 
	 */
	@Publish
	protected final class DeleteStoreHandler extends
			SimpleTaskMethodHandler<DeleteStoreTask> {

		@Override
		protected void handle(Context context, DeleteStoreTask task)
				throws Throwable {
			ORMAccessor<StoreOrmEntity> accessor = context
					.newORMAccessor(orm_Store);
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer
					.append("define delete Del_StoreEmployeeByStore(@storeGuid guid)");
			scriptBuffer.append(" begin");
			scriptBuffer.append(" delete from \"SA_STORE_EMPLOYEE\" as t ");
			scriptBuffer.append(" where t.storeGuid = @storeGuid ");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());
			try {
				accessor.delete(task.getStoreId());
				db.setArgumentValue(0, task.getStoreId());
				db.executeUpdate();
				context.handle(new UpdateStoreResourceTask(task.getStoreId()),
						UpdateStoreResourceTask.Method.Remove);
			} finally {
				db.unuse();
				accessor.unuse();
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 修改仓库状态
	 * </p>
	 * 
	 */
	@Publish
	protected final class UpdateStoreStatusHandler extends
			SimpleTaskMethodHandler<UpdateStoreStatusTask> {

		@Override
		protected void handle(Context context, UpdateStoreStatusTask task)
				throws Throwable {
			ORMAccessor<StoreOrmEntity> accessor = context
					.newORMAccessor(orm_Store);
			try {
				StoreOrmEntity entity = accessor.findByRECID(task.getId());
				entity.setStatus(task.getStoreStatus().getCode());
				accessor.update(entity);
				context.handle(new UpdateStoreResourceTask(task.getId()),
						UpdateStoreResourceTask.Method.Modify);
			} finally {
				accessor.unuse();
			}
		}
	}

	private void saveStoreEmployee(Context context, StoreInfoTask task,
			StoreOrmEntity impl, StoreEmployeeType storeEmployeeType) {
		StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
		scriptBuffer
				.append("define delete Del_StoreEmployeeByStore(@storeGuid guid)");
		scriptBuffer.append(" begin");
		scriptBuffer.append(" delete from \"SA_STORE_EMPLOYEE\" as t ");
		scriptBuffer.append(" where t.storeGuid = @storeGuid");
		scriptBuffer.append(" and t.employeeType='");
		scriptBuffer.append(storeEmployeeType.getCode()).append("'");
		scriptBuffer.append(" end");
		DBCommand db = context.prepareStatement(scriptBuffer.toString());
		try {
			db.setArgumentValue(0, task.getId());
			db.executeUpdate();
		} finally {
			db.unuse();
		}
		ORMAccessor<StoreEmployee> accessor = context
				.newORMAccessor(orm_StoreEmployee);
		try {
			GUID[] emps;
			// if(StoreEmployeeType.SALER == storeEmployeeType){
			// emps = task.getSalesIds();
			// }
			// else
			if (StoreEmployeeType.STOREMANAGER == storeEmployeeType) {
				emps = task.getKeeperIds();
			} else if (StoreEmployeeType.PURCHASE == storeEmployeeType) {
				emps = new GUID[0];
			} else {
				emps = new GUID[0];
			}
			if (emps != null && emps.length > 0) {
				if (storeEmployeeType == StoreEmployeeType.SALER) { // 如果是保存零售员工，需要验证该员工是否名花有主
					if (!validationRetailEmployeeIsVaild(context, emps[0], impl
							.getId())) {
						throw new IllegalArgumentException("该员工已被指定为某一仓库的零售人员。");
					}
				}
				Tenant tenant = context.find(Tenant.class);
				for (GUID emp : emps) {
					StoreEmployee se = new StoreEmployee();
					se.setRecid(context.newRECID());
					se.setEmployeeGuid(emp);
					se.setEmployeeType(storeEmployeeType.getCode());
					se.setStoreGuid(task.getId());
					se.setTenantId(tenant.getId());
					accessor.insert(se);
					// if (StoreEmployeeType.SALER == storeEmployeeType) {
					// impl.getSalesmanIdList().add(
					// context.find(EmployeeImpl.class, emp));
					// } else if (StoreEmployeeType.STOREMANAGER ==
					// storeEmployeeType) {
					// impl.getKeeperIdList().add(
					// context.find(EmployeeImpl.class, emp));
					// }
				}
			}
		} finally {
			accessor.unuse();
		}
	}

	/**
	 * 保存货位信息
	 * @param context
	 * @param task
	 * @param entity
	 */
	private void saveShelfItem(Context context, StoreInfoTask task,
			StoreOrmEntity entity) {
		ORMAccessor<ShelfItemImpl> orm = context.newORMAccessor(orm_Shelf);
		deleteAllShelf(context,task);
		for(ShelfInfoItem item:task.getShelfInfoItems())
		{
			ShelfItemImpl impl = new ShelfItemImpl();
			impl.setId(item.getId());
			impl.setShelfNo(item.getShelfNo());
			impl.setStoreId(task.getId());
			impl.setTiersCount(item.getTiersCount());
			orm.insert(impl);
		}
		orm.unuse();
	}
	
	/**
	 * 清空货位信息
	 * @param context
	 * @param task
	 */
	public void deleteAllShelf(Context context, StoreInfoTask task) {
		StringBuilder sb = new StringBuilder();
		sb.append("define delete deleteAllShelf(@storeId guid)\n");
		sb.append("begin\n");
		sb.append("delete from ");
		sb.append(ERPTableNames.Base.Shelf.getTableName());
		sb.append(" as t\n");
		sb.append("where t.storeId=@storeId\n");
		sb.append("end");
		DBCommand db = context.prepareStatement(sb);
		db.setArgumentValues(task.getId());
		try
		{
			db.executeUpdate();
		}
		finally
		{
			db.unuse();
		}
	}

	private boolean validationRetailEmployeeIsVaild(Context context,
			GUID empID, GUID storeId) {
		RecordSet rs = context.openQuery(
				qD_GetStoreSalesEmployeeListByTenantId, TenantHelper.getTenant(
						context).getId(), StoreEmployeeType.SALER.getCode());
		while (rs.next()) {
			GUID eid = rs.getFields().get(0).getGUID();
			GUID sid = rs.getFields().get(1).getGUID();
			if (eid.equals(empID) && !sid.equals(storeId)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 查询仓库管理可选择的销售员工
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-5-11
	 */
	@Publish
	protected final class GetSalesEmployeeListForStoreProvider
			extends
			OneKeyResultProvider<ListEntity<SelectSalesEmployeeByStoreItem>, GetSalesEmployeeListForStoreKey> {

		@Override
		protected ListEntity<SelectSalesEmployeeByStoreItem> provide(
				Context context, GetSalesEmployeeListForStoreKey key)
				throws Throwable {
			Tenant tenant = context.find(Tenant.class);
			List<StoreEmployee> emplyeeList = context.newORMAccessor(
					orm_StoreEmployee).fetch(tenant.getId(),
					StoreEmployee.StoreEmployeeType.SALER.getCode());
			Map<GUID, StoreEmployee> map = new HashMap<GUID, StoreEmployee>();
			for (StoreEmployee storeEmployee : emplyeeList) {
				map.put(storeEmployee.getEmployeeGuid(), storeEmployee);
			}
			List<Employee> list = context.getList(Employee.class,
					new com.spark.psi.base.key.GetEmployeeListByAuthKey(key
							.getAuths()));
			List<RoleInfo> roleList = context.getList(RoleInfo.class);
			List<SelectSalesEmployeeByStoreItem> resultList = new ArrayList<SelectSalesEmployeeByStoreItem>();
			for (Employee employee : list) {
				SelectSalesEmployeeByStoreItemImpl item = StoreHelper
						.employeeToItem(employee);

				if (map.containsKey(employee.getId())) {
					if (!map.get(employee.getId()).getStoreGuid().equals(
							key.getStoreId())) {
						Store store = context.find(Store.class, map.get(
								employee.getId()).getStoreGuid());
						item.setName(item.getName() + "(" + store.getName()
								+ ")");
						item.setDisable(true);
					}
				}

				Department dept = context.find(Department.class, item
						.getDepartmentId());
				item.setDepartmentName(dept.getName());
				StringBuilder roleTitle = new StringBuilder();
				for (int i = 0; i < roleList.size(); i++) {
					RoleInfo roleInfo = roleList.get(i);
					if (((1 << roleInfo.getCode()) & item.getRoles()) != 0) {
						if (roleTitle.length() > 0) {
							roleTitle.append(",");
						}
						roleTitle.append(roleInfo.getName());
					}

				}
				item.setRolesInfo(roleTitle.toString());
				resultList.add(item);
			}
			Collections.sort(resultList);
			return new ListEntity<SelectSalesEmployeeByStoreItem>(resultList,
					resultList.size());
		}

	}

	/**
	 * 
	 * <p>
	 * 验证仓库名称是否有效
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-5-10
	 */
	@Publish
	protected final class ValidationStoreNameIsValidHandler extends
			SimpleTaskMethodHandler<ValidationStoreNameIsValidTask> {

		@Override
		protected void handle(Context context,
				ValidationStoreNameIsValidTask task) throws Throwable {
			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context);
			List<Store> list = context
					.getResourceReferences(Store.class, token);
			for (Store store : list) {
				if (store.getName().equals(task.getName())
						&& store.getId() != task.getId()) {
					task.setValid(false);
					task.setMsg("仓库名称重复!");
				}
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 通过销售员工获得仓库
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-5-21
	 */
	@Publish
	protected final class GetSalesEmployeeStoreProvider extends
			OneKeyResultProvider<StoreInfo, GetSalesEmployeeStoreKey> {

		@Override
		protected StoreInfo provide(Context context,
				GetSalesEmployeeStoreKey key) throws Throwable {
			GUID empid = key.getEmployeeId() == null ? context
					.find(Login.class).getEmployeeId() : key.getEmployeeId();
			if (null == key.getStoreStatus()) {
				return StoreHelper.storeToStoreInfo(context.find(Store.class,
						new GetStoreBySaleManKey(empid, StoreStatus.ENABLE,
								StoreStatus.ONCOUNTING)));
			} else {
				return StoreHelper.storeToStoreInfo(context.find(Store.class,
						new GetStoreBySaleManKey(empid, key.getStoreStatus())));
			}
		}

	}

	/**
	 * 查询所有仓库数量
	 */
	@Publish
	protected final class FindStorageCountProvider extends
			OneKeyResultProvider<Integer, FindStorageCountKey> {

		/**
		 * 查询所有仓库数量的SQL
		 */
		private String getStorageCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_StorageCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from ");
			buffer.append(ERPTableNames.Base.Store.getTableName());
			buffer.append(" as t ");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindStorageCountKey key)
				throws Throwable {
			DBCommand dbCommand = context
					.prepareStatement(getStorageCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class)
						.getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 查询已经初始化仓库数量
	 */
	@Publish
	protected final class FindEnabledStorageCountProvider extends
			OneKeyResultProvider<Integer, FindEnabledStorageCountKey> {

		/**
		 * 查询已经初始化仓库数量的SQL
		 */
		private String getEnabledStorageCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("define query Qu_EnabledStorageCount(@tenantGuid guid, @StoreStatus string) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from ");
			buffer.append(ERPTableNames.Base.Store.getTableName());
			buffer.append(" as t ");
			buffer
					.append(" where t.StoreStatus=@StoreStatus");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context,
				FindEnabledStorageCountKey key) throws Throwable {
			DBCommand dbCommand = context
					.prepareStatement(getEnabledStorageCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class)
						.getTenantId());
				dbCommand.setArgumentValue(1, StoreStatus.ENABLE.getCode());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

}
