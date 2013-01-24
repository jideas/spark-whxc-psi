package com.spark.uac.service.impl;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.entity.TenantInfo;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.entity.HostInfo;
@StructClass
public class TenantInfoImpl implements TenantInfo {

	private GUID id;
	private String tenantTitle;
	private String fishNumber;
	private String bossTitle;
	private String bossMobile;
	private Service[] services;
	private String hostStr;

	public TenantInfoImpl(GUID id, String tenantTitle, String bossTitle,
			String bossMobile,String fishNumber) {
		super();
		this.id = id;
		this.tenantTitle = tenantTitle;
		this.bossTitle = bossTitle;
		this.bossMobile = bossMobile;
		this.fishNumber = fishNumber;
	}

	public void setServices(Service... services){
    	this.services = services;
    }



	public String getServicesStr(){
    	return hostStr;
    }

	public void setServicesStr(String hostStr){
    	this.hostStr = hostStr;
    }

	public GUID getId() {
		return this.id;
	}

	public String getTenantTitle() {
		return this.tenantTitle;
	}

	public String getBossTitle() {
		return this.bossTitle;
	}

	public String getBossMoible() {
		return bossMobile;
	}

	public String getFishNumber(){
    	return fishNumber;
    }

	public String getBossMobile(){
    	return bossMobile;
    }

	public void setBossMobile(String bossMobile){
    	this.bossMobile = bossMobile;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public void setTenantTitle(String tenantTitle){
    	this.tenantTitle = tenantTitle;
    }

	public void setFishNumber(String fishNumber){
    	this.fishNumber = fishNumber;
    }

	public void setBossTitle(String bossTitle){
    	this.bossTitle = bossTitle;
    }
	
	public static class Service {
		
		private ProductSerialsEnum productSerials;
		
		private HostInfo hostInfo;
		
		private long endDate;
		
		public Service(ProductSerialsEnum p,HostInfo hostInfo,long endDate){
	        this.productSerials = p;
	        this.endDate = endDate;
	        this.hostInfo = hostInfo;
        }
		public ProductSerialsEnum getProductSerials(){
        	return productSerials;
        }
		public void setProductSerials(ProductSerialsEnum productSerials){
        	this.productSerials = productSerials;
        }
		public HostInfo getHostInfo(){
        	return hostInfo;
        }
		public void setHostCode(HostInfo hostInfo){
        	this.hostInfo = hostInfo;
        }
		
		public boolean isValid(){
			return System.currentTimeMillis()<endDate;
		}
		
	}

	@Override
    public Service getServcie(ProductSerialsEnum p){
		for(Service service : this.services){
	        if(service.getProductSerials()==p)return service;
        }
	    return null;
    }

    public Service[] getServices(){
	    return services;
    }

}
