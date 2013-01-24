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
				if (salesMan.equals(id)&&isSelectable(element)) {  //�����Ա���Ѿ��������ֿ�Ҳ����ѡ��
					return true;
				}
			}
		}
		return false;// TODO����������Ա���Ƿ��Ѿ����������ֿ⣬ȷ���Ƿ�Ĭ��ѡ��
	}

	public boolean isSelectable(Object element) {
		return !((SelectSalesEmployeeByStoreItem)element).isDisable(); // TODO����������Ա���Ƿ��Ѿ����������ֿ⣬ȷ���Ƿ�ɸ���ѡ��
	}

	@Override
	protected String[] getSelections() {
		return table.getSelections(); // Ӧ��ȥ����Щ�Ѿ����������ֿ������
	}

}
