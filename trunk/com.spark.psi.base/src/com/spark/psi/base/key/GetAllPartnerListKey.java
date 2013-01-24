package com.spark.psi.base.key;

import com.spark.psi.publish.PartnerType;

/**
 * 
 * <p>获得所有的客户供应商列表</p>
 * 换成直接传 PartnerType 枚举


 *
 
 * @version 2012-3-12
 */
@Deprecated
public class GetAllPartnerListKey{
	
	/**
	 * 客户 or 供应商
	 */
	private PartnerType partnerType;

	/**
	 *  查询所有客户供应商列表
	 * @param partnerType 客户 or 供应商
	 */
	public GetAllPartnerListKey(PartnerType partnerType){
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType(){
    	return partnerType;
    }
	
	
}
