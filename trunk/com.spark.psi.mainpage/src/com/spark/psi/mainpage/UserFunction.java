package com.spark.psi.mainpage;

import java.util.Map;

import com.spark.common.components.pages.FunctionGroup;
import com.spark.common.components.pages.MainFunction;

/**
 * 
 * <p>用户功能模块按钮定位</p>
 *


 *
 
 * @version 2012-4-18
 */
public interface UserFunction extends MainFunction{
	
	
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
	
	/**
	 * 功能模块类别枚举
	 * 
	 * @return FunctionGroup
	 */
	public FunctionGroup getFunctionGroup();

	
	/**
	 * 获得角色优先级
	 * 
	 * @return Map<String,Integer>
	 */
	public Map<String,Integer> getRolePriority();	
}
