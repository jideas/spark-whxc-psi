package com.spark.psi.base.publicimpl;

import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;

public class CustomerInfoImpl extends PartnerInfoImpl implements CustomerInfo{
	

	private EmployeeInfo salesman;

	public EmployeeInfo getSalesman(){
    	return salesman;
    }

	public void setSalesman(EmployeeInfo salesman){
    	this.salesman = salesman;
    }
	
}
