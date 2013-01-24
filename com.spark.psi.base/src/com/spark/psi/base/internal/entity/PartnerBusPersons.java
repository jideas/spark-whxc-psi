package com.spark.psi.base.internal.entity;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>客户供应商业务负责人关联</p>
 *


 *
 
 * @version 2012-3-20
 */
@Deprecated
public class PartnerBusPersons{
	
	private GUID employeeId;
	
	private List<PartnerImpl> partners = new ArrayList<PartnerImpl>();
	
	public PartnerBusPersons(GUID emplyeeId){
		this.employeeId = emplyeeId;
	}

	public GUID getEmployeeId(){
    	return employeeId;
    }

	public List<PartnerImpl> getPartners(){
    	return partners;
    }

	public void setPartners(List<PartnerImpl> partners){
    	this.partners = partners;
    }
	
	public void addPartner(PartnerImpl partner){
		this.partners.add(partner);
	}
	
	
}
