package com.spark.psi.base.browser.store;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.store.entity.SelectSalesEmployeeByStoreItem;
import com.spark.psi.publish.base.store.key.GetSalesEmployeeListForStoreKey;

public class StoreSalesSelectProcessor extends StoreEmployeeSelectProcessor {

	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetSalesEmployeeListForStoreKey key = new GetSalesEmployeeListForStoreKey(storeId);
		@SuppressWarnings("unchecked")
		ListEntity<SelectSalesEmployeeByStoreItem> result = context.find(ListEntity.class, key);
		if (result != null && result.getTotalCount() > 0) {
			List<SelectSalesEmployeeByStoreItem> itemList = result.getItemList();
			return itemList.toArray();
		}
		return null;
	}

	public boolean isSelected(Object element) {
		String id = getElementId(element);
		if (employeeIds != null) {
			for (String salesMan : employeeIds) {
				if (salesMan.equals(id)&&isSelectable(element)) {  //如果此员工已经绑定其他仓库也不能选中
					return true;
				}
			}
		}
		return false;// TODO：根据销售员工是否已经管理其他仓库，确定是否默认选择
	}

	public boolean isSelectable(Object element) {
		return !((SelectSalesEmployeeByStoreItem)element).isDisable(); // TODO：根据销售员工是否已经管理其他仓库，确定是否可更改选择
	}

	@Override
	protected String[] getSelections() {
		return table.getSelections(); // 应该去掉那些已经关联其他仓库的销售
	}

}
