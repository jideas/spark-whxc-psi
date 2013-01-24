package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SysParamKey;

/**
 * 租户信息<br>
 * 查询方法：根据GUID查询Tenant对象
 */
public interface Tenant {
//
//	protected GUID id;

//	/**
//	 * 是否开启直供
//	 */
//	protected boolean directSupply;
//
//	/**
//	 * @return the id
//	 */
//		return id;
//	}
//
	/**
	 * 是否开启直供
	 * @return the directSupply
	 */
	public boolean isDirectSupply();
	
	/**
	 * 租户ID
	 */
	public GUID getId();
	
	public boolean getSysParamstatus(SysParamKey key);

	/**
	 * 租户名称
	 * 
	 * @return String
	 */
	public String getTitle();
	
	/**
	 * 获得审批配置信息
	 * 
	 * @return ApprovalConfig
	 */
	public ApprovalConfig getApprovalConfig();
	
	/**
	 * 获得可用用户数
	 * 
	 * @return int
	 */
	public int getUserCount();
	
	/**
	 * 行业版本
	 * 
	 * @return String
	 */
	public String getProduct();
	
	/**
	 * 服务开始日期
	 * 
	 * @return long
	 */
	public long getStartDate();
	
	/**
	 * 服务到期日期
	 * 
	 * @return long
	 */
	public long getEndDate();
	
}
