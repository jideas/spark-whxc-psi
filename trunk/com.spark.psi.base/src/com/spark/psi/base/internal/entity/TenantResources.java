package com.spark.psi.base.internal.entity;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.base.Tenant;
import com.spark.psi.publish.SysParamKey;

/**
 * 
 * <p>租户资源对象</p>
 *


 *
 
 * @version 2012-3-15
 */
@StructClass
public class TenantResources implements Tenant{
	//租户id
	private final GUID id;
	//租户title
	private final String title;
	
	private int userCount;
	
	private long startDate,endDate;
	
	private String product;
	
	
//	//商品分类列表
//	private List<GoodsCategoryImpl> goodsCategorys = new ArrayList<GoodsCategoryImpl>();

//	//供应商列表
//	private List<PartnerImpl> suppliers = new ArrayList<PartnerImpl>();
//	//客户列表
//	private List<PartnerImpl> customers = new ArrayList<PartnerImpl>();
	//租户配置信息
	private ApprovalConfig approvalConfigImpl = ApprovalConfig.DefualtApprovalConfig; 
	//租户系统参数
	private Map<SysParamKey,TenantSysParam> sysParams = new HashMap<SysParamKey,TenantSysParam>();
	
	public ApprovalConfig getApprovalConfig(){
    	return approvalConfigImpl;
    }

	public void setApprovalConfig(ApprovalConfig approvalConfigImpl){
    	this.approvalConfigImpl = approvalConfigImpl;
    }

	public TenantResources(GUID id, String title){
		this.id = id;
		this.title = title;
	}

	public GUID getId(){
    	return id;
    }	
	
	public String getTitle(){
		return title;
	}
//
//	public void addGoodsCategory(GoodsCategoryImpl category){
//		this.goodsCategorys.add(category);
//	}
//	
//	public void addSupplier(PartnerImpl partner){
//		this.suppliers.add(partner);
//	}
//	
//	public void addCustomer(PartnerImpl partner){
//		this.customers.add(partner);
//	}
	
	public void addSysParam(SysParamKey key,TenantSysParam tsp){
		sysParams.put(key, tsp);
	}

//	public List<GoodsCategoryImpl> getGoodsCategoryList(){
//    	return goodsCategorys;
//    }

//	
//	public List<PartnerImpl> getSuppliersList(){
//    	return suppliers;
//    }
//
//	public List<PartnerImpl> getCustomersList(){
//    	return customers;
//    }

//	/**
//	 * 获得租户的所有商品分类
//	 * 
//	 * @return GoodsCategory[]
//	 */
//	public GoodsCategory[] getGoodsCategorys(){
//		return goodsCategorys.toArray(new GoodsCategoryImpl[goodsCategorys.size()]);
//	}
	
	
//
//	/**
//	 * 获得租户的供应商列表
//	 * 
//	 * @return Partner[]
//	 */
//	public Partner[] getSuppliers(){
//    	return suppliers.toArray(new Partner[suppliers.size()]);
//    }
//
//	/**
//	 * 获得租户的客户列表
//	 * 
//	 * @return Partner[]
//	 */
//	public Partner[] getCustomers(){
//    	return customers.toArray(new Partner[customers.size()]);
//    }
//	
	/**
	 * 获得租户的系统参数值
	 */
	public boolean getSysParamstatus(SysParamKey key){
		TenantSysParam tsp = sysParams.get(key);
		if(tsp==null)return false;
	    return tsp.isYes();
    }

	public boolean isDirectSupply(){
	    return getSysParamstatus(SysParamKey.STORE_DIRECT);
    }

	public int getUserCount(){
    	return userCount;
    }

	public void setUserCount(int userCount){
    	this.userCount = userCount;
    }

	public long getStartDate(){
    	return startDate;
    }

	public void setStartDate(long startDate){
    	this.startDate = startDate;
    }

	public long getEndDate(){
    	return endDate;
    }

	public void setEndDate(long endDate){
    	this.endDate = endDate;
    }

	public String getProduct(){
    	return product;
    }

	public void setProduct(String product){
    	this.product = product;
    }

	public Map<SysParamKey, TenantSysParam> getSysParams(){
    	return sysParams;
    }

	public void setSysParams(Map<SysParamKey, TenantSysParam> sysParams){
    	this.sysParams = sysParams;
    }
	
}
