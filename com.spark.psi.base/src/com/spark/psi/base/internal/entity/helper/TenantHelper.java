package com.spark.psi.base.internal.entity.helper;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.publicimpl.TenantInfoImpl;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.config.entity.TenantInfo;

public class TenantHelper{
	
	public static TenantInfo TenantResourceToTenantInfo(Tenant tr){
		if(tr==null)return null;
		TenantInfoImpl info = new TenantInfoImpl();
		info.setDirectDelivery(tr.getSysParamstatus(SysParamKey.STORE_DIRECT));
		info.setDealingsInited(tr.getSysParamstatus(SysParamKey.CUSPRO_INIT));
		info.setTitle(tr.getTitle());
		info.setUserCount(tr.getUserCount());
		info.setId(tr.getId());
		return info;
	}
	
	public static ResourceToken<Tenant> getTenantToken(final Context context){
		 return context.findResourceToken(Tenant.class,context.find(Login.class).getTenantId());
	}
	
	public static ResourceToken<Tenant> getTenantToken(final Context context,GUID id){
		if(id==null)return getTenantToken(context);
		 return context.findResourceToken(Tenant.class,id);
	}
	
	public static Tenant getTenant(final Context context){
		 return getTenantToken(context).getFacade();
	}
	
}
