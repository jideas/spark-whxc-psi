/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-19    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-19    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.StoreImpl;
import com.spark.psi.base.internal.entity.StoreEmployee.StoreEmployeeType;
import com.spark.psi.base.internal.entity.helper.StoreHelper;
import com.spark.psi.base.internal.entity.ormentity.StoreOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Store;
import com.spark.psi.base.internal.service.query.QD_GetAllStoreList;
import com.spark.psi.base.internal.service.query.QD_GetStoreById;
import com.spark.psi.base.internal.service.query.QD_GetStoreEmployeeListByStoreId;
import com.spark.psi.base.internal.service.query.QD_GetStoreEmployeeListByType;
import com.spark.psi.base.task.resource.UpdateStoreResourceTask;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.StoreType;
import com.spark.psi.publish.base.store.task.StoreInfoTask;

/**
 * <p>
 * 仓库内部服务
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 * 
 * @version 2012-3-19
 */

public class StoreInternalService extends ResourceService<Store, StoreImpl, StoreImpl> {

	// 查询所有仓库
	final QD_GetAllStoreList qD_AllStoreList;
	// 查询所有的仓库与员工的关联
	final QD_GetStoreEmployeeListByType qD_GetStoreEmployeeListByType;
	// 通过id查询仓库
	final QD_GetStoreById qD_GetStoreById;
	// 通过仓库id获得销售员工/仓库负责人列表
	final QD_GetStoreEmployeeListByStoreId qD_GetStoreEmployeeListByStoreId;
	final Orm_Store orm_Store;

	protected StoreInternalService(final QD_GetAllStoreList qD_AllStoreList, final QD_GetStoreEmployeeListByType qD_GetStoreEmployeeListByType,
			final QD_GetStoreById qD_GetStoreById, final QD_GetStoreEmployeeListByStoreId qD_GetStoreEmployeeListByStoreId
			,final Orm_Store orm_Store) {
		super("com.spark.psi.base.internal.service.StoreInternalService", ResourceKind.SINGLETON_IN_CLUSTER);

		this.qD_AllStoreList = qD_AllStoreList;
		this.qD_GetStoreEmployeeListByType = qD_GetStoreEmployeeListByType;
		this.qD_GetStoreById = qD_GetStoreById;
		this.qD_GetStoreEmployeeListByStoreId = qD_GetStoreEmployeeListByStoreId;
		this.orm_Store = orm_Store;

	}

	@Override
	protected void init(final Context context) {
		context.getList(Store.class);
	}

	@Override
	protected void initResources(Context context, ResourceInserter<Store, StoreImpl, StoreImpl> initializer) {
		List<StoreImpl> storeList = getAllStoreList(context); // 获得所有的仓库
		boolean hasVirtual = false;
		for (StoreImpl store : storeList) {
			if (store.getId().equals(Store.GoodsStoreId)) {
				hasVirtual = true;
			}
		}
		if (!hasVirtual) {
			insertVirtualStore(context);
			storeList = getAllStoreList(context);
		}

		Map<GUID, List<GUID>> salesmanMap = getStoreEmployee(context, StoreEmployeeType.SALER); // 获得所有的仓库销售人员
		Map<GUID, List<GUID>> storeManagerMap = getStoreEmployee(context, StoreEmployeeType.STOREMANAGER); // 获得所有的仓库管理员
		for (StoreImpl storeImpl : storeList) {
			// if (salesmanMap.get(storeImpl.getId()) != null) {
			// storeImpl.getSalesmanIdList().addAll(
			// salesmanMap.get(storeImpl.getId()));
			// }
			if (storeManagerMap.get(storeImpl.getId()) != null) {
				storeImpl.getKeeperIdList().addAll(storeManagerMap.get(storeImpl.getId()));
			}
			// ResourceToken<Store> token =
			initializer.putResource(storeImpl);
			// try {
			// ResourceToken<Tenant> parentToken =
			// context.findResourceToken(Tenant.class,storeImpl.getTenantId());
			// initializer.putResourceReference(parentToken, token);
			// } catch (Exception e) {
			// continue;
			// }
		}
		
	}

	private void insertVirtualStore(Context context) {
		ORMAccessor<StoreOrmEntity> accessor = context.newORMAccessor(orm_Store);
		try {
			StoreOrmEntity entity = new StoreOrmEntity();
			entity.setId(Store.GoodsStoreId);
			entity.setCreateDate(System.currentTimeMillis());
			entity.setStoreNo("CPK");
			entity.setStoreType(StoreType.GoodsStore.getCode());
			entity.setStatus(StoreStatus.ENABLE.getCode());
			entity.setStoreName("虚拟成品库");
			entity.setNamePY(PinyinHelper.getLetter(entity.getStoreName()));
			accessor.insert(entity);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			accessor.unuse();

		}

	}

	private Map<GUID, List<GUID>> getStoreEmployee(Context context, StoreEmployeeType storeEmployeeType) {
		Map<GUID, List<GUID>> result = new HashMap<GUID, List<GUID>>();
		RecordSet rs = context.openQuery(qD_GetStoreEmployeeListByType, storeEmployeeType.getCode());
		while (rs.next()) {
			GUID storeId = rs.getFields().get(0).getGUID();
			if (!result.containsKey(storeId)) {
				result.put(storeId, new ArrayList<GUID>());
			}
			result.get(storeId).add(rs.getFields().get(1).getGUID());
		}
		return result;
	}

	private List<StoreImpl> getAllStoreList(Context context) {
		List<StoreImpl> result = new ArrayList<StoreImpl>();
		RecordSet rs = context.openQuery(qD_AllStoreList);
		while (rs.next()) {
			result.add(fullData(context, rs));
		}
		return result;
	}

	private StoreImpl getStoreById(Context context, GUID id) {
		RecordSet rs = context.openQuery(qD_GetStoreById, id);
		if (rs.next()) {
			return fullData(context, rs);
		} else {
			return null;
		}
	}

	private List<GUID> getStoreEmployeeByStoreId(Context context, StoreEmployeeType storeEmployeeType, GUID storeId) {
		List<GUID> list = new ArrayList<GUID>();
		RecordSet rs = context.openQuery(qD_GetStoreEmployeeListByStoreId, storeEmployeeType.getCode(), storeId);
		while (rs.next()) {
			list.add(rs.getFields().get(1).getGUID());
		}
		return list;
	}

	private StoreImpl fullData(Context context, RecordSet rs) {
		int i = 0;
		StoreImpl store = new StoreImpl();
		store.setName(rs.getFields().get(i++).getString());
		String status = rs.getFields().get(i++).getString();
		store.setStatus(StoreStatus.getStatusByCode(status));
		String province = rs.getFields().get(i++).getString();
		if (province != null) {
			store.setProvince(context.find(EnumEntity.class, EnumType.Area, province));
		}
		String city = rs.getFields().get(i++).getString();
		if (city != null) {
			store.setCity(context.find(EnumEntity.class, EnumType.Area, city));
		}
		String district = rs.getFields().get(i++).getString();
		if (district != null) {
			store.setTown(context.find(EnumEntity.class, EnumType.Area, district));
		}
		store.setAddress(rs.getFields().get(i++).getString());
		store.setPostcode(rs.getFields().get(i++).getString());
		store.setMobileNo(rs.getFields().get(i++).getString());
		store.setConsignee(rs.getFields().get(i++).getString());
		store.setId(rs.getFields().get(i++).getGUID());
		store.setRecver(rs.getFields().get(i++).getInt());
		store.setCreatorId(rs.getFields().get(i++).getGUID());
		store.setStoreNo(rs.getFields().get(i++).getString());
		store.setShelfCount(rs.getFields().get(i++).getInt());
		store.setDefaultTiersCount(rs.getFields().get(i++).getInt());
		store.setStoreType(rs.getFields().get(i++).getString());
		return store;
	}

	final class GetStoreResourceByIdProvider extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(StoreImpl keysHolder) {
			return keysHolder.getId();
		}

	}

	// final class GetStoreResourceByIdProvider2 extends
	// TwoKeyResourceProvider<GUID,String> {
	//
	// @Override
	// protected GUID getKey1(StoreImpl keysHolder){
	// return null;
	// }
	//
	// @Override
	// protected String getKey2(StoreImpl keysHolder){
	// return keysHolder.getName();
	// }
	//
	//
	// }

	// @Publish
	// final protected class GetStoreByIdProvider extends
	// OneKeyResultProvider<Store, GUID> {
	//
	// @Override
	// protected Store provide(
	// ResourceContext<StoreImpl, StoreImpl, StoreImpl> context,
	// GUID key) throws Throwable {
	// return context.find(Store.class, key);
	// }
	//
	// }

	@Publish
	protected final class PutStoreResourceHandler extends TaskMethodHandler<UpdateStoreResourceTask, UpdateStoreResourceTask.Method> {

		protected PutStoreResourceHandler() {
			super(UpdateStoreResourceTask.Method.Put);
		}

		@Override
		protected void handle(ResourceContext<Store, StoreImpl, StoreImpl> context, UpdateStoreResourceTask task) throws Throwable {
			StoreImpl store = getStoreById(context, task.id);
			store.getKeeperIdList().addAll(getStoreEmployeeByStoreId(context, StoreEmployeeType.STOREMANAGER, store.getId()));
			// store.getSalesmanIdList().addAll(
			// getStoreEmployeeByStoreId(context, StoreEmployeeType.SALER,
			// store.getId()));
			ResourceToken<Store> token = context.putResource(store);
			// ResourceToken<Tenant> parentToken =
			// context.findResourceToken(Tenant.class,store.getTenantId());
			// context.putResourceReference(parentToken, token);
		}

	}

	@Publish
	protected final class ModifyStoreResourceHandler extends TaskMethodHandler<UpdateStoreResourceTask, UpdateStoreResourceTask.Method> {

		protected ModifyStoreResourceHandler() {
			super(UpdateStoreResourceTask.Method.Modify);
		}

		@Override
		protected void handle(ResourceContext<Store, StoreImpl, StoreImpl> context, UpdateStoreResourceTask task) throws Throwable {
			StoreImpl store_old = context.modifyResource(task.id);
			StoreImpl store_new = getStoreById(context, task.id);
			store_old.setAddress(store_new.getAddress());
			store_old.setCity(store_new.getCity());
			store_old.setConsignee(store_new.getConsignee());
			store_old.setCreatedDate(store_new.getCreatedDate());
			store_old.setCreatorId(store_new.getCreatorId());
			store_old.setCreator(store_new.getCreator());
			store_old.setTown(store_new.getTown());
			store_old.setMobileNo(store_new.getMobileNo());
			store_old.setName(store_new.getName());
			store_old.setPostcode(store_new.getPostcode());
			store_old.setProvince(store_new.getProvince());
			store_old.setStatus(StoreStatus.getStatusByCode(store_new.getStatus().getCode()));
			store_old.getKeeperIdList().clear();
			store_old.getKeeperIdList().addAll(getStoreEmployeeByStoreId(context, StoreEmployeeType.STOREMANAGER, store_old.getId()));
			// store_old.getSalesmanIdList().clear();
			// store_old.getSalesmanIdList().addAll(
			// getStoreEmployeeByStoreId(context, StoreEmployeeType.SALER,
			// store_old.getId()));
			store_old.setRecver(store_new.getRecver());
			store_old.setShelfCount(store_new.getShelfCount());
			store_old.setDefaultTiersCount(store_new.getDefaultTiersCount());
			context.postModifiedResource(store_old);
		}
	}

	@Publish
	protected final class RemoveStoreResourceHandler extends TaskMethodHandler<UpdateStoreResourceTask, UpdateStoreResourceTask.Method> {

		protected RemoveStoreResourceHandler() {
			super(UpdateStoreResourceTask.Method.Remove);
		}

		@Override
		protected void handle(ResourceContext<Store, StoreImpl, StoreImpl> context, UpdateStoreResourceTask task) throws Throwable {
			ResourceToken<Store> token = context.findResourceToken(Store.class, task.id);
			// ResourceToken<Tenant> parentToken =
			// context.findResourceToken(Tenant.class,token.getFacade().getTenantId());
			// context.removeResourceReference(parentToken, token);
			context.removeResource(task.id);
		}

	}

}
