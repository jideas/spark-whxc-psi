package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>用户功能模块定位信息</p>
 *


 *
 
 * @version 2012-4-18
 */
public interface FunctionPosition{
	
	public GUID getId();
	
	/**
	 * 功能模块id
	 * 
	 * @return String
	 */
	public String getFunctionId();
	
	/**
	 * 用户id
	 * 
	 * @return GUID
	 */
	public GUID getUserId();
	
	/**
	 * 图标在桌面上的X坐标
	 * 
	 * @return int
	 */
	public int getXindex();
	
	/**
	 * 图标在桌面上的Y坐标
	 * 
	 * @return int
	 */
	public int getYindex();
	
	/**
	 * 是否放在桌面上
	 * 
	 * @return boolean
	 */
	public boolean isPutMain();
	
}
