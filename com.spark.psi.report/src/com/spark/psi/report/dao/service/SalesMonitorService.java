/**
 * 
 */
package com.spark.psi.report.dao.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Tenant;
import com.spark.psi.publish.report.entity.SalesTargetItem;
import com.spark.psi.publish.report.task.UpdateSalesTargetTask;
import com.spark.psi.report.entity.SalesTargetEntity;
import com.spark.psi.report.storage.monitor.ORMSalesTargetByPK;
import com.spark.psi.report.storage.monitor.ORMSalesTargetByYear;

/**
 * 销售目标监控
 */
public class SalesMonitorService extends Service {

	// private ORMSalesTargetByYear ormSalesTargetByYear;
	private ORMSalesTargetByPK ormSalesTargetByObject;

	/**
	 * @param title
	 */
	protected SalesMonitorService(ORMSalesTargetByYear ormSalesTargetByYear,
			ORMSalesTargetByPK ormSalesTargetByObject) {
		super("销售目标监控");
		// this.ormSalesTargetByYear = ormSalesTargetByYear;
		this.ormSalesTargetByObject = ormSalesTargetByObject;
	}

	@Publish
	protected class SalesTargetItemListProvider extends
	        ThreeKeyResultListProvider<SalesTargetItem, GUID[], String, String> {
		@Override
		protected void provide(Context context, GUID[] objects, String type,
				String year, List<SalesTargetItem> resultList) throws Throwable {
			Tenant tenant = context.find(Tenant.class);
			ORMAccessor<SalesTargetEntity> ormAccessor = context
					.newORMAccessor(ormSalesTargetByObject);
			for (int i = 0; i < objects.length; i++) {
				SalesTargetItem item = new SalesTargetItem();
				item.setObjectId(objects[i]);
				item.setDataType(type);
				item.setYear(year);
				//
				SalesTargetEntity entity = ormAccessor.first(tenant.getId(),
						objects[i], type, year);
				if (entity != null) {
					item.setValue01(entity.getValue01());
					item.setValue02(entity.getValue02());
					item.setValue03(entity.getValue03());
					item.setValue04(entity.getValue04());
					item.setValue05(entity.getValue05());
					item.setValue06(entity.getValue06());
					item.setValue07(entity.getValue07());
					item.setValue08(entity.getValue08());
					item.setValue09(entity.getValue09());
					item.setValue10(entity.getValue10());
					item.setValue11(entity.getValue11());
					item.setValue12(entity.getValue12());
				}
				resultList.add(item);
			}
		}
	}

	@Publish
	protected class UpdateSalesTargetTaskHandler extends
			SimpleTaskMethodHandler<UpdateSalesTargetTask> {

		@Override
		protected void handle(Context context, UpdateSalesTargetTask task)
				throws Throwable {

			Tenant tenant = context.find(Tenant.class);

			ORMAccessor<SalesTargetEntity> ormAccessor = context
					.newORMAccessor(ormSalesTargetByObject);

			//
			UpdateSalesTargetTask.Item[] items = task.getItems();
			for (int i = 0; i < items.length; i++) {
				//
				SalesTargetEntity entity = ormAccessor.first(tenant.getId(),
						items[i].getObjectId(), items[i].getDataType(),
						task.getYear());
				if (entity != null) {
					ormAccessor.delete(entity);
				}
				//
				entity = new SalesTargetEntity();
				entity.setId(GUID.randomID());
				entity.setTenantId(tenant.getId());
				entity.setObjectId(items[i].getObjectId());
				entity.setDataType(items[i].getDataType());
				entity.setYear(task.getYear());
				entity.setValue01(items[i].getValue01());
				entity.setValue02(items[i].getValue02());
				entity.setValue03(items[i].getValue03());
				entity.setValue04(items[i].getValue04());
				entity.setValue05(items[i].getValue05());
				entity.setValue06(items[i].getValue06());
				entity.setValue07(items[i].getValue07());
				entity.setValue08(items[i].getValue08());
				entity.setValue09(items[i].getValue09());
				entity.setValue10(items[i].getValue10());
				entity.setValue11(items[i].getValue11());
				entity.setValue12(items[i].getValue12());
				ormAccessor.insert(entity);
			}
		}
	}

}
