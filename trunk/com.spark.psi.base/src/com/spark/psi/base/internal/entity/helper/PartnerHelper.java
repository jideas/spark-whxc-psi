package com.spark.psi.base.internal.entity.helper;

import com.spark.psi.base.Partner;
import com.spark.psi.base.publicimpl.PartnerItemImpl;
import com.spark.psi.base.publicimpl.PartnerShortItemImpl;


public class PartnerHelper{
	
	public static PartnerItemImpl partnerToItem(Partner partner){
		PartnerItemImpl entity = new PartnerItemImpl();
		entity.setId(partner.getId());
		entity.setName(partner.getName());
		entity.setShortName(partner.getShortName());
		entity.setCreditAmount(partner.getCreditAmount());
		entity.setSalesmanId(partner.getBusinessPerson().getId());
		entity.setSalesmanName(partner.getBusinessPerson().getName());
		return entity;
	}
	
	public static PartnerShortItemImpl partnerToShortItem(Partner partner){
		PartnerShortItemImpl entity = new PartnerShortItemImpl();
		entity.setId(partner.getId());
		entity.setName(partner.getName());
		entity.setShortName(partner.getShortName());
		entity.setCreditAmount(partner.getCreditAmount());
		entity.setFax(partner.getFax());
		entity.setProvince(partner.getProvince());
		entity.setCity(partner.getCity());
		entity.setTown(partner.getTown());
		entity.setAddress(partner.getAddress());
		return entity;
	}
	
}