package com.spark.psi.publish.base.store.entity;

import com.spark.psi.publish.base.organization.entity.EmployeeItem;

/**
 * 
 * <p>仓库管理选择销售员工</p>
 * context.getList(ListEntity<SelectSalesEmployeeByStoreItem>.class,GetSalesEmployeeListForStoreKey)


 *
 
 * @version 2012-4-17
 */
public interface SelectSalesEmployeeByStoreItem extends EmployeeItem,Comparable<SelectSalesEmployeeByStoreItem>{
	
	/**
	 * 是否禁用
	 * 
	 * @return boolean
	 */
	public boolean isDisable();
	
}
