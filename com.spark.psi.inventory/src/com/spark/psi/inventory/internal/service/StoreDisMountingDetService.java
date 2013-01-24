/**
 * 
 */
/**
 * 
 */
package com.spark.psi.inventory.internal.service;

import java.util.Date;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.inventory.internal.entity.DismountingItem;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.intf.inventoryenum.RefactorGoodsItemType;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.dismounting.DismountingItemKey;
import com.spark.psi.inventory.intf.task.dismounting.DismountingItemTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.service.orm.Orm_Store_Dismounting_Det;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;

/**
 * @author durendong
 * 
 */
public class StoreDisMountingDetService extends Service {

	protected final Orm_Store_Dismounting_Det ormStoreDismountingDet;

	protected StoreDisMountingDetService(Orm_Store_Dismounting_Det ormStoreDismountingDet) {
		super("com.jiuqi.assa.bus.store.dismounting.service.StoreDisMountingDetService");
		this.ormStoreDismountingDet = ormStoreDismountingDet;
	}

	/**
	 * 根据拆装单的GUID查询所有明细
	 * 
	 * @author durendong
	 * 
	 */
	@Publish
	protected class queryDet extends OneKeyResultListProvider<DismountingItem, DismountingItemKey> {

		@Override
		protected void provide(Context context, DismountingItemKey storeDisMountingDetKey, List<DismountingItem> list) throws Throwable {
			ORMAccessor<DismountingItem> ormAccessor = context.newORMAccessor(ormStoreDismountingDet);
			try {
				list.addAll(ormAccessor.fetch(storeDisMountingDetKey.getStoreDisGuid()));
			} finally {
				ormAccessor.unuse();
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
	protected class addDet extends TaskMethodHandler<DismountingItemTask, Method> {

		protected addDet() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, DismountingItemTask storeDisMountingDetTask) throws Throwable {
			ORMAccessor<DismountingItem> ormAccessor = context.newORMAccessor(ormStoreDismountingDet);
			try {
				List<DismountingItem> dismountingDets = storeDisMountingDetTask.getList();
				for (DismountingItem dismountingDet : dismountingDets) {
					ormAccessor.insert(dismountingDet);
					modifyInventory(context, dismountingDet, storeDisMountingDetTask);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ormAccessor.unuse();
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
	protected class delDet extends TaskMethodHandler<DismountingItemTask, Method> {

		protected delDet() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context, DismountingItemTask storeDisMountingDetTask) throws Throwable {
			ORMAccessor<DismountingItem> ormAccessor = context.newORMAccessor(ormStoreDismountingDet);
			try {
				ormAccessor.delete(storeDisMountingDetTask.getDismountingItem().getDismGuid());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ormAccessor.unuse();
			}
		}

	}

	public void modifyInventory(Context context, DismountingItem det, DismountingItemTask storeDisMountingDetTask) {

		InventoryBusTask bTask = new InventoryBusTask(storeDisMountingDetTask.getStoreId(), det.getGoodsGuid());
		StoStreamTask task = new StoStreamTask();
		InventoryLogEntity stoStream = new InventoryLogEntity();
		if (RefactorGoodsItemType.Source.getCode().equals(det.getDismFlag())) {
			bTask.setChangeCount(det.getDismCount() * (-1));
			bTask.setUpdateAvgPrice(false);

			stoStream.setOutstoCount(det.getDismCount());
			stoStream.setOutstoAmount(det.getStoreCost() * det.getDismCount());
		} else {
			bTask.setChangeCountAndAmount(det.getDismCount(), det.getMoney());
			bTask.setUpdateAvgPrice(true);

			stoStream.setInstoCount(det.getDismCount());
			stoStream.setInstoAmount(det.getMoney());
		}
		context.handle(bTask);

		stoStream.setCreatedDate(new Date().getTime());
		stoStream.setCreatePerson(context.find(Employee.class, context.find(Login.class).getEmployeeId()).getName());
		stoStream.setCreatedDate(new Date().getTime());
		stoStream.setStockId(det.getGoodsGuid());
		MaterialsItem materials = context.find(MaterialsItem.class, det.getGoodsGuid());
		if (null != materials) {
			stoStream.setProperties(MaterialsProperyUtil.subMaterialsPropertyToString(materials.getMaterialProperties()));
			stoStream.setName(materials.getMaterialName());
			stoStream.setCode(materials.getMaterialCode());
			stoStream.setCategoryId(materials.getCategoryId());
			stoStream.setLogType(InventoryType.Materials.getCode());
			stoStream.setScale(materials.getScale());
			stoStream.setStockNo(materials.getMaterialNo());
		}
		stoStream.setOrderId(storeDisMountingDetTask.getSheetId());
		stoStream.setOrderNo(storeDisMountingDetTask.getSheetNumber());
		stoStream.setLogType(InventoryLogType.STOREDISMOUNT.getCode());
		stoStream.setId(context.newRECID());
		stoStream.setStoreId(storeDisMountingDetTask.getStoreId());
		task.setStoStream(stoStream);
		context.handle(task, StoStreamTask.Task.add);
	}

}
