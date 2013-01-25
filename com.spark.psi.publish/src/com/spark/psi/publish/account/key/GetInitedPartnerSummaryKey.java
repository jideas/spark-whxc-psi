package com.spark.psi.publish.account.key;

import com.spark.psi.publish.PartnerType;

/**
 * 
 * <p>查询已设置初始化往来金额的客户or供应商总数</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 * 查询方式：context.find(Integer.class, GetInitedPartnerSummaryKey);
 * @version 2012-7-16
 */
public class GetInitedPartnerSummaryKey {
	
	private PartnerType partnerType;

	/**
	 * 
	 * @param partnerType 供应商or客户
	 */
	public GetInitedPartnerSummaryKey(PartnerType partnerType) {
		super();
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType() {
		return partnerType;
	}
	
	
}
