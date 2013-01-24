package com.spark.uac.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.entity.HostInfo;
import com.spark.uac.service.impl.TenantInfoImpl.Service;

public interface TenantInfo {

	public GUID getId();

	public String getTenantTitle();
	
	public String getFishNumber();

	public String getBossMoible();

	public String getBossTitle();
	
	public Service getServcie(ProductSerialsEnum p);
	
	public Service[] getServices();

	
}
