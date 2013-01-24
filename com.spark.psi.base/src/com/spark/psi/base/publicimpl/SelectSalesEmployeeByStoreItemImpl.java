package com.spark.psi.base.publicimpl;

import com.spark.psi.publish.base.store.entity.SelectSalesEmployeeByStoreItem;

public class SelectSalesEmployeeByStoreItemImpl extends EmployeePublishImpl
        implements SelectSalesEmployeeByStoreItem
{

	boolean disable;
	
	public boolean isDisable(){
		return disable;
	}

	public void setDisable(boolean disable){
    	this.disable = disable;
    }

	public int compareTo(SelectSalesEmployeeByStoreItem arg0){
		int result = this.getDepartmentName().compareTo(arg0.getDepartmentName());
		if(result!=0){
			return result; 
		}
		return this.getName().compareTo(arg0.getName());
    }


	

}
