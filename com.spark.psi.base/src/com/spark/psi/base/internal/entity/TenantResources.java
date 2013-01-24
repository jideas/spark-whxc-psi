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
 * <p>�⻧��Դ����</p>
 *


 *
 
 * @version 2012-3-15
 */
@StructClass
public class TenantResources implements Tenant{
	//�⻧id
	private final GUID id;
	//�⻧title
	private final String title;
	
	private int userCount;
	
	private long startDate,endDate;
	
	private String product;
	
	
//	//��Ʒ�����б�
//	private List<GoodsCategoryImpl> goodsCategorys = new ArrayList<GoodsCategoryImpl>();

//	//��Ӧ���б�
//	private List<PartnerImpl> suppliers = new ArrayList<PartnerImpl>();
//	//�ͻ��б�
//	private List<PartnerImpl> customers = new ArrayList<PartnerImpl>();
	//�⻧������Ϣ
	private ApprovalConfig approvalConfigImpl = ApprovalConfig.DefualtApprovalConfig; 
	//�⻧ϵͳ����
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
//	 * ����⻧��������Ʒ����
//	 * 
//	 * @return GoodsCategory[]
//	 */
//	public GoodsCategory[] getGoodsCategorys(){
//		return goodsCategorys.toArray(new GoodsCategoryImpl[goodsCategorys.size()]);
//	}
	
	
//
//	/**
//	 * ����⻧�Ĺ�Ӧ���б�
//	 * 
//	 * @return Partner[]
//	 */
//	public Partner[] getSuppliers(){
//    	return suppliers.toArray(new Partner[suppliers.size()]);
//    }
//
//	/**
//	 * ����⻧�Ŀͻ��б�
//	 * 
//	 * @return Partner[]
//	 */
//	public Partner[] getCustomers(){
//    	return customers.toArray(new Partner[customers.size()]);
//    }
//	
	/**
	 * ����⻧��ϵͳ����ֵ
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
