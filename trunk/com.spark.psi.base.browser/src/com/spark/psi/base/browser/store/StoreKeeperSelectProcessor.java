package com.spark.psi.base.browser.store;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListByAuthKey;

public class StoreKeeperSelectProcessor extends StoreEmployeeSelectProcessor {

	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetEmployeeListByAuthKey key = new GetEmployeeListByAuthKey(Auth.StoreKeeper);
		@SuppressWarnings("unchecked")
		ListEntity<EmployeeItem> result = context.find(ListEntity.class, key); // TOOD：应该过滤出有库管权限的员工
		if (result != null && result.getTotalCount() > 0) {
			List<EmployeeItem> itemList = result.getItemList();
			return itemList.toArray();
		}
		return null;
	}

	public boolean isSelected(Object element) {
		String id = getElementId(element);
		if(((EmployeeItem)element).getStatus()==EmployeeStatus.Supper)return true;
		if (employeeIds != null) {
			for (String keeper : employeeIds) {
				if (keeper.equals(id)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isSelectable(Object element) {
		return ((EmployeeItem)element).getStatus()!=EmployeeStatus.Supper;
	}

	@Override
	protected String[] getSelections() {
		return table.getSelections();
	}

}
