package com.spark.psi.publish.base.store.entity;

import com.spark.psi.publish.base.organization.entity.EmployeeItem;

/**
 * 
 * <p>�ֿ����ѡ������Ա��</p>
 * context.getList(ListEntity<SelectSalesEmployeeByStoreItem>.class,GetSalesEmployeeListForStoreKey)


 *
 
 * @version 2012-4-17
 */
public interface SelectSalesEmployeeByStoreItem extends EmployeeItem,Comparable<SelectSalesEmployeeByStoreItem>{
	
	/**
	 * �Ƿ����
	 * 
	 * @return boolean
	 */
	public boolean isDisable();
	
}
